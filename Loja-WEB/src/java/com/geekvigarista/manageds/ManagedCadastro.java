/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.manageds;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author carlos
 */
@ManagedBean
@RequestScoped
public class ManagedCadastro {

    public void showMessage(FacesMessage mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, mensagem);
    }
}
