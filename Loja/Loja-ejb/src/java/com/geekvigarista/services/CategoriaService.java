/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.dao.CategoriaFacadeLocal;
import com.geekvigarista.pojo.Categoria;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlos
 */
@Stateless
public class CategoriaService implements CategoriaServiceLocal {
    
    @EJB
    private CategoriaFacadeLocal dao;
    
    @Override
    public void create(Categoria c) {
        dao.create(c);
    }

    @Override
    public void delete(Categoria c) {
        dao.remove(c);
    }

    @Override
    public void edit(Categoria c) {
        dao.edit(c);
    }

    @Override
    public Categoria find(Object id) {
        return dao.find(id);
    }

    @Override
    public List<Categoria> findAll() {
        return dao.findAll();
    }
}
