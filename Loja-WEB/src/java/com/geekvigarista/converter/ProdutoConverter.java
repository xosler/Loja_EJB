package com.geekvigarista.converter;

import com.geekvigarista.pojo.Produto;
import com.geekvigarista.services.ProdutoServiceLocal;
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
@FacesConverter("produtoConverter")
public class ProdutoConverter implements Converter {
    private ProdutoServiceLocal servico;

    public ProdutoConverter() throws NamingException {
        InitialContext ic = new InitialContext();
        servico = (ProdutoServiceLocal) ic.lookup("java:global/Loja/Loja-ejb/ProdutoService");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return servico.find(new Long(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Produto) {
            return  String.valueOf(((Produto) value).getId());
        } else {
            throw new IllegalArgumentException("tá errado essa merda");
        }
    }
}
