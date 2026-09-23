package com.juniorsdevelopers.nexa.factory.model;

import java.sql.Date;

public class RegistroAvance {

    private int idAvance;
    private int idOrden;
    private int idUsuario;
    private String nombreUsuario;
    private int cantidadProducida;
    private Date fechaRegistro;

    public RegistroAvance() {
    }

    public RegistroAvance(
            int idAvance,
            int idOrden,
            int idUsuario,
            String nombreUsuario,
            int cantidadProducida,
            Date fechaRegistro) {

        this.idAvance = idAvance;
        this.idOrden = idOrden;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.cantidadProducida = cantidadProducida;
        this.fechaRegistro = fechaRegistro;
    }

    public RegistroAvance(
            int idOrden,
            int idUsuario,
            int cantidadProducida,
            Date fechaRegistro) {

        this.idOrden = idOrden;
        this.idUsuario = idUsuario;
        this.cantidadProducida = cantidadProducida;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdAvance() {
        return idAvance;
    }

    public void setIdAvance(int idAvance) {
        this.idAvance = idAvance;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(int cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}