package com.juniorsdevelopers.nexa.factory.model;

import java.sql.Date;

public class OrdenProduccion {
    private int idOrdenProduccion;
    private int idProducto;
    private int idUsuario;
    private int cantidadOrden;
    private Date fechaCreacion;
    private String estadoOrden;

    public OrdenProduccion() {
    }

    public OrdenProduccion(int idOrdenProduccion, int idProducto, int idUsuario, int cantidadOrden, Date fechaCreacion, String estadoOrden) {
        this.idOrdenProduccion = idOrdenProduccion;
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
        this.cantidadOrden = cantidadOrden;
        this.fechaCreacion = fechaCreacion;
        this.estadoOrden = estadoOrden;
    }

    public int getIdOrdenProduccion() { return idOrdenProduccion; }
    public void setIdOrdenProduccion(int idOrdenProduccion) { this.idOrdenProduccion = idOrdenProduccion; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getCantidadOrden() { return cantidadOrden; }
    public void setCantidadOrden(int cantidadOrden) { this.cantidadOrden = cantidadOrden; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEstadoOrden() { return estadoOrden; }
    public void setEstadoOrden(String estadoOrden) { this.estadoOrden = estadoOrden; }
}
