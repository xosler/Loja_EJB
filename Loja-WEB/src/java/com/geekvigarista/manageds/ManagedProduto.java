package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Categoria;
import com.geekvigarista.pojo.Produto;
import com.geekvigarista.services.CategoriaServiceLocal;
import com.geekvigarista.services.ProdutoServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class ManagedProduto extends ManagedCadastro implements Serializable {

    private Produto produto = new Produto();
    private List<Produto> produtos = new ArrayList<Produto>();
    private String filtro = "";
    private Long idSelecionado = null;
    private Long idCategoriaSelecionada = null;
    private List<Categoria> categorias = new ArrayList<Categoria>();
    @EJB
    private ProdutoServiceLocal servico;
    @EJB
    private CategoriaServiceLocal servicoCategoria;

    public Long getIdCategoriaSelecionada() {
        return idCategoriaSelecionada;
    }

    public void setIdCategoriaSelecionada(Long idCategoriaSelecionada) {
        System.out.println(idCategoriaSelecionada);
        this.idCategoriaSelecionada = idCategoriaSelecionada;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public ManagedProduto() {
    }

    public List<Categoria> getCategorias() {
        return servicoCategoria.findAll();
    }
    
    public void removerFiltro()
    {
        idCategoriaSelecionada = null;
        buscar();
    }

    public void filtrarPorCategoria() {
        // TODO fazer uma consulta descente aqui tbm..
        List<Produto> produtos_ = new ArrayList<Produto>();
        if (produto == null || produtos.isEmpty()) {
            buscar();
        }

        if (idCategoriaSelecionada == null) {
            return;
        }

        Categoria c = new Categoria();
        for (Categoria cc : getCategorias()) {
            if (cc.getId().equals(idCategoriaSelecionada)) {
                c = cc;
                break;
            }
        }

        for (Produto p : produtos) {
            if (p.getCategorias().contains(c)) {
                produtos_.add(p);
            }
        }

        produtos = produtos_;
    }

    public void salvar() {
        try {
            if (produto != null) {

                if (produto.getDataCadastro() == null) {
                    produto.setDataCadastro(new Date());
                }
                
                if(produto.getDataVencimentoOferta().before(new Date()))
                {
                    showMessage(new FacesMessage("Atenção", "Data de vencimento da oferta é inferior ou igual a data atual."));
                }
                
                if (produto.getId() == null) {
                    servico.create(produto);
                } else {
                    servico.edit(produto);
                }
            }
            showMensagemSalvar(produto.getNome());

        } catch (Exception e) {
            e.printStackTrace();
            showMensagemErroSalvar(produto.getNome(), e.getLocalizedMessage());
        }
    }

    public void novo() {
        produto = new Produto();
    }

    public void excluir() {
        try {
            servico.delete(produto);
            showMensagemExcluir(produto.getNome());
            produto = new Produto();
        } catch (Exception e) {
            e.printStackTrace();
            showMensagemErroExcluir(produto.getNome(), e.getLocalizedMessage());
        }
    }

    public void buscar() {
        produtos = servico.findByText(filtro);
    }

    public void load() {
        if (idSelecionado != null) {
            produto = servico.find(idSelecionado);
        }
    }
}
