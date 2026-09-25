package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.model.RegistroAvance;
import com.juniorsdevelopers.nexa.factory.service.ServiceRegistroAvance;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AvancesSupervisorController implements Initializable {

    @FXML
    private TableView<RegistroAvance> tablaAvances;

    @FXML
    private TableColumn<RegistroAvance, Integer> colIdAvance;

    @FXML
    private TableColumn<RegistroAvance, Integer> colIdOrden;

    @FXML
    private TableColumn<RegistroAvance, String> colUsuario;

    @FXML
    private TableColumn<RegistroAvance, Integer> colCantidad;

    @FXML
    private TableColumn<RegistroAvance, Date> colFecha;

    @FXML
    private Label lblTotalAvances;

    @FXML
    private TextField txtIdOrden;

    @FXML
    private TextField txtIdUsuario;

    @FXML
    private TextField txtCantidad;

    @FXML
    private Label lblMensaje;

    private final ServiceRegistroAvance serviceRegistroAvance =
            new ServiceRegistroAvance();

    private final ObservableList<RegistroAvance> datos =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabla();

        cargarAvances();
    }

    private void configurarTabla() {

        colIdAvance.setCellValueFactory(
                new PropertyValueFactory<>("idAvance")
        );

        colIdOrden.setCellValueFactory(
                new PropertyValueFactory<>("idOrden")
        );

        colUsuario.setCellValueFactory(
                new PropertyValueFactory<>("nombreUsuario")
        );

        colCantidad.setCellValueFactory(
                new PropertyValueFactory<>("cantidadProducida")
        );

        colFecha.setCellValueFactory(
                new PropertyValueFactory<>("fechaRegistro")
        );

        tablaAvances.setItems(datos);
    }

    private void cargarAvances() {

        List<RegistroAvance> lista =
                serviceRegistroAvance.listar();

        datos.setAll(lista);

        lblTotalAvances.setText(
                "Avances encontrados: " + lista.size()
        );
    }

    @FXML
    private void cargarTodos() {

        cargarAvances();

        lblMensaje.setText("");
    }

    @FXML
    private void cargarHoy() {

        List<RegistroAvance> lista =
                serviceRegistroAvance.listar();

        Date hoy = new Date(System.currentTimeMillis());

        lista.removeIf(avance ->
                avance.getFechaRegistro() == null
                || !avance.getFechaRegistro().equals(hoy)
        );

        datos.setAll(lista);

        int produccionHoy =
                serviceRegistroAvance.contarProduccionHoy();

        lblTotalAvances.setText(
                "Producción de hoy: " + produccionHoy
        );
    }

    @FXML
    private void registrarAvance() {

        try {

            if (txtIdOrden.getText().trim().isEmpty()
                    || txtIdUsuario.getText().trim().isEmpty()
                    || txtCantidad.getText().trim().isEmpty()) {

                lblMensaje.setText(
                        "Complete todos los campos."
                );

                return;
            }

            int idOrden =
                    Integer.parseInt(
                            txtIdOrden.getText().trim()
                    );

            int idUsuario =
                    Integer.parseInt(
                            txtIdUsuario.getText().trim()
                    );

            int cantidad =
                    Integer.parseInt(
                            txtCantidad.getText().trim()
                    );

            if (idOrden <= 0
                    || idUsuario <= 0
                    || cantidad <= 0) {

                lblMensaje.setText(
                        "Los valores deben ser mayores que 0."
                );

                return;
            }

            Date fechaActual =
                    new Date(System.currentTimeMillis());

            RegistroAvance avance =
                    new RegistroAvance(
                            idOrden,
                            idUsuario,
                            cantidad,
                            fechaActual
                    );

            boolean registrado =
                    serviceRegistroAvance.registrar(avance);

            if (registrado) {

                lblMensaje.setText(
                        "Avance registrado correctamente."
                );

                txtIdOrden.clear();
                txtIdUsuario.clear();
                txtCantidad.clear();

                cargarAvances();

            } else {

                lblMensaje.setText(
                        "No se pudo registrar el avance."
                );
            }

        } catch (NumberFormatException e) {

            lblMensaje.setText(
                    "Ingrese solamente números válidos."
            );
        }
    }
}