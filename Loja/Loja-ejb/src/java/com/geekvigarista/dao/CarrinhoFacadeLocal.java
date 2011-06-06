/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. 
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Carrinho;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface CarrinhoFacadeLocal {

    void create(Carrinho carrinho);

    void edit(Carrinho carrinho);

    void remove(Carrinho carrinho);

    Carrinho find(Object id);

    List<Carrinho> findAll();

    List<Carrinho> findRange(int[] range);

    int count();
    
}
