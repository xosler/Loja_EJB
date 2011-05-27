/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.converter;

import com.geekvigarista.pojo.Categoria;
import com.geekvigarista.services.CategoriaServiceLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author carlos
 */
@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter {
    
    private CategoriaServiceLocal servico;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            System.out.print("aqw " + value);
            
            InitialContext ic = new InitialContext();
            servico = (CategoriaServiceLocal) ic.lookup("java:global/Loja/Loja-ejb/CategoriaService");
            return servico.find(new Long(value));
        } catch (NamingException ex) {
            Logger.getLogger(CategoriaConverter.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        System.out.println(value.getClass());
        
        if (value instanceof Categoria) {
            return ((Categoria) value).getId().toString();
        } else {
            throw new IllegalArgumentException("tá errado essa merda");

        }
    }
}
