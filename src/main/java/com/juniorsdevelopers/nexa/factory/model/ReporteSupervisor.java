package com.juniorsdevelopers.nexa.factory.model;

public class ReporteSupervisor {

    private int idOrden;
    private String producto;
    private int cantidadOrden;
    private int cantidadProducida;
    private String estado;

    public ReporteSupervisor() {
    }

    public ReporteSupervisor(
            int idOrden,
            String producto,
            int cantidadOrden,
            int cantidadProducida,
            String estado) {

        this.idOrden = idOrden;
        this.producto = producto;
        this.cantidadOrden = cantidadOrden;
        this.cantidadProducida = cantidadProducida;
        this.estado = estado;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidadOrden() {
        return cantidadOrden;
    }

    public void setCantidadOrden(int cantidadOrden) {
        this.cantidadOrden = cantidadOrden;
    }

    public int getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(int cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}