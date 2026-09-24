package com.juniorsdevelopers.nexa.factory.service;

import com.juniorsdevelopers.nexa.factory.model.RegistroAvance;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryRegistroAvance;

import java.util.List;

public class ServiceRegistroAvance {

    private final RepositoryRegistroAvance repository;

    public ServiceRegistroAvance() {
        this.repository = new RepositoryRegistroAvance();
    }

    public List<RegistroAvance> listar() {
        return repository.listar();
    }

    public List<RegistroAvance> listarPorOrden(int idOrden) {
        return repository.listarPorOrden(idOrden);
    }

    public int contarProduccionHoy() {
        return repository.contarProduccionHoy();
    }

    public boolean registrar(RegistroAvance avance) {
        return repository.registrar(avance);
    }
}