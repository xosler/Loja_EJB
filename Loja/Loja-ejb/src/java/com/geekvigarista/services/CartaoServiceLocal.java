/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.pojo.Cartao;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface CartaoServiceLocal {

    void create(Cartao c);

    void delete(Cartao c);

    void edit(Cartao c);

    Cartao find(Long id);

    List<Cartao> findAll();
}
