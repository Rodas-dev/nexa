package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.model.MateriaPrima;
import com.juniorsdevelopers.nexa.factory.service.ServiceMateriaPrima;
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

import java.util.List;

public class MateriaPrimaController {

    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoStock;

    @FXML
    private ComboBox<String> comboUnidad;

    @FXML
    private TableView<MateriaPrima> tablaMateriaPrima;

    @FXML
    private TableColumn<MateriaPrima, String> columnaNombre;

    @FXML
    private TableColumn<MateriaPrima, Integer> columnaStock;

    @FXML
    private TableColumn<MateriaPrima, String> columnaUnidad;

    @FXML
    private TableColumn<MateriaPrima, String> columnaEstado;

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonEliminar;

    @FXML
    private Button botonLimpiar;

    private final ServiceMateriaPrima service = new ServiceMateriaPrima();
    private final ObservableList<MateriaPrima> datos = FXCollections.observableArrayList();
    private MateriaPrima materiaSeleccionada;

    @FXML
    public void initialize() {
        comboUnidad.getItems().addAll("kg", "g", "l", "ml", "unidad");

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreMateria"));
        columnaStock.setCellValueFactory(new PropertyValueFactory<>("stockMateria"));
        columnaUnidad.setCellValueFactory(new PropertyValueFactory<>("unidadMedida"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estadoMateria"));

        tablaMateriaPrima.setItems(datos);

        tablaMateriaPrima.getSelectionModel().selectedItemProperty().addListener((observable, anterior, actual) -> {
            if (actual != null) {
                cargarFormulario(actual);
            }
        });

        cargarDatos();
    }

    private void cargarDatos() {
        List<MateriaPrima> lista = service.listar();
        datos.setAll(lista);
    }

    private void cargarFormulario(MateriaPrima materia) {
        materiaSeleccionada = materia;
        campoNombre.setText(materia.getNombreMateria());
        campoStock.setText(String.valueOf(materia.getStockMateria()));
        comboUnidad.setValue(materia.getUnidadMedida());
    }

    @FXML
    private void guardar(ActionEvent evento) {
        String nombre = campoNombre.getText();
        String stock = campoStock.getText();
        String unidad = comboUnidad.getValue();

        String resultado;
        if (materiaSeleccionada == null) {
            resultado = service.agregar(nombre, stock, unidad);
        } else {
            resultado = service.actualizar(materiaSeleccionada.getIdMateriaPrima(), nombre, stock, unidad);
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
        if (materiaSeleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona una fila para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Eliminar");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Seguro que quieres eliminar " + materiaSeleccionada.getNombreMateria() + "?");

        confirmacion.showAndWait().ifPresent(botonPresionado -> {
            if (botonPresionado.getButtonData().isDefaultButton()) {
                String resultado = service.eliminar(materiaSeleccionada.getIdMateriaPrima());
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
        materiaSeleccionada = null;
        campoNombre.clear();
        campoStock.clear();
        comboUnidad.setValue(null);
        tablaMateriaPrima.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Materia Prima");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}