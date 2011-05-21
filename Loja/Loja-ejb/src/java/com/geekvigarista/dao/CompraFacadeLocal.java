/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Compra;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface CompraFacadeLocal {

    void create(Compra compra);

    void edit(Compra compra);

    void remove(Compra compra);

    Compra find(Object id);

    List<Compra> findAll();

    List<Compra> findRange(int[] range);

    int count();
    
}
