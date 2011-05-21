/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.dao.CarrinhoFacadeLocal;
import com.geekvigarista.pojo.Carrinho;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlos
 */
@Stateless
public class CarrinhoService implements CarrinhoServiceLocal {
    
    @EJB
    private CarrinhoFacadeLocal dao;
    
    @Override
    public void create(Carrinho p) {
        dao.create(p);
    }

    @Override
    public void delete(Carrinho p) {
        dao.remove(p);
    }

    @Override
    public void edit(Carrinho p) {
        dao.edit(p);
    }

    @Override
    public Carrinho find(Long id) {
        return dao.find(id);
    }

    @Override
    public List<Carrinho> findAll() {
        return dao.findAll();
    }
    
}
