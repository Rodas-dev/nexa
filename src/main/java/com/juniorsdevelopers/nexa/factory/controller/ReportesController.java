package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.model.Reporte;
import com.juniorsdevelopers.nexa.factory.service.ServiceReportes;
import com.juniorsdevelopers.nexa.factory.util.GeneradorPDF;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReportesController implements Initializable {

    @FXML
    private ComboBox<String> cbTipoReporte;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private DatePicker dpFechaFin;

    @FXML
    private TableView<Reporte> tblReportes;

    @FXML
    private TableColumn<Reporte, String> colFecha;

    @FXML
    private TableColumn<Reporte, String> colDescripcion;

    @FXML
    private TableColumn<Reporte, Integer> colCantidad;

    @FXML
    private TableColumn<Reporte, String> colEstado;

    @FXML
    private Button btnGenerar;

    @FXML
    private Button btnExportar;

    @FXML
    private Button onRegresarClick;

    private ServiceReportes serviceReportes;
    private ObservableList<Reporte> listaReportes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.serviceReportes = new ServiceReportes();
        this.listaReportes = FXCollections.observableArrayList();

        cbTipoReporte.setItems(FXCollections.observableArrayList(
            "Productos",
            "Materia Prima",
            "Órdenes de Producción"
        ));

        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tblReportes.setItems(listaReportes);
    }

    @FXML
    private void generarReporte(ActionEvent event) {
        String tipoSeleccionado = cbTipoReporte.getValue();

        if (tipoSeleccionado == null || tipoSeleccionado.isEmpty()) {
            mostrarAlerta("Advertencia", "Selección Requerida", "Por favor seleccione un tipo de reporte.", Alert.AlertType.WARNING);
            return;
        }

        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaFin.getValue();

        List<Reporte> resultado = serviceReportes.generarReporte(tipoSeleccionado, fechaInicio, fechaFin);
        listaReportes.clear();
        listaReportes.addAll(resultado);

        if (listaReportes.isEmpty()) {
            mostrarAlerta("Información", "Sin Registros", "No se encontraron datos para el reporte seleccionado.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void exportarPDF(ActionEvent event) {
        if (listaReportes.isEmpty()) {
            mostrarAlerta("Advertencia", "Tabla Vacía", "No hay datos en la tabla para exportar a PDF.", Alert.AlertType.WARNING);
            return;
        }

        String tipoSeleccionado = cbTipoReporte.getValue();
        if (tipoSeleccionado == null) {
            tipoSeleccionado = "General";
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte PDF");
        fileChooser.setInitialFileName("Reporte_" + tipoSeleccionado.replace(" ", "_") + ".pdf");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF (*.pdf)", "*.pdf"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File archivo = fileChooser.showSaveDialog(stage);

        if (archivo != null) {
            boolean exito = GeneradorPDF.generarPdf(archivo, tipoSeleccionado, listaReportes);
            if (exito) {
                mostrarAlerta("Éxito", "PDF Generado", "El reporte en PDF se ha exportado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "Error de Exportación", "Ocurrió un error al intentar generar el archivo PDF.", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void regresarMenu(ActionEvent event) {
        try {
            URL rutaVista = getClass().getResource("/view/administrador-view.fxml");
            if (rutaVista == null) {
                System.out.println("No se encontró /view/administrador-view.fxml");
                return;
            }
            Parent vista = FXMLLoader.load(rutaVista);
            Node origen = (Node) event.getSource();
            Stage stage = (Stage) origen.getScene().getWindow();
            stage.setScene(new Scene(vista));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}