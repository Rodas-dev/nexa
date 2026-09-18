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
        comboRol.getItems().addAll(List.of(
            "Administrador",
            "Supervisor de Producción",
            "Operario",
            "Inspector de Calidad"
        ));

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

        boolean acceso = service.iniciarSesion(
            usuario,
            contrasena,
            rolSeleccionado
        );

        if (acceso) {
            if (rolSeleccionado.equals("Administrador")) {
                try {
                    FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/view/administrador-view.fxml")
                    );

                    Parent vistaAdministrador = loader.load();

                    Stage ventana = (Stage) ((Node) evento.getSource())
                        .getScene()
                        .getWindow();

                    Scene escena = new Scene(vistaAdministrador);

                    ventana.setScene(escena);
                    ventana.setTitle("Nexa - Administrador");
                    ventana.show();

                } catch (IOException e) {
                    e.printStackTrace();
                    mostrarAlerta("No se pudo cargar la pantalla de administrador.");
                }
            } else {
                mostrarAlerta(
                    "Inicio de sesión correcto, pero esta vista todavía no está configurada para el rol: "
                    + rolSeleccionado
                );
            }
        } else {
            mostrarAlerta("Usuario, contraseña o rol incorrectos.");
        }
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
