/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.services;

import com.geekvigarista.pojo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface UsuarioServiceLocal {

    void create(Usuario u);

    void delete(Usuario u);

    void edit(Usuario u);

    Usuario find(Long id);

    List<Usuario> findAll();

    Usuario login(String user, String password);

    List<Usuario> find(String param);
}
