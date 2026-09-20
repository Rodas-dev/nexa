package com.juniorsdevelopers.nexa.factory.service;

import com.juniorsdevelopers.nexa.factory.model.MateriaPrima;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryMateriaPrima;

import java.util.List;

public class ServiceMateriaPrima {

    private static final int UMBRAL_BAJO_STOCK = 20;

    private final RepositoryMateriaPrima repository;

    public ServiceMateriaPrima() {
        this.repository = new RepositoryMateriaPrima();
    }

    public List<MateriaPrima> listar() {
        return repository.listar();
    }

    public String agregar(String nombre, String stockTexto, String unidad) {
        String validacion = validarCampos(nombre, stockTexto, unidad);
        if (validacion != null) {
            return validacion;
        }

        int stock = Integer.parseInt(stockTexto);
        String estado = calcularEstado(stock);
        MateriaPrima materia = new MateriaPrima(0, nombre, stock, unidad, estado);

        return repository.agregar(materia) ? "OK" : "No se pudo agregar la materia prima.";
    }

    public String actualizar(int idMateriaPrima, String nombre, String stockTexto, String unidad) {
        String validacion = validarCampos(nombre, stockTexto, unidad);
        if (validacion != null) {
            return validacion;
        }

        int stock = Integer.parseInt(stockTexto);
        String estado = calcularEstado(stock);
        MateriaPrima materia = new MateriaPrima(idMateriaPrima, nombre, stock, unidad, estado);

        return repository.actualizar(materia) ? "OK" : "No se pudo actualizar la materia prima.";
    }

    public String eliminar(int idMateriaPrima) {
        return repository.eliminar(idMateriaPrima) ? "OK" : "No se pudo eliminar la materia prima.";
    }

    private String validarCampos(String nombre, String stockTexto, String unidad) {
        if (nombre == null || nombre.isBlank() ||
            stockTexto == null || stockTexto.isBlank() ||
            unidad == null || unidad.isBlank()) {
            return "Debes completar todos los campos.";
        }

        try {
            int stock = Integer.parseInt(stockTexto);
            if (stock < 0) {
                return "El stock no puede ser negativo.";
            }
        } catch (NumberFormatException e) {
            return "El stock debe ser un número entero.";
        }

        return null;
    }

    private String calcularEstado(int stock) {
        if (stock <= 0) {
            return "Agotado";
        }
        if (stock < UMBRAL_BAJO_STOCK) {
            return "Bajo stock";
        }
        return "Disponible";
    }
}