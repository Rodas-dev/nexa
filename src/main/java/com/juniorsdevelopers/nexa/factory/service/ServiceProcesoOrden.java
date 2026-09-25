package com.juniorsdevelopers.nexa.factory.service;

import com.juniorsdevelopers.nexa.factory.model.ProcesoOrden;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryProcesoOrden;

import java.util.List;

public class ServiceProcesoOrden {

    private final RepositoryProcesoOrden repository;

    public ServiceProcesoOrden() {
        this.repository = new RepositoryProcesoOrden();
    }

    public List<ProcesoOrden> listar() {
        return repository.listar();
    }

    public List<ProcesoOrden> listarPorOrden(int idOrden) {
        return repository.listarPorOrden(idOrden);
    }

    public int contarActivos() {
        return repository.contarActivos();
    }
}