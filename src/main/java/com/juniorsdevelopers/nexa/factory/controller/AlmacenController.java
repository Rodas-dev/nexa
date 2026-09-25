package com.juniorsdevelopers.nexa.factory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlmacenController implements Initializable {

    @FXML
    private BorderPane raiz;
    private Node vistaInicio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vistaInicio = raiz.getCenter();
    }

    @FXML
    private void mostrarInicio(ActionEvent evento) {
        if (vistaInicio != null) {
            raiz.setCenter(vistaInicio);
        }
    }

    @FXML
    private void mostrarMateriaPrima(ActionEvent evento) {
        URL rutaVista = getClass().getResource("/view/materia-prima-view.fxml");
        if (rutaVista == null) {
            System.out.println("No se encontró /view/materia-prima-view.fxml en el classpath.");
            return;
        }
        try {
            Parent vista = FXMLLoader.load(rutaVista);
            raiz.setCenter(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarInventario(ActionEvent evento) {
        URL rutaVista = getClass().getResource("/view/Producto-view.fxml");
        if (rutaVista == null) {
            System.out.println("No se encontró /view/Producto-view.fxml en el classpath.");
            return;
        }
        try {
            Parent vista = FXMLLoader.load(rutaVista);
            raiz.setCenter(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarMovimientos(ActionEvent evento) {
        URL rutaVista = getClass().getResource("/view/movimientos-view.fxml");
        if (rutaVista == null) {
            System.out.println("No se encontró /view/movimientos-view.fxml en el classpath. (Pendiente por crear)");
            return;
        }
        try {
            Parent vista = FXMLLoader.load(rutaVista);
            raiz.setCenter(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent evento) {
        try {
            URL rutaVista = getClass().getResource("/view/login-view.fxml");
            Parent vista = FXMLLoader.load(rutaVista);
            Node origen = (Node) evento.getSource();
            Stage stage = (Stage) origen.getScene().getWindow();
            stage.setScene(new Scene(vista));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}