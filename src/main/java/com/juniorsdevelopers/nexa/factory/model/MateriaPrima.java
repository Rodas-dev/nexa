package com.juniorsdevelopers.nexa.factory.model;

public class MateriaPrima {

    private int idMateriaPrima;
    private String nombreMateria;
    private int stockMateria;
    private String unidadMedida;
    private String estadoMateria;

    public MateriaPrima() {
    }

    public MateriaPrima(int idMateriaPrima, String nombreMateria, int stockMateria, String unidadMedida, String estadoMateria) {
        this.idMateriaPrima = idMateriaPrima;
        this.nombreMateria = nombreMateria;
        this.stockMateria = stockMateria;
        this.unidadMedida = unidadMedida;
        this.estadoMateria = estadoMateria;
    }

    public int getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(int idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getStockMateria() {
        return stockMateria;
    }

    public void setStockMateria(int stockMateria) {
        this.stockMateria = stockMateria;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getEstadoMateria() {
        return estadoMateria;
    }

    public void setEstadoMateria(String estadoMateria) {
        this.estadoMateria = estadoMateria;
    }
}