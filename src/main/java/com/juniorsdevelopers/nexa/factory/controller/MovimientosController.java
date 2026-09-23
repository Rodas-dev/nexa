package com.juniorsdevelopers.nexa.factory.controller;

import com.juniorsdevelopers.nexa.factory.config.DataBaseConnection;
import com.juniorsdevelopers.nexa.factory.model.Movimiento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MovimientosController implements Initializable {

    @FXML
    private TableView<Movimiento> tablaMovimientos;
    @FXML
    private TableColumn<Movimiento, Integer> colId;
    @FXML
    private TableColumn<Movimiento, String> colTipo;
    @FXML
    private TableColumn<Movimiento, String> colProducto;
    @FXML
    private TableColumn<Movimiento, Integer> colCantidad;
    @FXML
    private TableColumn<Movimiento, String> colFecha;
    @FXML
    private TableColumn<Movimiento, String> colUsuario;

    private ObservableList<Movimiento> listaMovimientos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        listaMovimientos = FXCollections.observableArrayList();
        cargarDatosMovimientos();
    }

    private void cargarDatosMovimientos() {
        String sql = "SELECT m.id_movimiento_inventario AS id, m.tipo_movimiento AS tipo, "
                   + "mp.nombre_materia AS producto, m.cantidad AS cantidad, "
                   + "m.fecha_movimiento AS fecha, u.nombre_usuario AS usuario "
                   + "FROM movimiento_inventario m "
                   + "INNER JOIN materia_prima mp ON m.id_materia = mp.id_materia_prima "
                   + "INNER JOIN usuario u ON m.id_usuario = u.id_usuario;";

        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                listaMovimientos.add(new Movimiento(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("producto"),
                        rs.getInt("cantidad"),
                        rs.getString("fecha"),
                        rs.getString("usuario")
                ));
            }

            tablaMovimientos.setItems(listaMovimientos);

        } catch (SQLException e) {
            System.out.println("Error al cargar los movimientos de inventario: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

