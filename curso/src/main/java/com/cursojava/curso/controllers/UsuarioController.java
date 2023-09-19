package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao userDao;

    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value="api/usuario/{id}", method = RequestMethod.GET)
    public Usuario getUser(@PathVariable Long id){

        Usuario usuario = new Usuario();
        usuario.setName("Alhena");
        usuario.setId(id);
        usuario.setLastname("Landsman");
        usuario.setEmail("alhe@mail.com");
        usuario.setPhone("123456");
        usuario.setPassword("1234");

        return usuario;
    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsers(@RequestHeader(value = "Authorization") String token){

        String userID = jwtUtil.getKey(token);
        if (userID == null){
            return new ArrayList<>();
        }
        return userDao.getUsers();
    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.POST)
    public void registerUser(@RequestBody Usuario user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.register(user);
    }


    @RequestMapping(value="api/usuario/{id}", method = RequestMethod.PUT)
    public Usuario updateUser(){

        Usuario usuario = new Usuario();
        usuario.setName("Alhena");
        usuario.setLastname("Landsman");
        usuario.setEmail("alhe@mail.com");
        usuario.setPhone("123456");
        usuario.setPassword("1234");
        return usuario;
    }

    @RequestMapping(value="api/usuario/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id){
        userDao.delete(id);
        return;
    }

}
