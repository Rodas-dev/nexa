package com.juniorsdevelopers.nexa.factory.repository;

import com.juniorsdevelopers.nexa.factory.config.DataBaseConnection;
import com.juniorsdevelopers.nexa.factory.model.ProcesoOrden;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryProcesoOrden {

    public List<ProcesoOrden> listar() {

        List<ProcesoOrden> lista = new ArrayList<>();

        String query =
                "SELECT " +
                "id_proceso, " +
                "id_orden, " +
                "nombre_proceso, " +
                "estado_proceso, " +
                "orden_secuencia " +
                "FROM proceso_orden " +
                "ORDER BY id_orden, orden_secuencia";

        try (
                Connection conn =
                        DataBaseConnection.getDataBaseConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(query);

                ResultSet rs =
                        stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(
                        new ProcesoOrden(
                                rs.getInt("id_proceso"),
                                rs.getInt("id_orden"),
                                rs.getString("nombre_proceso"),
                                rs.getString("estado_proceso"),
                                rs.getInt("orden_secuencia")
                        )
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    public List<ProcesoOrden> listarPorOrden(int idOrden) {

        List<ProcesoOrden> lista = new ArrayList<>();

        String query =
                "SELECT " +
                "id_proceso, " +
                "id_orden, " +
                "nombre_proceso, " +
                "estado_proceso, " +
                "orden_secuencia " +
                "FROM proceso_orden " +
                "WHERE id_orden = ? " +
                "ORDER BY orden_secuencia";

        try (
                Connection conn =
                        DataBaseConnection.getDataBaseConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(query)) {

            stmt.setInt(1, idOrden);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    lista.add(
                            new ProcesoOrden(
                                    rs.getInt("id_proceso"),
                                    rs.getInt("id_orden"),
                                    rs.getString("nombre_proceso"),
                                    rs.getString("estado_proceso"),
                                    rs.getInt("orden_secuencia")
                            )
                    );
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    public int contarActivos() {

        String query =
                "SELECT COUNT(*) " +
                "FROM proceso_orden " +
                "WHERE estado_proceso NOT IN " +
                "('Completado', 'Cancelado')";

        try (
                Connection conn =
                        DataBaseConnection.getDataBaseConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(query);

                ResultSet rs =
                        stmt.executeQuery()) {

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }
}