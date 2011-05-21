/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.pojo.Produto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface ProdutoServiceLocal {

    void create(Produto p);

    void delete(Produto p);

    void edit(Produto p);

    Produto find(Long id);

    List<Produto> findAll();
    
}
