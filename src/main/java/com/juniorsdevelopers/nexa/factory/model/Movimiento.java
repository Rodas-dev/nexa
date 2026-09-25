package com.juniorsdevelopers.nexa.factory.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Movimiento {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty tipo;
    private final SimpleStringProperty producto;
    private final SimpleIntegerProperty cantidad;
    private final SimpleStringProperty fecha;
    private final SimpleStringProperty usuario;

    public Movimiento(int id, String tipo, String producto, int cantidad, String fecha, String usuario) {
        this.id = new SimpleIntegerProperty(id);
        this.tipo = new SimpleStringProperty(tipo);
        this.producto = new SimpleStringProperty(producto);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.fecha = new SimpleStringProperty(fecha);
        this.usuario = new SimpleStringProperty(usuario);
    }

    public int getId() { return id.get(); }
    public String getTipo() { return tipo.get(); }
    public String getProducto() { return producto.get(); }
    public int getCantidad() { return cantidad.get(); }
    public String getFecha() { return fecha.get(); }
    public String getUsuario() { return usuario.get(); }
}

