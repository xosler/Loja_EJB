/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Categoria;
import com.geekvigarista.services.CategoriaServiceLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class ManagedCategoria extends ManagedCadastro {

   
    @EJB
    private CategoriaServiceLocal servico;
    
    private Categoria categoria = new Categoria();
    
    public ManagedCategoria() {
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void salvar()
    {
         try{
            if(categoria != null && categoria.getId() == null)
            {
                servico.create(categoria);
            }else{
                servico.edit(categoria);
            }
            showMessage(new FacesMessage("Salvo", "Categoria salva " + categoria.getDescricao() + " com sucesso!"));
        }catch(Exception e)
        {
            showMessage(new FacesMessage("Erro", "Falha ao excluri categoria " + categoria.getDescricao() + ". Motivo: " + e.getCause()));
        }
    }
    
    public void novo()
    {
       categoria = new Categoria();
    }
    
    public Categoria load(Long id)
    {
        return servico.find(id);
    }
        
}
