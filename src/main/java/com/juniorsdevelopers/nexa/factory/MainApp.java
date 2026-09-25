package com.juniorsdevelopers.nexa.factory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MainApp extends Application {

   @Override
public void start(Stage stagePrincipal) throws IOException {
    URL rutaVista = getClass().getResource("/view/login-view.fxml");
    if (rutaVista == null) {
        throw new IllegalStateException("No se encontró /view/login-view.fxml en el classpath.");
    }
    FXMLLoader cargador = new FXMLLoader(rutaVista);
    Scene escena = new Scene(cargador.load());
    stagePrincipal.setTitle("Control de Producción y Ensamblaje");
   
    stagePrincipal.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Logo-nexa.png"))));
    
    stagePrincipal.setScene(escena);
   stagePrincipal.setResizable(true); 
    stagePrincipal.show();
}

    public static void main(String[] args) {
        launch(args);
    }
}
