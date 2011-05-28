/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Categoria;
import com.geekvigarista.pojo.Produto;
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
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {
    @PersistenceContext(unitName = "Loja-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }

    @Override
    public List<Categoria> findByDescricao(String descr) {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Categoria> query = qb.createQuery(Categoria.class);
        Root<Categoria> categoria = query.from(Categoria.class);

        if (!(descr == null || descr.trim().isEmpty())) {
            query.where(
                    qb.like(categoria.<String>get("descricao"), "%" + descr + "%")
                    );
        }
        List<Categoria> result = em.createQuery(query).getResultList();

        return result;
    }
    
}
