package com.juniorsdevelopers.nexa.factory.service;

import com.juniorsdevelopers.nexa.factory.model.ReporteSupervisor;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryReporteSupervisor;

import java.util.List;

public class ServiceReporteSupervisor {

    private final RepositoryReporteSupervisor repository;

    public ServiceReporteSupervisor() {
        this.repository = new RepositoryReporteSupervisor();
    }

    public List<ReporteSupervisor> listar() {
        return repository.listar();
    }
}