package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class LoginController {

    @FXML
    private ComboBox<String> comboRol;

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    private TextField campoContrasenaVisible;

    @FXML
    private ToggleButton botonMostrarContrasena;

    @FXML
    private Button botonIniciarSesion;

    @FXML
    private Hyperlink enlaceRegistro;

    private final Service service = new Service();

    @FXML
    public void initialize() {

        comboRol.getItems().addAll(
                List.of(
                        "Administrador",
                        "Supervisor",
                        "Operario",
                        "Almacén"
                )
        );

        campoContrasenaVisible.setVisible(false);
        campoContrasenaVisible.setManaged(false);

        campoContrasenaVisible.textProperty()
                .bindBidirectional(campoContrasena.textProperty());
    }

    @FXML
    private void alternarVisibilidadContrasena(ActionEvent evento) {

        boolean mostrar = botonMostrarContrasena.isSelected();

        campoContrasenaVisible.setVisible(mostrar);
        campoContrasenaVisible.setManaged(mostrar);

        campoContrasena.setVisible(!mostrar);
        campoContrasena.setManaged(!mostrar);
    }

    @FXML
    private void iniciarSesion(ActionEvent evento) {

        String rolSeleccionado = comboRol.getValue();
        String usuario = campoUsuario.getText();
        String contrasena = campoContrasena.getText();

        if (rolSeleccionado == null
                || usuario.isBlank()
                || contrasena.isBlank()) {

            mostrarAlerta(
                    "Debes completar todos los campos antes de iniciar sesión."
            );

            return;
        }

        boolean credencialesValidas =
                service.iniciarSesion(
                        usuario,
                        contrasena,
                        rolSeleccionado
                );

        if (!credencialesValidas) {

            mostrarAlerta(
                    "Usuario, contraseña o rol incorrectos."
            );

            return;
        }

        navegarSegunRol(rolSeleccionado, evento);
    }

    @FXML
    private void irARegistro(ActionEvent evento) {

        try {

            URL rutaVista =
                    getClass().getResource("/view/registro-view.fxml");

            if (rutaVista == null) {

                mostrarAlerta(
                        "No se encontró registro-view.fxml."
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

            mostrarAlerta(
                    "No se pudo abrir la ventana de registro."
            );
        }
    }

    private void navegarSegunRol(
            String rol,
            ActionEvent evento) {

        String ruta;

        switch (rol) {

            case "Administrador":

                ruta = "/view/administrador-view.fxml";

                break;

            case "Supervisor":

                ruta = "/view/supervisor-view.fxml";

                break;

            case "Operario":

                mostrarAlerta(
                        "El panel para Operario aún no está disponible."
                );

                return;

            case "Almacén":

                mostrarAlerta(
                        "El panel para Almacén aún no está disponible."
                );

                return;

            default:

                mostrarAlerta(
                        "Rol no reconocido."
                );

                return;
        }

        URL rutaVista =
                getClass().getResource(ruta);

        if (rutaVista == null) {

            mostrarAlerta(
                    "No se encontró la vista: " + ruta
            );

            return;
        }

        try {

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

            mostrarAlerta(
                    "Error al cargar la vista del rol "
                    + rol
                    + ": "
                    + e.getMessage()
            );
        }
    }

    private void mostrarAlerta(String mensaje) {

        Alert alerta =
                new Alert(Alert.AlertType.WARNING);

        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}