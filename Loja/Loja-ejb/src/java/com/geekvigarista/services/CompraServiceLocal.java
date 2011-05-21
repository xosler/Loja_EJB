/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.pojo.Compra;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface CompraServiceLocal {

    void create(Compra c);

    void delete(Compra c);

    void edit(Compra c);

    Compra find(Long id);

    List<Compra> findAll();
}
