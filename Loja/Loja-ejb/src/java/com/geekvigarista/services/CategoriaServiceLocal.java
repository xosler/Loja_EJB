/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.pojo.Categoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface CategoriaServiceLocal {

    void create(Categoria c);

    void delete(Categoria c);

    void edit(Categoria c);

    Categoria find(Long id);

    List<Categoria> findAll();
}
