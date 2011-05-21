/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.dao.CartaoFacadeLocal;
import com.geekvigarista.pojo.Cartao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlos
 */
@Stateless
public class CartaoService implements CartaoServiceLocal {
    
    @EJB
    private CartaoFacadeLocal dao;
    
    @Override
    public void create(Cartao c) {
        dao.create(c);
    }

    @Override
    public void delete(Cartao c) {
        dao.remove(c);
    }

    @Override
    public void edit(Cartao c) {
        dao.edit(c);
    }

    @Override
    public Cartao find(Long id) {
        return dao.find(id);
    }

    @Override
    public List<Cartao> findAll() {
        return dao.findAll();
    }
    
}
