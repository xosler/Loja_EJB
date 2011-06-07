/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.dao.GrupoFacadeLocal;
import com.geekvigarista.pojo.Grupo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlos
 */
@Stateless
public class GrupoService implements GrupoServiceLocal {
    
    @EJB
    private GrupoFacadeLocal dao;
    
    @Override
    public void create(Grupo g) {
        dao.create(g);
    }

    @Override
    public void delete(Grupo g) {
        dao.remove(g);
    }

    @Override
    public void edit(Grupo g) {
        dao.edit(g);
    }

    @Override
    public Grupo find(Long id) {
        return dao.find(id);
    }

    @Override
    public List<Grupo> findAll() {
        return dao.findAll();
    }

    @Override
    public Grupo find(String userid, String groupid) {
        return dao.find(userid, groupid);
    }

    
}
