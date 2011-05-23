/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Produto;
import com.geekvigarista.services.ProdutoServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class ManagedProduto extends ManagedCadastro implements Serializable {

    private Produto produto = new Produto();
    private List<Produto> produtos = new ArrayList<Produto>();
    private String filtro = "";
    
    @EJB
    private ProdutoServiceLocal servico;
    

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
    public ManagedProduto() {
        
    }

    public void salvar() {
        try {
            System.out.println("AQUI");
            produto.setDataCadastro(new Date());
            servico.edit(produto);
            super.showMessage(new FacesMessage("Salvo!", "Produto " + produto.getNome() + " salvo com sucesso."));
            
        } catch (Exception e) {
            e.printStackTrace();
            super.showMessage(new FacesMessage("Erro!", "Falha ao salvar produto " + produto.getNome() + ". Causa: " + e.getCause()));
        }
    }

    public void novo() {
        produto = new Produto();
    }

    public void excluir() {
        try {
            servico.delete(produto);
            super.showMessage(new FacesMessage("Salvo!", "Produto " + produto.getNome() + " excluído com sucesso."));
            produto = new Produto();
        } catch (Exception e) {
            e.printStackTrace();
            super.showMessage(new FacesMessage("Erro!", "Falha ao excluir produto " + produto.getNome() + ". Causa: " + e.getCause()));
        }
    }
    
    
    public void buscar()
    {
        // TODO To fazendo gambiarra aqui, arrumar algum dia.
        List<Produto> produtosEncontrados = servico.findAll();
        produtos = new ArrayList<Produto>();
        if(!filtro.equals(""))
        {
           for(Produto p : produtosEncontrados)
           {
               if(p.getNome().toLowerCase().contains(filtro.toLowerCase())
                       || p.getDescricao().toLowerCase().contains(filtro.toLowerCase()))
               {
                   produtos.add(p); 
               }
           }
        }
        else
        {
            produtos = produtosEncontrados; 
        }
    }
    
}
