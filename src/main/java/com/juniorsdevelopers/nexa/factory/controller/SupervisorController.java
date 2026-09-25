package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.model.OrdenProduccion;
import com.juniorsdevelopers.nexa.factory.service.ServiceOrdenProduccion;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SupervisorController implements Initializable {

    @FXML
    private BorderPane raiz;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblOrdenes;

    @FXML
    private Label lblProcesos;

    @FXML
    private Label lblAvances;

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

    private final ServiceOrdenProduccion serviceOrdenProduccion =
            new ServiceOrdenProduccion();

    private final ObservableList<OrdenProduccion> datos =
            FXCollections.observableArrayList();

   
    private Node vistaInicio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        vistaInicio = raiz.getCenter();

        configurarTabla();
        cargarDashboard();
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

        tablaOrdenes.setItems(datos);
    }

    private void cargarDashboard() {

        cargarOrdenesActivas();

        int procesosActivos =
                serviceOrdenProduccion.contarProcesosActivos();

        int produccionHoy =
                serviceOrdenProduccion.contarProduccionHoy();

        lblProcesos.setText(
                String.valueOf(procesosActivos)
        );

        lblAvances.setText(
                String.valueOf(produccionHoy)
        );
    }

    private void cargarOrdenesActivas() {

        List<OrdenProduccion> lista =
                serviceOrdenProduccion.listarActivas();

        datos.setAll(lista);

        int ordenesActivas =
                serviceOrdenProduccion.contarActivas();

        lblOrdenes.setText(
                String.valueOf(ordenesActivas)
        );
    }



    @FXML
    private void mostrarInicio(ActionEvent evento) {

        
        raiz.setCenter(vistaInicio);

        
        cargarDashboard();

        System.out.println(
                "Inicio del Supervisor"
        );
    }



    @FXML
    private void mostrarOrdenes(ActionEvent evento) {

        URL rutaVista =
                getClass().getResource(
                        "/view/ordenes-supervisor-view.fxml"
                );

        if (rutaVista == null) {

            System.out.println(
                    "No se encontró /view/ordenes-supervisor-view.fxml"
            );

            return;
        }

        try {

            Parent vista =
                    FXMLLoader.load(rutaVista);

            raiz.setCenter(vista);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

 

    @FXML
    private void mostrarProcesos(ActionEvent evento) {

        URL rutaVista =
                getClass().getResource(
                        "/view/procesos-supervisor-view.fxml"
                );

        if (rutaVista == null) {

            System.out.println(
                    "No se encontró /view/procesos-supervisor-view.fxml"
            );

            return;
        }

        try {

            Parent vista =
                    FXMLLoader.load(rutaVista);

            raiz.setCenter(vista);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

 

    @FXML
    private void mostrarAvances(ActionEvent evento) {

        URL rutaVista =
                getClass().getResource(
                        "/view/avances-supervisor-view.fxml"
                );

        if (rutaVista == null) {

            System.out.println(
                    "No se encontró /view/avances-supervisor-view.fxml"
            );

            return;
        }

        try {

            Parent vista =
                    FXMLLoader.load(rutaVista);

            raiz.setCenter(vista);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    @FXML
    private void mostrarMateriaPrima(ActionEvent evento) {

        URL rutaVista =
                getClass().getResource(
                        "/view/materia-prima-view.fxml"
                );

        if (rutaVista == null) {

            System.out.println(
                    "No se encontró /view/materia-prima-view.fxml"
            );

            return;
        }

        try {

            Parent vista =
                    FXMLLoader.load(rutaVista);

            raiz.setCenter(vista);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

  

    @FXML
    private void mostrarReportes(ActionEvent evento) {

        URL rutaVista =
                getClass().getResource(
                        "/view/reportes-supervisor-view.fxml"
                );

        if (rutaVista == null) {

            System.out.println(
                    "No se encontró /view/reportes-supervisor-view.fxml"
            );

            return;
        }

        try {

            Parent vista =
                    FXMLLoader.load(rutaVista);

            raiz.setCenter(vista);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }



    @FXML
    private void cerrarSesion(ActionEvent evento) {

        try {

            URL rutaVista =
                    getClass().getResource(
                            "/view/login-view.fxml"
                    );

            if (rutaVista == null) {

                System.out.println(
                        "No se encontró /view/login-view.fxml"
                );

                return;
            }

            Parent vista =
                    FXMLLoader.load(rutaVista);

            Node origen =
                    (Node) evento.getSource();

            Stage stage =
                    (Stage) origen.getScene().getWindow();

            stage.setScene(
                    new Scene(vista)
            );

            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}