package com.juniorsdevelopers.nexa.factory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdministradorController implements Initializable {

    @FXML
    private BorderPane raiz;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void mostrarProducto(ActionEvent evento) {
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
    private void mostrarOrdenes(ActionEvent evento) {
        URL rutaVista = getClass().getResource("/view/ordenes-produccion-view.fxml");

        if (rutaVista == null) {
            System.out.println("No se encontró /view/ordenes-produccion-view.fxml en el classpath.");
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
    private void mostrarReportes(ActionEvent evento) {
        URL rutaVista = getClass().getResource("/view/reportes-view.fxml");

        if (rutaVista == null) {
            System.out.println("No se encontró /view/reportes-view.fxml en el classpath.");
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