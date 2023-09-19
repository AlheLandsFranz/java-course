package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsers();

    void delete(Long id);

    void register(Usuario user);

    Usuario verifyEmailPassword(Usuario user);
};
