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
    public Usuario login(String user, String senha);
    public void save(Usuario u);
    public void edit(Usuario u);
    public void delete(Usuario u);
    public List<Usuario> findAll();
    public List<Usuario> find(Long id);
}
