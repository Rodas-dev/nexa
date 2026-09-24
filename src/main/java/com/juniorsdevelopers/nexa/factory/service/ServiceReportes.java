package com.juniorsdevelopers.nexa.factory.service;

import com.juniorsdevelopers.nexa.factory.model.MateriaPrima;
import com.juniorsdevelopers.nexa.factory.model.OrdenProduccion;
import com.juniorsdevelopers.nexa.factory.model.Producto;
import com.juniorsdevelopers.nexa.factory.model.Reporte;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryMateriaPrima;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryOrdenProduccion;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryProducto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceReportes {

    private final RepositoryProducto repositoryProducto;
    private final RepositoryMateriaPrima repositoryMateriaPrima;
    private final RepositoryOrdenProduccion repositoryOrdenProduccion;

    public ServiceReportes() {
        this.repositoryProducto = new RepositoryProducto();
        this.repositoryMateriaPrima = new RepositoryMateriaPrima();
        this.repositoryOrdenProduccion = new RepositoryOrdenProduccion();
    }

    public List<Reporte> generarReporte(String tipoReporte, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Reporte> listaReportes = new ArrayList<>();

        if (tipoReporte == null || tipoReporte.trim().isEmpty()) {
            return listaReportes;
        }

        switch (tipoReporte) {
            case "Productos":
            case "Inventario de Productos":
                List<Producto> productos = repositoryProducto.listar();
                for (Producto p : productos) {
                    String descripcion = p.getNombreProducto();
                    if (p.getDescripcionProducto() != null && !p.getDescripcionProducto().isBlank()) {
                        descripcion += " - " + p.getDescripcionProducto();
                    }
                    listaReportes.add(new Reporte(
                        "N/A",
                        descripcion,
                        p.getStockProducto(),
                        p.getEstadoProducto()
                    ));
                }
                break;

            case "Materia Prima":
            case "Inventario de Materia Prima":
                List<MateriaPrima> materias = repositoryMateriaPrima.listar();
                for (MateriaPrima m : materias) {
                    String descripcion = m.getNombreMateria() + " (" + m.getUnidadMedida() + ")";
                    listaReportes.add(new Reporte(
                        "N/A",
                        descripcion,
                        m.getStockMateria(),
                        m.getEstadoMateria()
                    ));
                }
                break;

            case "Órdenes de Producción":
            case "Órdenes de Fabricación":
                List<OrdenProduccion> ordenes = repositoryOrdenProduccion.listar();
                for (OrdenProduccion o : ordenes) {
                    LocalDate fechaOrden = (o.getFechaCreacion() != null) ? o.getFechaCreacion().toLocalDate() : null;

                    boolean dentroDeRango = true;
                    if (fechaOrden != null) {
                        if (fechaInicio != null && fechaOrden.isBefore(fechaInicio)) {
                            dentroDeRango = false;
                        }
                        if (fechaFin != null && fechaOrden.isAfter(fechaFin)) {
                            dentroDeRango = false;
                        }
                    }

                    if (dentroDeRango) {
                        String fechaStr = (fechaOrden != null) ? fechaOrden.toString() : "N/A";
                        String descripcion = "Orden #" + o.getIdOrdenProduccion() + " (Producto ID: " + o.getIdProducto() + ")";
                        listaReportes.add(new Reporte(
                            fechaStr,
                            descripcion,
                            o.getCantidadOrden(),
                            o.getEstadoOrden()
                        ));
                    }
                }
                break;

            default:
                break;
        }

        return listaReportes;
    }
}