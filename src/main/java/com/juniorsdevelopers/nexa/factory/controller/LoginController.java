package com.juniorsdevelopers.nexa.factory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

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

    @FXML
    public void initialize() {
        comboRol.getItems().addAll(List.of("Administrador", "Supervisor", "Operario", "Almacén"));
        campoContrasenaVisible.setVisible(false);
        campoContrasenaVisible.setManaged(false);
        campoContrasenaVisible.textProperty().bindBidirectional(campoContrasena.textProperty());
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

        if (rolSeleccionado == null || usuario.isBlank() || contrasena.isBlank()) {
            mostrarAlerta("Debes completar todos los campos antes de iniciar sesión.");
            return;
        }

        System.out.println("Rol: " + rolSeleccionado + " Usuario: " + usuario);
    }

    @FXML
    private void irARegistro(ActionEvent evento) {
        System.out.println("Ir a pantalla de registro");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}