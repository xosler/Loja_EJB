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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class ManagedCategoria extends ManagedCadastro implements Serializable {

   
    @EJB
    private CategoriaServiceLocal servico;
    
    private Categoria categoria = new Categoria();
    private Long idSelecionado = null;
    private String filtro = "";
    private List<Categoria> categorias = new ArrayList<Categoria>();
    
    public ManagedCategoria() {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaServiceLocal getServico() {
        return servico;
    }

    public void setServico(CategoriaServiceLocal servico) {
        this.servico = servico;
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
            showMensagemSalvar(categoria.getDescricao());
        }catch(Exception e)
        {
            showMensagemErroSalvar(categoria.getDescricao(), e.getMessage());
        }
    }
    
    public void novo()
    {
       categoria = new Categoria();
    }
    
    public void excluir()
    {
        if(categoria != null && categoria.getId() != null)
        {
            try {
                servico.delete(categoria);
                novo();
                showMensagemExcluir(categoria.getDescricao());
            } catch (Exception e)
            {
                e.printStackTrace();
                showMensagemErroExcluir(categoria.getDescricao(), e.getMessage());
            }
        }
    }
    
    public void load()
    {
        categoria = servico.find(idSelecionado);
    }
    
    public void selecionarExcluir()
    {
        if(idSelecionado != null)
        {
            load();
            excluir();
        }
    }
    
    public void selecionarExcluirBuscar()
    {
        selecionarExcluir();
        buscar();
    }
    
    public void buscar()
    {
        if(filtro == null || filtro.trim().isEmpty())
        {
            categorias = servico.findAll();
        }
        else
        {
            categorias = servico.findByDescricao(filtro);
        }
    }
        
}
