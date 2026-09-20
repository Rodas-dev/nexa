package com.juniorsdevelopers.nexa.factory.repository;

import com.juniorsdevelopers.nexa.factory.config.DataBaseConnection;
import com.juniorsdevelopers.nexa.factory.model.MateriaPrima;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryMateriaPrima {

    public List<MateriaPrima> listar() {
        List<MateriaPrima> lista = new ArrayList<>();
        String query = "SELECT id_materia_prima, nombre_materia, stock_materia, unidad_medida, estado_materia FROM materia_prima ORDER BY nombre_materia";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new MateriaPrima(
                    rs.getInt("id_materia_prima"),
                    rs.getString("nombre_materia"),
                    rs.getInt("stock_materia"),
                    rs.getString("unidad_medida"),
                    rs.getString("estado_materia")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(MateriaPrima materia) {
        String query = "INSERT INTO materia_prima (nombre_materia, stock_materia, unidad_medida, estado_materia) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, materia.getNombreMateria());
            stmt.setInt(2, materia.getStockMateria());
            stmt.setString(3, materia.getUnidadMedida());
            stmt.setString(4, materia.getEstadoMateria());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(MateriaPrima materia) {
        String query = "UPDATE materia_prima SET nombre_materia = ?, stock_materia = ?, unidad_medida = ?, estado_materia = ? WHERE id_materia_prima = ?";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, materia.getNombreMateria());
            stmt.setInt(2, materia.getStockMateria());
            stmt.setString(3, materia.getUnidadMedida());
            stmt.setString(4, materia.getEstadoMateria());
            stmt.setInt(5, materia.getIdMateriaPrima());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idMateriaPrima) {
        String query = "DELETE FROM materia_prima WHERE id_materia_prima = ?";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idMateriaPrima);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}