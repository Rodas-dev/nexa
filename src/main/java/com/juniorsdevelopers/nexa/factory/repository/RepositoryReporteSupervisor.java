package com.juniorsdevelopers.nexa.factory.repository;

import com.juniorsdevelopers.nexa.factory.config.DataBaseConnection;
import com.juniorsdevelopers.nexa.factory.model.ReporteSupervisor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryReporteSupervisor {

    public List<ReporteSupervisor> listar() {

        List<ReporteSupervisor> lista = new ArrayList<>();

        String query =
                "SELECT " +
                "o.id_orden_produccion, " +
                "p.nombre_producto, " +
                "o.cantidad_orden, " +
                "COALESCE(SUM(ra.cantidad_producida), 0) AS cantidad_producida, " +
                "o.estado_orden " +
                "FROM orden_produccion o " +
                "INNER JOIN producto p " +
                "ON o.id_producto = p.id_producto " +
                "LEFT JOIN registro_avance ra " +
                "ON o.id_orden_produccion = ra.id_orden " +
                "GROUP BY " +
                "o.id_orden_produccion, " +
                "p.nombre_producto, " +
                "o.cantidad_orden, " +
                "o.estado_orden " +
                "ORDER BY o.id_orden_produccion DESC";

        try (
                Connection conn =
                        DataBaseConnection.getDataBaseConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(query);

                ResultSet rs =
                        stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(
                        new ReporteSupervisor(
                                rs.getInt("id_orden_produccion"),
                                rs.getString("nombre_producto"),
                                rs.getInt("cantidad_orden"),
                                rs.getInt("cantidad_producida"),
                                rs.getString("estado_orden")
                        )
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }
}