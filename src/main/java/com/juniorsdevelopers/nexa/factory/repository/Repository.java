package com.juniorsdevelopers.nexa.factory.repository;

import com.juniorsdevelopers.nexa.factory.config.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Repository {

    public boolean validarUsuario(String usuarioInput, String passwordInput, String rolInput) {
        String query = "SELECT u.id_usuario FROM usuario u " +
                       "JOIN rol r ON u.id_rol = r.id_rol " +
                       "WHERE (u.correo = ? OR u.nombre_usuario = ?) " +
                       "AND u.contraseña = ? " +
                       "AND r.nombre_rol = ?";

        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuarioInput);
            stmt.setString(2, usuarioInput);
            stmt.setString(3, passwordInput);
            stmt.setString(4, rolInput);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}