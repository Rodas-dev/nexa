package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.model.OrdenProduccion;
import com.juniorsdevelopers.nexa.factory.service.ServiceOrdenProduccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.List;

public class OrdenProduccionController {

    @FXML private TextField campoIdProducto;
    @FXML private TextField campoIdUsuario;
    @FXML private TextField campoCantidad;
    @FXML private ComboBox<String> comboEstado;
    
    @FXML private TableView<OrdenProduccion> tablaOrdenes;
    @FXML private TableColumn<OrdenProduccion, Integer> columnaIdOrden;
    @FXML private TableColumn<OrdenProduccion, Integer> columnaProducto;
    @FXML private TableColumn<OrdenProduccion, Integer> columnaUsuario;
    @FXML private TableColumn<OrdenProduccion, Integer> columnaCantidad;
    @FXML private TableColumn<OrdenProduccion, Date> columnaFecha;
    @FXML private TableColumn<OrdenProduccion, String> columnaEstado;

    private final ServiceOrdenProduccion service = new ServiceOrdenProduccion();
    private final ObservableList<OrdenProduccion> datos = FXCollections.observableArrayList();
    private OrdenProduccion ordenSeleccionada;

    @FXML
    public void initialize() {
        comboEstado.getItems().addAll("Pendiente", "En Proceso", "Finalizado", "Cancelado");
        comboEstado.setDisable(true); // Solo se activa al editar, crear es "Pendiente" por defecto.

        columnaIdOrden.setCellValueFactory(new PropertyValueFactory<>("idOrdenProduccion"));
        columnaProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        columnaUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadOrden"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estadoOrden"));

        tablaOrdenes.setItems(datos);

        tablaOrdenes.getSelectionModel().selectedItemProperty().addListener((obs, anterior, actual) -> {
            if (actual != null) cargarFormulario(actual);
        });

        cargarDatos();
    }

    private void cargarDatos() {
        List<OrdenProduccion> lista = service.listar();
        datos.setAll(lista);
    }

    private void cargarFormulario(OrdenProduccion orden) {
        ordenSeleccionada = orden;
        campoIdProducto.setText(String.valueOf(orden.getIdProducto()));
        campoIdUsuario.setText(String.valueOf(orden.getIdUsuario()));
        campoCantidad.setText(String.valueOf(orden.getCantidadOrden()));
        comboEstado.setValue(orden.getEstadoOrden());
        comboEstado.setDisable(false);
    }

    @FXML
    private void guardar(ActionEvent evento) {
        String idProd = campoIdProducto.getText();
        String idUsu = campoIdUsuario.getText();
        String cant = campoCantidad.getText();
        
        String resultado;
        if (ordenSeleccionada == null) {
            resultado = service.agregar(idProd, idUsu, cant);
        } else {
            String estado = comboEstado.getValue();
            resultado = service.actualizar(ordenSeleccionada.getIdOrdenProduccion(), idProd, idUsu, cant, estado);
        }

        if (resultado.equals("OK")) {
            cargarDatos();
            limpiar(evento);
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, resultado);
        }
    }

    @FXML
    private void eliminar(ActionEvent evento) {
        if (ordenSeleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona una orden en la tabla para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Eliminar Orden");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Seguro que quieres eliminar la orden #" + ordenSeleccionada.getIdOrdenProduccion() + "?");

        confirmacion.showAndWait().ifPresent(boton -> {
            if (boton.getButtonData().isDefaultButton()) {
                String resultado = service.eliminar(ordenSeleccionada.getIdOrdenProduccion());
                if (resultado.equals("OK")) {
                    cargarDatos();
                    limpiar(evento);
                } else {
                    mostrarAlerta(Alert.AlertType.WARNING, resultado);
                }
            }
        });
    }

    @FXML
    private void limpiar(ActionEvent evento) {
        ordenSeleccionada = null;
        campoIdProducto.clear();
        campoIdUsuario.clear();
        campoCantidad.clear();
        comboEstado.setValue(null);
        comboEstado.setDisable(true);
        tablaOrdenes.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Gestión de Órdenes");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
