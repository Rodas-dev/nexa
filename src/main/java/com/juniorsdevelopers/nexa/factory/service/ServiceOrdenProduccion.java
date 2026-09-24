package com.juniorsdevelopers.nexa.factory.service;

import com.juniorsdevelopers.nexa.factory.model.OrdenProduccion;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryOrdenProduccion;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ServiceOrdenProduccion {

    private final RepositoryOrdenProduccion repository;

    public ServiceOrdenProduccion() {
        this.repository = new RepositoryOrdenProduccion();
    }

    public List<OrdenProduccion> listar() {
        return repository.listar();
    }

    public List<OrdenProduccion> listarActivas() {
        return repository.listarActivas();
    }

    public int contarActivas() {
        return repository.contarActivas();
    }

    public int contarProcesosActivos() {
        return repository.contarProcesosActivos();
    }

    public int contarProduccionHoy() {
        return repository.contarProduccionHoy();
    }

    public String agregar(String idProductoStr, String idUsuarioStr, String cantidadStr) {
        String validacion = validarCampos(idProductoStr, idUsuarioStr, cantidadStr);
        if (validacion != null) return validacion;

        int idProducto = Integer.parseInt(idProductoStr);
        int idUsuario = Integer.parseInt(idUsuarioStr);
        int cantidad = Integer.parseInt(cantidadStr);
        Date fecha = Date.valueOf(LocalDate.now());

        OrdenProduccion orden = new OrdenProduccion(0, idProducto, idUsuario, cantidad, fecha, "Pendiente");

        return repository.agregar(orden) ? "OK" : "Error de BD al crear la orden.";
    }

    public String actualizar(int idOrden, String idProductoStr, String idUsuarioStr, String cantidadStr, String estado) {
        String validacion = validarCampos(idProductoStr, idUsuarioStr, cantidadStr);
        if (validacion != null) return validacion;

        if (estado == null || estado.isBlank()) return "Debe seleccionar un estado.";

        int idProducto = Integer.parseInt(idProductoStr);
        int idUsuario = Integer.parseInt(idUsuarioStr);
        int cantidad = Integer.parseInt(cantidadStr);

        OrdenProduccion orden = new OrdenProduccion(idOrden, idProducto, idUsuario, cantidad, null, estado);

        return repository.actualizar(orden) ? "OK" : "Error de BD al actualizar la orden.";
    }

    public String eliminar(int idOrden) {
        return repository.eliminar(idOrden) ? "OK" : "Error al eliminar la orden (puede estar en proceso).";
    }

    private String validarCampos(String idProductoStr, String idUsuarioStr, String cantidadStr) {
        if (idProductoStr == null || idProductoStr.isBlank() ||
            idUsuarioStr == null || idUsuarioStr.isBlank() ||
            cantidadStr == null || cantidadStr.isBlank()) {
            return "Todos los campos de ID y Cantidad son obligatorios.";
        }
        try {
            int prod = Integer.parseInt(idProductoStr);
            int usu = Integer.parseInt(idUsuarioStr);
            int cant = Integer.parseInt(cantidadStr);
            if (prod <= 0 || usu <= 0 || cant <= 0) {
                return "Los ID y la cantidad deben ser mayores a 0.";
            }
        } catch (NumberFormatException e) {
            return "ID de producto, ID de usuario y cantidad deben ser números enteros.";
        }
        return null;
    }
}