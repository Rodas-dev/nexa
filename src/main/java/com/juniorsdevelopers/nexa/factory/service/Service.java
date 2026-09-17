package com.juniorsdevelopers.nexa.factory.service;

import com.juniorsdevelopers.nexa.factory.repository.Repository;

public class Service {

    private final Repository repository;

    public Service() {
        this.repository = new Repository();
    }

    public boolean iniciarSesion(String usuario, String password, String rol) {
        if (usuario == null || usuario.isBlank() ||
            password == null || password.isBlank() ||
            rol == null || rol.isBlank()) {
            return false;
        }

        return repository.validarUsuario(usuario, password, rol);
    }
}