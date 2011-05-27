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
import javax.faces.model.SelectItem;

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

    /**
     * Gera o array de SelectItens de categorias :)
     * @return SelectItem[]
     * @author Carlos
     */
    public SelectItem[] getCategoriasItems() {

        List<Categoria> categorias = servicoCategoria.findAll();

        SelectItem[] items = new SelectItem[(categorias.size())];
        int i = 0;
        for (Categoria c : categorias) {
            items[i++] = new SelectItem(c, c.getDescricao());
        }
        return items;
    }

    public List<Categoria> getCategorias() {
        return servicoCategoria.findAll();
    }

    public void filtrarPorCategoria() {
        List<Produto> produtos_ = new ArrayList<Produto>();
        if (produto == null || produtos.isEmpty()) {
            buscar();
        }

        if (idCategoriaSelecionada == null) {
            return;
        }

//        for(Produto p : produtos)
//        {
//            for(Categoria c : p.getCategorias())
//            {
//                if(c.getId().equals(idCategoriaSelecionada)) //repare que nao carreguei a categoria do banco :)
//                {
//                    produtos_.add(p);
//                }
//            }
//        }
        //modo 2 = menos iterações :D
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

            System.out.println("cats" + produto.getCategorias().toString());

            List<Categoria> categorias_ = produto.getCategorias();
            produto.setCategorias(new ArrayList<Categoria>());
            
            for (Object si : categorias_) {
                System.out.println(si);
                System.out.println(si.getClass());
//                Categoria c = servicoCategoria.find(si.getId());
//                System.out.println(c.toString());
//                produto.getCategorias().add(c);
            }
            
            if (produto != null && produto.getId() == null) {
                produto.setDataCadastro(new Date());
                produto.setImagens(new ArrayList<String>());
                servico.create(produto);
            } else {
                servico.edit(produto);
            }
            showMessage(new FacesMessage("Salvo!", "Produto " + produto.getNome() + " salvo com sucesso."));

        } catch (Exception e) {
            e.printStackTrace();
            showMessage(new FacesMessage("Erro!", "Falha ao salvar produto " + produto.getNome() + ". Causa: " + e.getCause()));
        }
    }

    public void novo() {
        produto = new Produto();
    }

    public void excluir() {
        try {
            servico.delete(produto);
            showMessage(new FacesMessage("Salvo!", "Produto " + produto.getNome() + " excluído com sucesso."));
            produto = new Produto();
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(new FacesMessage("Erro!", "Falha ao excluir produto " + produto.getNome() + ". Causa: " + e.getCause()));
        }
    }

    public void buscar() {
        // TODO To fazendo gambiarra aqui, arrumar algum dia. by Xosler (XGH HAHAH). (ou nao)
        List<Produto> produtosEncontrados = servico.findAll();
        produtos = new ArrayList<Produto>();
        if (!filtro.equals("")) {
            for (Produto p : produtosEncontrados) {
                if (p.getNome().toLowerCase().contains(filtro.toLowerCase())
                        || p.getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    produtos.add(p);
                }
            }
        } else {
            produtos = produtosEncontrados;
        }
    }

    public void load() {
        if (idSelecionado != null) {
            produto = servico.find(idSelecionado);
        }
    }
}
