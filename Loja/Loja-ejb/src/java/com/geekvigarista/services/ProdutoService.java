/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.pojo.Produto;
import com.geekvigarista.dao.ProdutoFacadeLocal;
import com.geekvigarista.pojo.Categoria;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlos
 */
@Stateless
public class ProdutoService implements ProdutoServiceLocal {

    @EJB
    private ProdutoFacadeLocal dao;

    @Override
    public void create(Produto p) {
        dao.create(p);
    }

    @Override
    public void delete(Produto p) {
        dao.remove(p);
    }

    @Override
    public void edit(Produto p) {
        dao.edit(p);
    }

    @Override
    public Produto find(Long id) {
        return dao.find((Object) id);
    }

    @Override
    public List<Produto> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Produto> findByTextCategoria(String t, Categoria c) {
        return dao.findByTextCategoria(t, c);
    }
}
