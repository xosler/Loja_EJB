package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Categoria;
import com.geekvigarista.pojo.Produto;
import com.geekvigarista.services.CategoriaServiceLocal;
import com.geekvigarista.services.ProdutoServiceLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

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
    private static final int BUFFER_SIZE = 6124;
    private boolean removerVencidos = false;

    public boolean isRemoverVencidos() {
        return removerVencidos;
    }

    public void setRemoverVencidos(boolean removerVencidos) {
        this.removerVencidos = removerVencidos;
    }

    public Long getIdCategoriaSelecionada() {
        return idCategoriaSelecionada;
    }

    public void setIdCategoriaSelecionada(Long idCategoriaSelecionada) {
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

    public void removerFiltro() {
        idCategoriaSelecionada = null;
        buscar();
    }

    @PostConstruct
    public void init() {
        
    }
    
    /**
     * isso era usado enquanto eu nao conseguia fazer as query funcionar certo
     * @author carlos
     * @deprecated
     */
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
            novo();
        } catch (Exception e) {
            e.printStackTrace();
            showMensagemErroExcluir(produto.getNome(), e.getLocalizedMessage());
        }
    }
    
    public void buscarTela()
    {
        buscarGen(removerVencidos);
    }
    
    /**
     * busca somente os produtos que ainda não venceram!
     * @author Carlos
     */
    public void buscar() {
        buscarGen(true);
    }
    
    /**
     * busca todos, incluindo os vencidos (para edições e tals)
     * @author Carlos
     */
    public void buscarTodos(){
        buscarGen(false);
    }
    
    /**
     * busca gerica.
     * @param removerVencidos true: somente os que ainda nao venceram; false: todos
     * @see buscarTodos(), buscar()
     * @author Carlos
     */
    public void buscarGen(boolean removerVencidos){
        if (idCategoriaSelecionada == null) {
            produtos = servico.findByTextCategoria(filtro, null, removerVencidos);
        } else {
            produtos = servico.findByTextCategoria(filtro, servicoCategoria.find(idCategoriaSelecionada), removerVencidos);
        }
    }
    
    public void load() {
        if (idSelecionado != null) {
            produto = servico.find(idSelecionado);
        }
    }

    /**
     * @author Alexandre
     * @return 
     */
    public List<Produto> getListaProdutos() {
        buscar();
        return produtos;
    }

    public void handleFileUpload(FileUploadEvent event) {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();

        File pasta = new File(extContext.getRealPath("//files//"));
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        String filenameOriginal = event.getFile().getFileName();
        String ext = filenameOriginal.substring(filenameOriginal.lastIndexOf("."), filenameOriginal.length()); //lastIndexOf(".")
        String nomeArquivo = new Date().getTime() + ext;


        File result = new File(extContext.getRealPath("//files//"
                + nomeArquivo));

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            FacesMessage msg = new FacesMessage("Sucesso", event.getFile().getFileName()
                    + " enviado com sucesso.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            produto.setImagem("/files/" + nomeArquivo);

        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Falha ao fazer o upload!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }

    }

    public void selecionarExcluir() {
        load();
        excluir();
    }

    public void selecionarExcluirBuscar() {
        selecionarExcluir();
        buscarTela();
    }
    
    public void excluirImagemProduto(){
        produto.setImagem("");
    }
}
