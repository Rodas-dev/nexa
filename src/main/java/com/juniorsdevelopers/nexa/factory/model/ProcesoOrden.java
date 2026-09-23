package com.juniorsdevelopers.nexa.factory.model;

public class ProcesoOrden {

    private int idProceso;
    private int idOrden;
    private String nombreProceso;
    private String estadoProceso;
    private int ordenSecuencia;

    public ProcesoOrden() {
    }

    public ProcesoOrden(
            int idProceso,
            int idOrden,
            String nombreProceso,
            String estadoProceso,
            int ordenSecuencia) {

        this.idProceso = idProceso;
        this.idOrden = idOrden;
        this.nombreProceso = nombreProceso;
        this.estadoProceso = estadoProceso;
        this.ordenSecuencia = ordenSecuencia;
    }

    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public int getOrdenSecuencia() {
        return ordenSecuencia;
    }

    public void setOrdenSecuencia(int ordenSecuencia) {
        this.ordenSecuencia = ordenSecuencia;
    }
}