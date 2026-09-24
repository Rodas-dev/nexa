package com.juniorsdevelopers.nexa.factory.model;

import java.sql.Date;

public class OrdenProduccion {

    private int idOrdenProduccion;
    private int idProducto;
    private int idUsuario;
    private String nombreProducto;
    private String nombreUsuario;
    private int cantidadOrden;
    private int cantidadProducida;
    private Date fechaCreacion;
    private String estadoOrden;

    public OrdenProduccion() {
    }

    public OrdenProduccion(int idOrdenProduccion, int idProducto, int idUsuario, int cantidadOrden, Date fechaCreacion, String estadoOrden) {
        this(idOrdenProduccion, idProducto, idUsuario, null, null, cantidadOrden, 0, fechaCreacion, estadoOrden);
    }

    public OrdenProduccion(
            int idOrdenProduccion,
            int idProducto,
            int idUsuario,
            String nombreProducto,
            String nombreUsuario,
            int cantidadOrden,
            int cantidadProducida,
            Date fechaCreacion,
            String estadoOrden) {

        this.idOrdenProduccion = idOrdenProduccion;
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
        this.nombreProducto = nombreProducto;
        this.nombreUsuario = nombreUsuario;
        this.cantidadOrden = cantidadOrden;
        this.cantidadProducida = cantidadProducida;
        this.fechaCreacion = fechaCreacion;
        this.estadoOrden = estadoOrden;
    }

    public int getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(int idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }
}