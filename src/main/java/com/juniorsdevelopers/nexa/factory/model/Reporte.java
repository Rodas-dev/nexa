package com.juniorsdevelopers.nexa.factory.model;

public class Reporte {

    private String fecha;
    private String descripcion;
    private int cantidad;
    private String estado;

    public Reporte() {
    }

    public Reporte(String fecha, String descripcion, int cantidad, String estado) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}