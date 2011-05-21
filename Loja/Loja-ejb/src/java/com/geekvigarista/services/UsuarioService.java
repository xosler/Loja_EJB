/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.pojo.Usuario;
import com.geekvigarista.dao.UsuarioFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlos
 */
@Stateless
public class UsuarioService implements UsuarioServiceLocal {

    @EJB
    private UsuarioFacadeLocal dao;

    @Override
    public Usuario login(String user, String senha) {
        Usuario u = new Usuario();
        u.setEmail(user);
        u.setSenha(senha);

        u = dao.find(u);
        if (u != null) {
            System.out.println("logando como " + u.getNome());
        }
        return u;
    }

    @Override
    public void save(Usuario u) {
        dao.create(u);
    }

    @Override
    public void edit(Usuario u) {
        dao.edit(u);
    }

    @Override
    public void delete(Usuario u) {
        dao.remove(u);
    }

    @Override
    public List<Usuario> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Usuario> find(Long id) {
        return (List<Usuario>) dao.find((Object) id);
    }
}
