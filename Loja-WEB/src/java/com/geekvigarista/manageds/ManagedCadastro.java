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

    protected void showMessage(FacesMessage mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, mensagem);
    }

    public void showMensagemErroSalvar(String nomeObj, String causa) {
        String msg = "";
        
        if (nomeObj != null) {
            msg = "Ocorreu um erro ao salvar \"" + nomeObj + "\". " + causa != null ? "Causa: " + causa : "";
        }
        else
        {
            msg = "Ocorreu um erro ao salvar.";
        }
        
        showMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg));
    }
    
    public void showMensagemErroExcluir(String nomeObj, String causa) {
        String msg = "";
        
        if (nomeObj != null) {
            msg = "Ocorreu um erro ao excluir \"" + nomeObj + "\". " + causa != null ? "Causa: " + causa : "";
        }
        else
        {
            msg = "Ocorreu um erro ao excluir.";
        }
        
        showMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg));
    }
    
    public void showMensagemSalvar(String nomeObj) {
        String msg = "";
        
        if (nomeObj != null) {
            msg = "\"" + nomeObj + "\" salvo com sucesso. ";
        }
        else
        {
            msg = "Salvo com sucesso. ";
        }
        
        showMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", msg));
    }
    
    public void showMensagemExcluir(String nomeObj) {
        String msg = "";
        
        if (nomeObj != null) {
            msg = "\"" + nomeObj + "\" excluído com sucesso. ";
        }
        else
        {
            msg = "Excluído com sucesso. ";
        }
        
        showMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", msg));
    }
}
