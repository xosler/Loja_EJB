/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.pojo.Carrinho;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface CarrinhoServiceLocal {

    void create(Carrinho p);

    void delete(Carrinho p);

    void edit(Carrinho p);

    Carrinho find(Long id);

    List<Carrinho> findAll();
}
