package com.juniorsdevelopers.nexa.factory.service;

import com.juniorsdevelopers.nexa.factory.model.Rol;
import com.juniorsdevelopers.nexa.factory.model.Usuario;
import com.juniorsdevelopers.nexa.factory.repository.Repository;
import com.juniorsdevelopers.nexa.factory.segurity.Bcrypt;
import java.util.List;
import java.util.Map;


public class Service {

    private final Repository repository;

  private static final Map<String, String> CODIGOS_ROL = Map.of(
    "Administrador", "2026A",
    "Supervisor", "2026B",
    "Almacén", "2026C"
 );

    public Service() {
        this.repository = new Repository();
    }

    public boolean iniciarSesion(String usuario, String password, String rol) {
    if (usuario == null || usuario.isBlank() ||
        password == null || password.isBlank() ||
        rol == null || rol.isBlank()) {
        return false;
    }

    String hashGuardado = repository.obtenerHashContrasena(usuario, rol);
    if (hashGuardado == null) {
        return false;
    }

    try {
        return Bcrypt.checkpw(password, hashGuardado);
    } catch (IllegalArgumentException e) {
        return false;
    }
}

    public List<Rol> obtenerRoles() {
        return repository.listarRoles();
    }

    public String registrarUsuario(String nombreUsuario, String correo, String contrasena, String nombreRol, String codigoRolIngresado) {
        if (nombreUsuario == null || nombreUsuario.isBlank() ||
            correo == null || correo.isBlank() ||
            contrasena == null || contrasena.isBlank() ||
            nombreRol == null || nombreRol.isBlank() ||
            codigoRolIngresado == null || codigoRolIngresado.isBlank()) {
            return "Debes completar todos los campos.";
        }

        String codigoEsperado = CODIGOS_ROL.get(nombreRol);
        if (codigoEsperado == null || !codigoEsperado.equals(codigoRolIngresado)) {
            return "El código del rol no es válido.";
        }

        if (repository.existeUsuarioOCorreo(nombreUsuario, correo)) {
            return "Ya existe un usuario con ese nombre o correo.";
        }

        Rol rolEncontrado = repository.obtenerRolPorNombre(nombreRol);
        if (rolEncontrado == null) {
            return "El rol seleccionado no existe en la base de datos.";
        }

        String contrasenaEncriptada = Bcrypt.hashpw(contrasena, Bcrypt.gensalt());
        Usuario nuevoUsuario = new Usuario(0, rolEncontrado.getIdRol(), nombreUsuario, correo, contrasenaEncriptada);

        boolean registrado = repository.registrarUsuario(nuevoUsuario);
        return registrado ? "OK" : "No se pudo registrar el usuario.";
    }
}