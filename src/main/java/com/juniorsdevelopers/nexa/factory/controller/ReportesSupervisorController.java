package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.model.ReporteSupervisor;
import com.juniorsdevelopers.nexa.factory.service.ServiceReporteSupervisor;

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

public class ReportesSupervisorController implements Initializable {

    @FXML
    private TableView<ReporteSupervisor> tablaReporte;

    @FXML
    private TableColumn<ReporteSupervisor, Integer> colIdOrden;

    @FXML
    private TableColumn<ReporteSupervisor, String> colProducto;

    @FXML
    private TableColumn<ReporteSupervisor, Integer> colCantidadOrden;

    @FXML
    private TableColumn<ReporteSupervisor, Integer> colCantidadProducida;

    @FXML
    private TableColumn<ReporteSupervisor, String> colEstado;

    @FXML
    private Label lblTotal;

    private final ServiceReporteSupervisor serviceReporteSupervisor =
            new ServiceReporteSupervisor();

    private final ObservableList<ReporteSupervisor> datos =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabla();

        cargarReporte();
    }

    private void configurarTabla() {

        colIdOrden.setCellValueFactory(
                new PropertyValueFactory<>("idOrden")
        );

        colProducto.setCellValueFactory(
                new PropertyValueFactory<>("producto")
        );

        colCantidadOrden.setCellValueFactory(
                new PropertyValueFactory<>("cantidadOrden")
        );

        colCantidadProducida.setCellValueFactory(
                new PropertyValueFactory<>("cantidadProducida")
        );

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado")
        );

        tablaReporte.setItems(datos);
    }

    private void cargarReporte() {

        List<ReporteSupervisor> lista =
                serviceReporteSupervisor.listar();

        datos.setAll(lista);

        lblTotal.setText(
                "Órdenes en el reporte: " + lista.size()
        );
    }

    @FXML
    private void actualizarReporte() {

        cargarReporte();
    }
}