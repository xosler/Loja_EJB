/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.dao;

import com.geekvigarista.pojo.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "Loja-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario login(String user, String password) {

        if (user != null && password != null && !user.trim().equals("") && !password.trim().equals("")) {
            CriteriaBuilder qb = em.getCriteriaBuilder();
            CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);
            Root<Usuario> usuario = query.from(Usuario.class);

            query.where(qb.and(qb.equal(usuario.<String>get("userid"), user),
                    qb.equal(usuario.<String>get("password"), password)));

            List<Usuario> u = em.createQuery(query).getResultList();
            if (u != null && !u.isEmpty()) {
                return u.get(0);
            } else {
                return null;
            }
        }

        return null;
    }

    public List<Usuario> find(String param) {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);
        Root<Usuario> usuario = query.from(Usuario.class);

        param = "%" + param + "%";

        query.where(qb.or(qb.like(usuario.<String>get("userid"), param),
                qb.like(usuario.<String>get("email"), param),
                qb.like(usuario.<String>get("nome"), param),
                qb.like(usuario.<String>get("telefone"), param),
                qb.like(usuario.<String>get("endereco"), param)));
        
        return em.createQuery(query).getResultList();
    }
}
