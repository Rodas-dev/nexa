package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.model.OrdenProduccion;
import com.juniorsdevelopers.nexa.factory.service.ServiceOrdenProduccion;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdenesSupervisorController implements Initializable {

    @FXML
    private TableView<OrdenProduccion> tablaOrdenes;

    @FXML
    private TableColumn<OrdenProduccion, Integer> colIdOrden;

    @FXML
    private TableColumn<OrdenProduccion, String> colProducto;

    @FXML
    private TableColumn<OrdenProduccion, Integer> colCantidad;

    @FXML
    private TableColumn<OrdenProduccion, Integer> colAvance;

    @FXML
    private TableColumn<OrdenProduccion, String> colEstado;

    @FXML
    private TableColumn<OrdenProduccion, java.sql.Date> colFecha;

    @FXML
    private Label lblTotalOrdenes;

    private final ServiceOrdenProduccion serviceOrdenProduccion =
            new ServiceOrdenProduccion();

    private final ObservableList<OrdenProduccion> datos =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabla();

        cargarOrdenes();
    }

    private void configurarTabla() {

        colIdOrden.setCellValueFactory(
                new PropertyValueFactory<>("idOrdenProduccion")
        );

        colProducto.setCellValueFactory(
                new PropertyValueFactory<>("nombreProducto")
        );

        colCantidad.setCellValueFactory(
                new PropertyValueFactory<>("cantidadOrden")
        );

        colAvance.setCellValueFactory(
                new PropertyValueFactory<>("cantidadProducida")
        );

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("estadoOrden")
        );

        colFecha.setCellValueFactory(
                new PropertyValueFactory<>("fechaCreacion")
        );

        tablaOrdenes.setItems(datos);
    }

    private void cargarOrdenes() {

        List<OrdenProduccion> lista =
                serviceOrdenProduccion.listar();

        datos.setAll(lista);

        lblTotalOrdenes.setText(
                "Órdenes encontradas: " + lista.size()
        );
    }

    @FXML
    private void cargarTodas() {

        cargarOrdenes();
    }

    @FXML
    private void cargarActivas() {

        List<OrdenProduccion> lista =
                serviceOrdenProduccion.listarActivas();

        datos.setAll(lista);

        lblTotalOrdenes.setText(
                "Órdenes activas: " + lista.size()
        );
    }
}