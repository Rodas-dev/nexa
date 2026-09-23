package com.juniorsdevelopers.nexa.factory.service;

import com.juniorsdevelopers.nexa.factory.model.OrdenProduccion;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryOrdenProduccion;

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
}