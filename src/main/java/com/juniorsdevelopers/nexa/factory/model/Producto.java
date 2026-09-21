package com.juniorsdevelopers.nexa.factory.model;

public class Producto {

    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private int stockProducto;
    private String estadoProducto;

    public Producto() {
    }

    public Producto(int idProducto, String nombreProducto, String descripcionProducto, int stockProducto, String estadoProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.stockProducto = stockProducto;
        this.estadoProducto = estadoProducto;
    }

    public Producto(String nombreProducto, String descripcionProducto, int stockProducto, String estadoProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.stockProducto = stockProducto;
        this.estadoProducto = estadoProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public String getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(String estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
}