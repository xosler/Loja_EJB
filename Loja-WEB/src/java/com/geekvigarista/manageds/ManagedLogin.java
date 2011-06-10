/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Usuario;
import com.geekvigarista.services.UsuarioServiceLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class ManagedLogin implements Serializable {

    private Usuario logado = null;
    private String user;
    private String password;
    @EJB
    private UsuarioServiceLocal service;

    public Usuario getLogado() {
        return logado;  
    }

    public void setLogado(Usuario logado) {
        this.logado = logado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /** Creates a new instance of ManagedLogin */
    public ManagedLogin() {
    }

    public String login() {
        Usuario u = service.login(user, password);
        if (u != null && u.getId() != null) {
            logado = u;
            return "sucesso";
        } else {
            return "erro";
        }
    }

    public String cadastrar() {
        return "cadastrar";
    }

    public String logout() {
        logado = null;
        return "sair";
    }
}
