/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Produto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface ProdutoFacadeLocal {
    
    void create(Produto produto);

    void edit(Produto produto);

    void remove(Produto produto);

    Produto find(Object id);

    List<Produto> findAll();

    List<Produto> findRange(int[] range);

    int count();
    
    List<Produto> findByText(String t);

}
