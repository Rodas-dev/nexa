package com.juniorsdevelopers.nexa.factory.repository;

import com.juniorsdevelopers.nexa.factory.config.DataBaseConnection;
import com.juniorsdevelopers.nexa.factory.model.OrdenProduccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryOrdenProduccion {

    public List<OrdenProduccion> listar() {
        List<OrdenProduccion> lista = new ArrayList<>();
        String query = "SELECT id_orden_produccion, id_producto, id_usuario, cantidad_orden, fecha_creacion, estado_orden FROM orden_produccion ORDER BY id_orden_produccion DESC";

        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new OrdenProduccion(
                    rs.getInt("id_orden_produccion"),
                    rs.getInt("id_producto"),
                    rs.getInt("id_usuario"),
                    rs.getInt("cantidad_orden"),
                    rs.getDate("fecha_creacion"),
                    rs.getString("estado_orden")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(OrdenProduccion orden) {
        String query = "INSERT INTO orden_produccion (id_producto, id_usuario, cantidad_orden, fecha_creacion, estado_orden) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orden.getIdProducto());
            stmt.setInt(2, orden.getIdUsuario());
            stmt.setInt(3, orden.getCantidadOrden());
            stmt.setDate(4, orden.getFechaCreacion());
            stmt.setString(5, orden.getEstadoOrden());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(OrdenProduccion orden) {

        String query = "UPDATE orden_produccion SET id_producto = ?, id_usuario = ?, cantidad_orden = ?, estado_orden = ? WHERE id_orden_produccion = ?";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orden.getIdProducto());
            stmt.setInt(2, orden.getIdUsuario());
            stmt.setInt(3, orden.getCantidadOrden());
            stmt.setString(4, orden.getEstadoOrden());
            stmt.setInt(5, orden.getIdOrdenProduccion());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idOrden) {
        String query = "DELETE FROM orden_produccion WHERE id_orden_produccion = ?";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idOrden);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
