/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Cartao;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface CartaoFacadeLocal {

    void create(Cartao cartao);

    void edit(Cartao cartao);

    void remove(Cartao cartao);

    Cartao find(Object id);

    List<Cartao> findAll();

    List<Cartao> findRange(int[] range);

    int count();
    
}
