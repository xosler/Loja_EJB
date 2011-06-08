/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Grupo;
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
public class GrupoFacade extends AbstractFacade<Grupo> implements GrupoFacadeLocal {

    @PersistenceContext(unitName = "Loja-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }

    @Override
    public Grupo find(String userid, String groupid) {

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Grupo> query = qb.createQuery(Grupo.class);
        Root<Grupo> grupo = query.from(Grupo.class);

        if (userid == null) {
            return null;
        }

        if (groupid != null) {
            query.where(
                    qb.and(
                    qb.equal(grupo.<String>get("userid"), userid),
                    qb.equal(grupo.<String>get("groupid"), groupid)));
        } else {
            query.where(qb.and(qb.equal(grupo.<String>get("userid"), userid)));
        }

        List<Grupo> result = em.createQuery(query).getResultList();

        System.out.println(result);

        if (result.size() == 1) {
            return result.get(0);
        } else {
            return null;
        }
    }
}
