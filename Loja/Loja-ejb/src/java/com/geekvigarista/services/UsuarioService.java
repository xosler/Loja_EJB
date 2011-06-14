/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.dao.GrupoFacadeLocal;
import com.geekvigarista.dao.UsuarioFacadeLocal;
import com.geekvigarista.pojo.Grupo;
import com.geekvigarista.pojo.Usuario;
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
    @EJB
    private GrupoFacadeLocal daoGrupo;

    @Override
    public void create(Usuario u) {

        /* logica pra salvar o grupo também
         * isso tem que ficar transparente pro usuario e pra camada de visao!
         */
        if (u.getUserid() != null && !u.getUserid().trim().equals("")
                && u.getGrupo() != null && !u.getGrupo().trim().equals("")) {
            Grupo g = new Grupo();

            g.setUserid(u.getUserid());
            g.setGroupid(u.getGrupo());

            daoGrupo.create(g);
            dao.create(u);
        }
    }

    @Override
    public void delete(Usuario u) {
        dao.remove(u);
        daoGrupo.remove(daoGrupo.find(u.getUserid(), u.getGrupo()));
    }

    @Override
    public void edit(Usuario u) {
        dao.edit(u);
        if (u.getGrupo() != null && !u.getGrupo().equals("")) {
            daoGrupo.edit(daoGrupo.find(u.getUserid(), u.getGrupo()));
        }
    }

    @Override
    public Usuario find(Long id) {
        Usuario u = dao.find(id);
        u.setGrupo(daoGrupo.find(u.getUserid(), null).getGroupid()); // ao dar load, seta o grupo pra usar se for necessario em alguma lógica!
        return u;
    }

    @Override
    public List<Usuario> findAll() {
        return dao.findAll();
    }

    @Override
    public Usuario login(String user, String password) {
        Usuario u = dao.login(user, password);
        if (u != null) {
            u.setGrupo(daoGrupo.find(u.getUserid(), null).getGroupid());
        }
        return u;
    }

    @Override
    public List<Usuario> find(String param) {
        return dao.find(param);
    }
}
