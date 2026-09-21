package com.juniorsdevelopers.nexa.factory.repository;

import com.juniorsdevelopers.nexa.factory.config.DataBaseConnection;
import com.juniorsdevelopers.nexa.factory.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryProducto {

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String query = "SELECT id_producto, nombre_producto, descripcion_producto, stock_producto, estado_producto FROM producto ORDER BY nombre_producto";

        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("descripcion_producto"),
                    rs.getInt("stock_producto"),
                    rs.getString("estado_producto")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean agregar(Producto producto) {
        String query = "INSERT INTO producto (nombre_producto, descripcion_producto, stock_producto, estado_producto) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, producto.getNombreProducto());
            stmt.setString(2, producto.getDescripcionProducto());
            stmt.setInt(3, producto.getStockProducto());
            stmt.setString(4, producto.getEstadoProducto());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Producto producto) {
        String query = "UPDATE producto SET nombre_producto = ?, descripcion_producto = ?, stock_producto = ?, estado_producto = ? WHERE id_producto = ?";

        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, producto.getNombreProducto());
            stmt.setString(2, producto.getDescripcionProducto());
            stmt.setInt(3, producto.getStockProducto());
            stmt.setString(4, producto.getEstadoProducto());
            stmt.setInt(5, producto.getIdProducto());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idProducto) {
        String query = "DELETE FROM producto WHERE id_producto = ?";

        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idProducto);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}