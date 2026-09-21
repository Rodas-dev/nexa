package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.model.Producto;
import com.juniorsdevelopers.nexa.factory.repository.RepositoryProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

public class ProductoController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtStock;

    @FXML
    private ComboBox<String> cmbEstado;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, Integer> colId;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colDescripcion;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private TableColumn<Producto, String> colEstado;

    private final RepositoryProducto repositoryProducto = new RepositoryProducto();
    private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    private int idProductoSeleccionado = -1;

    @FXML
    public void initialize() {
        cmbEstado.setItems(FXCollections.observableArrayList(
                "Disponible",
                "Bajo stock",
                "Agotado"
        ));

        colId.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionProducto"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stockProducto"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoProducto"));

        tablaProductos.setItems(listaProductos);

        cargarProductos();

        tablaProductos.getSelectionModel().selectedItemProperty().addListener(
                (observable, anterior, productoSeleccionado) -> {
                    if (productoSeleccionado != null) {
                        cargarDatosProducto(productoSeleccionado);
                    }
                }
        );
    }

    private void cargarProductos() {
        listaProductos.setAll(repositoryProducto.listar());
    }

    private void cargarDatosProducto(Producto producto) {
        idProductoSeleccionado = producto.getIdProducto();
        txtNombre.setText(producto.getNombreProducto());
        txtDescripcion.setText(producto.getDescripcionProducto());
        txtStock.setText(String.valueOf(producto.getStockProducto()));
        cmbEstado.setValue(producto.getEstadoProducto());
    }

    @FXML
    private void registrarProducto(ActionEvent evento) {
        if (!validarCampos()) {
            return;
        }

        int stock;

        try {
            stock = Integer.parseInt(txtStock.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "El stock debe ser un número entero.");
            return;
        }

        if (stock < 0) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "El stock no puede ser negativo.");
            return;
        }

        String descripcion = txtDescripcion.getText().trim();

        Producto producto = new Producto(
                txtNombre.getText().trim(),
                descripcion.isEmpty() ? null : descripcion,
                stock,
                cmbEstado.getValue()
        );

        if (repositoryProducto.agregar(producto)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Producto registrado", "El producto se registró correctamente.");
            cargarProductos();
            limpiarCampos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar el producto.");
        }
    }

    @FXML
    private void editarProducto(ActionEvent evento) {
        if (idProductoSeleccionado == -1) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona un producto", "Selecciona un producto de la tabla para editarlo.");
            return;
        }

        if (!validarCampos()) {
            return;
        }

        int stock;

        try {
            stock = Integer.parseInt(txtStock.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "El stock debe ser un número entero.");
            return;
        }

        if (stock < 0) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "El stock no puede ser negativo.");
            return;
        }

        String descripcion = txtDescripcion.getText().trim();

        Producto producto = new Producto(
                idProductoSeleccionado,
                txtNombre.getText().trim(),
                descripcion.isEmpty() ? null : descripcion,
                stock,
                cmbEstado.getValue()
        );

        if (repositoryProducto.actualizar(producto)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Producto actualizado", "El producto se actualizó correctamente.");
            cargarProductos();
            limpiarCampos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el producto.");
        }
    }

    @FXML
    private void eliminarProducto(ActionEvent evento) {
        if (idProductoSeleccionado == -1) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona un producto", "Selecciona un producto de la tabla para eliminarlo.");
            return;
        }

        Alert confirmacion = new Alert(
                Alert.AlertType.CONFIRMATION,
                "¿Deseas eliminar el producto seleccionado?",
                ButtonType.YES,
                ButtonType.NO
        );

        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);

        Optional<ButtonType> respuesta = confirmacion.showAndWait();

        if (respuesta.isPresent() && respuesta.get() == ButtonType.YES) {
            if (repositoryProducto.eliminar(idProductoSeleccionado)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Producto eliminado", "El producto se eliminó correctamente.");
                cargarProductos();
                limpiarCampos();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el producto.");
            }
        }
    }

    @FXML
    private void limpiarCampos(ActionEvent evento) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        idProductoSeleccionado = -1;
        txtNombre.clear();
        txtDescripcion.clear();
        txtStock.clear();
        cmbEstado.getSelectionModel().clearSelection();
        tablaProductos.getSelectionModel().clearSelection();
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo requerido", "Ingresa el nombre del producto.");
            txtNombre.requestFocus();
            return false;
        }

        if (txtStock.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo requerido", "Ingresa el stock del producto.");
            txtStock.requestFocus();
            return false;
        }

        if (cmbEstado.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo requerido", "Selecciona el estado del producto.");
            cmbEstado.requestFocus();
            return false;
        }

        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void regresarMenu(ActionEvent evento) {
        try {
            Parent vista = FXMLLoader.load(
                    getClass().getResource("/view/administrador-view.fxml")
            );

            txtNombre.getScene().setRoot(vista);

        } catch (IOException | NullPointerException e) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No se pudo regresar al menú."
            );
        }
    }
}