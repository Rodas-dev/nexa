package com.juniorsdevelopers.nexa.factory.model;

public class Rol {

    private int idRol;
    private String nombreRol;
    private String codigoRol;

    public Rol() {
    }

    public Rol(int idRol, String nombreRol, String codigoRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.codigoRol = codigoRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }
}
