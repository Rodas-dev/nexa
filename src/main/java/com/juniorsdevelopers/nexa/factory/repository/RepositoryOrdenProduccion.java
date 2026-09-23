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

        String query =
                "SELECT " +
                "o.id_orden_produccion, " +
                "o.id_producto, " +
                "o.id_usuario, " +
                "p.nombre_producto, " +
                "u.nombre_usuario, " +
                "o.cantidad_orden, " +
                "COALESCE(SUM(ra.cantidad_producida), 0) AS cantidad_producida, " +
                "o.fecha_creacion, " +
                "o.estado_orden " +
                "FROM orden_produccion o " +
                "INNER JOIN producto p ON o.id_producto = p.id_producto " +
                "INNER JOIN usuario u ON o.id_usuario = u.id_usuario " +
                "LEFT JOIN registro_avance ra ON o.id_orden_produccion = ra.id_orden " +
                "GROUP BY " +
                "o.id_orden_produccion, " +
                "o.id_producto, " +
                "o.id_usuario, " +
                "p.nombre_producto, " +
                "u.nombre_usuario, " +
                "o.cantidad_orden, " +
                "o.fecha_creacion, " +
                "o.estado_orden " +
                "ORDER BY o.id_orden_produccion DESC";

        try (
                Connection conn = DataBaseConnection.getDataBaseConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(new OrdenProduccion(
                        rs.getInt("id_orden_produccion"),
                        rs.getInt("id_producto"),
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_producto"),
                        rs.getString("nombre_usuario"),
                        rs.getInt("cantidad_orden"),
                        rs.getInt("cantidad_producida"),
                        rs.getDate("fecha_creacion"),
                        rs.getString("estado_orden")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public int contarActivas() {

        String query =
                "SELECT COUNT(*) " +
                "FROM orden_produccion " +
                "WHERE estado_orden NOT IN ('Completada', 'Cancelada')";

        try (
                Connection conn = DataBaseConnection.getDataBaseConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    
    public int contarProcesosActivos() {

        String query =
                "SELECT COUNT(*) " +
                "FROM proceso_orden " +
                "WHERE estado_proceso NOT IN ('Completado', 'Cancelado')";

        try (
                Connection conn = DataBaseConnection.getDataBaseConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    
    public int contarProduccionHoy() {

        String query =
                "SELECT COALESCE(SUM(cantidad_producida), 0) " +
                "FROM registro_avance " +
                "WHERE fecha_registro = CURDATE()";

        try (
                Connection conn = DataBaseConnection.getDataBaseConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public List<OrdenProduccion> listarActivas() {

    List<OrdenProduccion> lista = new ArrayList<>();

    String query =
            "SELECT " +
            "o.id_orden_produccion, " +
            "o.id_producto, " +
            "o.id_usuario, " +
            "p.nombre_producto, " +
            "u.nombre_usuario, " +
            "o.cantidad_orden, " +
            "COALESCE(SUM(ra.cantidad_producida), 0) AS cantidad_producida, " +
            "o.fecha_creacion, " +
            "o.estado_orden " +
            "FROM orden_produccion o " +
            "INNER JOIN producto p ON o.id_producto = p.id_producto " +
            "INNER JOIN usuario u ON o.id_usuario = u.id_usuario " +
            "LEFT JOIN registro_avance ra " +
            "ON o.id_orden_produccion = ra.id_orden " +
            "WHERE o.estado_orden NOT IN ('Completada', 'Cancelada') " +
            "GROUP BY " +
            "o.id_orden_produccion, " +
            "o.id_producto, " +
            "o.id_usuario, " +
            "p.nombre_producto, " +
            "u.nombre_usuario, " +
            "o.cantidad_orden, " +
            "o.fecha_creacion, " +
            "o.estado_orden " +
            "ORDER BY o.id_orden_produccion DESC";

    try (
            Connection conn = DataBaseConnection.getDataBaseConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            lista.add(new OrdenProduccion(
                    rs.getInt("id_orden_produccion"),
                    rs.getInt("id_producto"),
                    rs.getInt("id_usuario"),
                    rs.getString("nombre_producto"),
                    rs.getString("nombre_usuario"),
                    rs.getInt("cantidad_orden"),
                    rs.getInt("cantidad_producida"),
                    rs.getDate("fecha_creacion"),
                    rs.getString("estado_orden")
            ));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
}