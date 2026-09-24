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

public class RegistroController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private TextField campoCorreo;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    private TextField campoContrasenaVisible;

    @FXML
    private ToggleButton botonMostrarContrasena;

    @FXML
    private ComboBox<String> comboRol;

    @FXML
    private TextField campoCodigoRol;

    @FXML
    private Button botonRegistrar;

    @FXML
    private Hyperlink enlaceIniciarSesion;

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
    private void registrar(ActionEvent evento) {
        String usuario = campoUsuario.getText();
        String correo = campoCorreo.getText();
        String contrasena = campoContrasena.getText();
        String rolSeleccionado = comboRol.getValue();
        String codigoRol = campoCodigoRol.getText();

        String resultado = service.registrarUsuario(
                usuario,
                correo,
                contrasena,
                rolSeleccionado,
                codigoRol
        );

        if (resultado.equals("OK")) {
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Usuario registrado correctamente."
            );

            // Pasamos también el nombre de usuario ingresado
            navegarSegunRol(
                    rolSeleccionado,
                    usuario,
                    evento
            );

        } else {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    resultado
            );
        }
    }

    @FXML
    private void irAIniciarSesion(ActionEvent evento) {
        try {
            URL rutaVista = getClass().getResource("/view/login-view.fxml");

            if (rutaVista == null) {
                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "No se encontró login-view.fxml."
                );
                return;
            }

            Parent vista = FXMLLoader.load(rutaVista);

            Node origen = (Node) evento.getSource();
            Stage stage = (Stage) origen.getScene().getWindow();

            stage.setScene(new Scene(vista));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "No se pudo abrir la ventana de inicio de sesión."
            );
        }
    }

   private void navegarSegunRol(
        String rol,
        String usuario,
        ActionEvent evento) {

    String ruta;

    switch (rol) {
        case "Administrador":
            ruta = "/view/administrador-view.fxml";
            break;

        case "Supervisor":
            ruta = "/view/supervisor-view.fxml";
            break;

        case "Almacén":
            ruta = "/view/almacen-view.fxml"; // Asegúrate de que coincida con el nombre exacto de tu FXML
            break;

        case "Operario":
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Registro exitoso. El panel para Operario aún no está disponible."
            );
            irAIniciarSesion(evento);
            return;

        default:
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Rol no reconocido."
            );
            irAIniciarSesion(evento);
            return;
    }

    URL rutaVista = getClass().getResource(ruta);

    if (rutaVista == null) {
        mostrarAlerta(
                Alert.AlertType.WARNING,
                "No se encontró la vista: " + ruta
        );
        return;
    }

    try {
        FXMLLoader loader = new FXMLLoader(rutaVista);
        Parent vista = loader.load();

        if ("Administrador".equals(rol)) {
            AdministradorController adminController = loader.getController();
            adminController.setNombreUsuario(usuario);
        } 

        Node origen = (Node) evento.getSource();
        Stage stage = (Stage) origen.getScene().getWindow();

        stage.setScene(new Scene(vista));
        stage.show();

    } catch (IOException e) {
        e.printStackTrace();
        mostrarAlerta(
                Alert.AlertType.WARNING,
                "Error al cargar la vista del rol "
                        + rol
                        + ": "
                        + e.getMessage()
        );
    }
}

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String mensaje) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle("Registro");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}