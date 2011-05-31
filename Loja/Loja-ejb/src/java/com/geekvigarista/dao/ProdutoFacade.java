/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Produto;
import com.geekvigarista.pojo.Categoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    public List<Produto> findByTextCategoria(String t, Categoria c) {

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = qb.createQuery(Produto.class);
        Root<Produto> produto = query.from(Produto.class);

        // avoid null pointers
        t = t == null ? "" : t;

        if (c != null) {
            query.where(qb.or(
                    qb.like(produto.<String>get("nome"), "%" + t + "%"),
                    qb.like(produto.<String>get("descricao"), "%" + t + "%")),
                    qb.and(qb.equal(produto.<Categoria>get("categorias"), c)));
        } else {
             query.where(qb.or(
                    qb.like(produto.<String>get("nome"), "%" + t + "%"),
                    qb.like(produto.<String>get("descricao"), "%" + t + "%")));
        }
        
        
        List<Produto> result = em.createQuery(query).getResultList();

        return result;
    }
}
