/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.pojo.Grupo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface GrupoServiceLocal {

    void create(Grupo g);

    void delete(Grupo g);

    void edit(Grupo g);

    Grupo find(Long id);

    List<Grupo> findAll();
    
    Grupo find(String userid, String groupid);
}
