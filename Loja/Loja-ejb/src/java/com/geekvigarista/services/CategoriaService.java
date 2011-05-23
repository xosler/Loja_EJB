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
    public void create(Categoria categoria) {
        dao.create(categoria);
    }

    @Override
    public void edit(Categoria categoria) {
        dao.edit(categoria);
    }

    @Override
    public void remove(Categoria categoria) {
        dao.remove(categoria);
    }

    @Override
    public Categoria find(Object id) {
        return dao.find(id);
    }

    @Override
    public List<Categoria> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Categoria> findRange(int[] range) {
        return dao.findRange(range);
    }

    @Override
    public int count() {
        return dao.count();
    }


    
}
