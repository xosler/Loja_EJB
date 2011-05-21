/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.dao.CompraFacadeLocal;
import com.geekvigarista.pojo.Compra;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlos
 */
@Stateless
public class CompraService implements CompraServiceLocal {

    @EJB
    private CompraFacadeLocal dao;
    
    @Override
    public void create(Compra c) {
        dao.create(c);
    }

    @Override
    public void delete(Compra c) {
        dao.remove(c);
    }

    @Override
    public void edit(Compra c) {
        dao.edit(c);
    }

    @Override
    public Compra find(Long id) {
        return dao.find(id);
    }

    @Override
    public List<Compra> findAll() {
        return dao.findAll();
    }
    
}
