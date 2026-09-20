package com.juniorsdevelopers.nexa.factory.repository;

import com.juniorsdevelopers.nexa.factory.config.DataBaseConnection;
import com.juniorsdevelopers.nexa.factory.model.Rol;
import com.juniorsdevelopers.nexa.factory.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    public boolean validarUsuario(String usuarioInput, String passwordInput, String rolInput) {
        String query = "SELECT u.id_usuario FROM usuario u " +
                       "JOIN rol r ON u.id_rol = r.id_rol " +
                       "WHERE (u.correo = ? OR u.nombre_usuario = ?) " +
                       "AND u.contrasena = ? " +
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

    public List<Rol> listarRoles() {
        List<Rol> roles = new ArrayList<>();
        String query = "SELECT id_rol, nombre_rol FROM rol";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                roles.add(new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol"), null));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public Rol obtenerRolPorNombre(String nombreRol) {
        String query = "SELECT id_rol, nombre_rol FROM rol WHERE nombre_rol = ?";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreRol);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol"), null);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existeUsuarioOCorreo(String nombreUsuario, String correo) {
        String query = "SELECT id_usuario FROM usuario WHERE nombre_usuario = ? OR correo = ?";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, correo);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean registrarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuario (id_rol, nombre_usuario, correo, contrasena) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getDataBaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, usuario.getIdRol());
            stmt.setString(2, usuario.getNombreUsuario());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContraseña());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String obtenerHashContrasena(String usuarioInput, String rolInput) {
    String query = "SELECT u.contrasena FROM usuario u " +
                   "JOIN rol r ON u.id_rol = r.id_rol " +
                   "WHERE (u.correo = ? OR u.nombre_usuario = ?) " +
                   "AND r.nombre_rol = ?";
    try (Connection conn = DataBaseConnection.getDataBaseConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, usuarioInput);
        stmt.setString(2, usuarioInput);
        stmt.setString(3, rolInput);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("contrasena");
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}
