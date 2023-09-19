package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.Query;
import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsers() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void delete(Long id) {
        Usuario user = entityManager.find(Usuario.class, id);
        entityManager.remove(user);
    }

    @Override
    public void register(Usuario user) {
        entityManager.merge(user);
    }

    @Override
    public Usuario verifyEmailPassword(Usuario user) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> list = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if(list.isEmpty()){
            return null;
        }

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(list.get(0).getPassword(), user.getPassword())){
            return list.get(0);
        }
        return null;
    }
}
