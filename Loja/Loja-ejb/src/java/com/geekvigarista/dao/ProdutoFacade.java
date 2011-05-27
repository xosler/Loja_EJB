/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Produto;
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
    public List<Produto> findByText(String t) {

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = qb.createQuery(Produto.class);
        Root<Produto> produto = query.from(Produto.class);

        if (!(t == null || t.trim().isEmpty())) {
            query.where(
                    qb.like(produto.<String>get("nome"), "%" + t + "%"),
                    qb.or(qb.like(produto.<String>get("descricao"), "%" + t + "%"))
                    );
        }
        List<Produto> result = em.createQuery(query).getResultList();

        return result;
    }
}
