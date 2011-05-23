/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Categoria;
import com.geekvigarista.services.CategoriaServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class ManagedCategoria extends ManagedCadastro implements Serializable{
    
    @EJB
    private CategoriaServiceLocal service;
    
    private Long idSelecionado = 0L;
    private Categoria categoria = new Categoria();
    private List<Categoria> categorias = new ArrayList<Categoria>();
    private String filtro = "";
    
    
    public ManagedCategoria() {
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }
    
    // ########################################################################
    
    public void novo()
    {
        categoria = new Categoria();
    }
    
    public void salvar()
    {
        try{
            categoria.setId(1L);
            service.create(categoria); 
            showMessage(new FacesMessage("Salvo!", "Categoria " + categoria.getDescricao() + " salva com sucesso!"));
        } catch (Exception e)
        {
            e.printStackTrace();
            showMessage(new FacesMessage("Erro!", "Falha ao salvar a categoria " + categoria.getDescricao() + ". Causa: " + e.getCause()));
        }
    }
    
    public void excluir()
    {
        try{
            service.remove(categoria); 
            showMessage(new FacesMessage("Excluido!", "Categoria " + categoria.getDescricao() + " excluida com sucesso!"));
        } catch (Exception e)
        {
            e.printStackTrace();
            showMessage(new FacesMessage("Erro!", "Falha ao excluir a categoria " + categoria.getDescricao() + ". Causa: " + e.getCause()));
        }
    }
}
