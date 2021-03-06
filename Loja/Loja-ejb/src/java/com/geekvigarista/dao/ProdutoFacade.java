/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Produto;
import com.geekvigarista.pojo.Categoria;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author carlos
 */
@Stateless
public class ProdutoFacade extends AbstractFacade<Produto> implements ProdutoFacadeLocal {

    @PersistenceContext(unitName = "Loja-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }

    @Override
    public List<Produto> findByTextCategoria(String t, Categoria c, boolean removerVencidos) {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = qb.createQuery(Produto.class);
        Root<Produto> produto = query.from(Produto.class);

        // avoid null pointers e tretas com o predicate
        t = t == null ? "" : t;


        Predicate padrao = qb.or(
                qb.like(produto.<String>get("nome"), "%" + t + "%"),
                qb.like(produto.<String>get("descricao"), "%" + t + "%"));
        
        Predicate n_vencidos = qb.and(qb.greaterThan(produto.<Integer>get("quantidadeEmEstoque"), 0), 
                qb.greaterThanOrEqualTo(produto.<Date>get("dataVencimentoOferta"), new Date()));
        
        Predicate categoria = qb.equal(produto.<Categoria>get("categorias"), c);

        // fazendo essa merda like a boss!
        if (c != null) {
            
            /*
             * SE TIVER FALSE E TEM CATEGORIA SELECIONADA, É UMA BUSCA AVANCADA, AE EU MOSTRO TODOS PELA CATEGORIA
             */
            if (!removerVencidos) {
            
                query.where(padrao, qb.and(categoria));
            
            } else {
            
                /*
                 * SENAO, É UMA BUSCA NORMAL, E TIRO OS VENCIDOS FORA
                 */
                query.where(padrao, categoria, n_vencidos);
                
            }
            
        } else if (!removerVencidos) {
            
            /*
             * UMA BUSCA SEM CATEGORIA, E INCLUINDO OS VENCIDOS, ENTÃO, BUSCO SOMENTE O PADRAO
             */
            query.where(padrao);
            
        } else {
            
            /*
             * SENAO, FILTRO APENAS PELOS VENCIDOS SEM CATEGORIA E COM O PADRAO.
             */
             query.where(padrao, n_vencidos);
        
        }

        List<Produto> result = em.createQuery(query).getResultList();

        return result;
    }
}
