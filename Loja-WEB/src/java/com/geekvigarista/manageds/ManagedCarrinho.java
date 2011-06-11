package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Carrinho;
import com.geekvigarista.pojo.Compra;
import com.geekvigarista.pojo.Produto;
import com.geekvigarista.services.ProdutoServiceLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class ManagedCarrinho extends ManagedCadastro implements Serializable {

    private Carrinho carrinho = new Carrinho();
    private Long idProdutoSelecionado;
    @EJB
    private ProdutoServiceLocal servicoProduto;
    // injetando outros manageds aqui!
    @ManagedProperty(value = "#{managedLogin}")
    private ManagedLogin managedLogin;

    public ManagedLogin getManagedLogin() {
        return managedLogin;
    }

    public void setManagedLogin(ManagedLogin managedLogin) {
        this.managedLogin = managedLogin;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Long getIdProdutoSelecionado() {
        return idProdutoSelecionado;
    }

    public void setIdProdutoSelecionado(Long idProdutoSelecionado) {
        this.idProdutoSelecionado = idProdutoSelecionado;
    }

    public void adicionarAoCarrinho() {
        if (idProdutoSelecionado == null) {
            showMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", "Selecione um produto!"));
            return;
        }

        Produto p = servicoProduto.find(idProdutoSelecionado);
        carrinho.getProdutos().add(p);
        showMessage(new FacesMessage("Adicionado.", "Produto Adicionado ao carrinho com sucesso!"));
//        idProdutoSelecionado = null;
    }

    public void removerDoCarrinho() {
        if (idProdutoSelecionado == null) {
            showMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", "Selecione um produto!"));
            return;
        }

        Produto p = servicoProduto.find(idProdutoSelecionado);
        carrinho.getProdutos().remove(p);
        showMessage(new FacesMessage("Removido.", "Produto removido do carrinho com sucesso!"));
        idProdutoSelecionado = null;
    }

    public ManagedCarrinho() {
    }

    public Double getTotalCarrinho() {
        Double total = 0.0;
        for (Produto p : carrinho.getProdutos()) {
            total += p.getPreco();
        }
        return total;
    }
}
