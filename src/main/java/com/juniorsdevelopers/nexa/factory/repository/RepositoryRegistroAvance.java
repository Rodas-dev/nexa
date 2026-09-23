package com.juniorsdevelopers.nexa.factory.repository;

import com.juniorsdevelopers.nexa.factory.config.DataBaseConnection;
import com.juniorsdevelopers.nexa.factory.model.RegistroAvance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryRegistroAvance {

    public List<RegistroAvance> listar() {

        List<RegistroAvance> lista = new ArrayList<>();

        String query =
                "SELECT " +
                "ra.id_avance, " +
                "ra.id_orden, " +
                "ra.id_usuario, " +
                "u.nombre_usuario, " +
                "ra.cantidad_producida, " +
                "ra.fecha_registro " +
                "FROM registro_avance ra " +
                "INNER JOIN usuario u " +
                "ON ra.id_usuario = u.id_usuario " +
                "ORDER BY ra.fecha_registro DESC, ra.id_avance DESC";

        try (
                Connection conn = DataBaseConnection.getDataBaseConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(
                        new RegistroAvance(
                                rs.getInt("id_avance"),
                                rs.getInt("id_orden"),
                                rs.getInt("id_usuario"),
                                rs.getString("nombre_usuario"),
                                rs.getInt("cantidad_producida"),
                                rs.getDate("fecha_registro")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<RegistroAvance> listarPorOrden(int idOrden) {

        List<RegistroAvance> lista = new ArrayList<>();

        String query =
                "SELECT " +
                "ra.id_avance, " +
                "ra.id_orden, " +
                "ra.id_usuario, " +
                "u.nombre_usuario, " +
                "ra.cantidad_producida, " +
                "ra.fecha_registro " +
                "FROM registro_avance ra " +
                "INNER JOIN usuario u " +
                "ON ra.id_usuario = u.id_usuario " +
                "WHERE ra.id_orden = ? " +
                "ORDER BY ra.fecha_registro DESC, ra.id_avance DESC";

        try (
                Connection conn = DataBaseConnection.getDataBaseConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idOrden);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    lista.add(
                            new RegistroAvance(
                                    rs.getInt("id_avance"),
                                    rs.getInt("id_orden"),
                                    rs.getInt("id_usuario"),
                                    rs.getString("nombre_usuario"),
                                    rs.getInt("cantidad_producida"),
                                    rs.getDate("fecha_registro")
                            )
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
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
    public boolean registrar(RegistroAvance avance) {

    String query =
            "INSERT INTO registro_avance " +
            "(id_orden, id_usuario, cantidad_producida, fecha_registro) " +
            "VALUES (?, ?, ?, ?)";

    try (
            Connection conn = DataBaseConnection.getDataBaseConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, avance.getIdOrden());
        stmt.setInt(2, avance.getIdUsuario());
        stmt.setInt(3, avance.getCantidadProducida());
        stmt.setDate(4, avance.getFechaRegistro());

        stmt.executeUpdate();

        return true;

    } catch (SQLException e) {

        e.printStackTrace();

        return false;
    }
}
}
