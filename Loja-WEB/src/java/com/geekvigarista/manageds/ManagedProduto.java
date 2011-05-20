/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Produto;
import com.geekvigarista.services.ProdutoServiceLocal;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class ManagedProduto implements Serializable {
    
    private Produto produto = new Produto();
    
    @EJB
    private ProdutoServiceLocal servico;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    public ManagedProduto() {
        
    }
    
    public void salvar()
    {
        try
        {
            produto.setDataCadastro(new Date());
            servico.create(produto);

            System.out.println("Salvei essa merda");
            
            for(Produto p : servico.findAll())
            {
                System.out.println(p);
            }
            
            produto = new Produto();
                 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
}
