/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.converter;

import com.geekvigarista.pojo.Categoria;
import com.geekvigarista.services.CategoriaServiceLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public CategoriaConverter() throws NamingException {
        InitialContext ic = new InitialContext();
        servico = (CategoriaServiceLocal) ic.lookup("java:global/Loja/Loja-ejb/CategoriaService");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return servico.find(new Long(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Categoria) {
            return ((Categoria) value).getId().toString();
        } else {
            throw new IllegalArgumentException("tá errado essa merda");
        }
    }
}
