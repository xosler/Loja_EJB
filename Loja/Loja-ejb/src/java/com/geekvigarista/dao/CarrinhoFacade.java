/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Carrinho;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author carlos
 */
@Stateless
public class CarrinhoFacade extends AbstractFacade<Carrinho> implements CarrinhoFacadeLocal {
    @PersistenceContext(unitName = "Loja-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CarrinhoFacade() {
        super(Carrinho.class);
    }
    
}
