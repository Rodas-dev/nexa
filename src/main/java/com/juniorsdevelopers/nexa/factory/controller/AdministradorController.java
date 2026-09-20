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
}