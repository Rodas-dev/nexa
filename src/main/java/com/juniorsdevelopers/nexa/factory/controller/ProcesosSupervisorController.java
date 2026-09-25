package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.model.ProcesoOrden;
import com.juniorsdevelopers.nexa.factory.service.ServiceProcesoOrden;

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

public class ProcesosSupervisorController implements Initializable {

    @FXML
    private TableView<ProcesoOrden> tablaProcesos;

    @FXML
    private TableColumn<ProcesoOrden, Integer> colIdProceso;

    @FXML
    private TableColumn<ProcesoOrden, Integer> colIdOrden;

    @FXML
    private TableColumn<ProcesoOrden, String> colNombreProceso;

    @FXML
    private TableColumn<ProcesoOrden, String> colEstadoProceso;

    @FXML
    private TableColumn<ProcesoOrden, Integer> colSecuencia;

    @FXML
    private Label lblTotalProcesos;

    private final ServiceProcesoOrden serviceProcesoOrden =
            new ServiceProcesoOrden();

    private final ObservableList<ProcesoOrden> datos =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabla();

        cargarProcesos();
    }

    private void configurarTabla() {

        colIdProceso.setCellValueFactory(
                new PropertyValueFactory<>("idProceso")
        );

        colIdOrden.setCellValueFactory(
                new PropertyValueFactory<>("idOrden")
        );

        colNombreProceso.setCellValueFactory(
                new PropertyValueFactory<>("nombreProceso")
        );

        colEstadoProceso.setCellValueFactory(
                new PropertyValueFactory<>("estadoProceso")
        );

        colSecuencia.setCellValueFactory(
                new PropertyValueFactory<>("ordenSecuencia")
        );

        tablaProcesos.setItems(datos);
    }

    private void cargarProcesos() {

        List<ProcesoOrden> lista =
                serviceProcesoOrden.listar();

        datos.setAll(lista);

        lblTotalProcesos.setText(
                "Procesos encontrados: " + lista.size()
        );
    }

    @FXML
    private void cargarTodos() {

        cargarProcesos();
    }

    @FXML
    private void cargarActivos() {

        List<ProcesoOrden> lista =
                serviceProcesoOrden.listar();

        lista.removeIf(proceso ->
                proceso.getEstadoProceso().equalsIgnoreCase("Completado")
                || proceso.getEstadoProceso().equalsIgnoreCase("Cancelado")
        );

        datos.setAll(lista);

        lblTotalProcesos.setText(
                "Procesos activos: " + lista.size()
        );
    }
}