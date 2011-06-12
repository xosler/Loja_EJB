package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Compra;
import com.geekvigarista.pojo.Produto;
import com.geekvigarista.services.CarrinhoServiceLocal;
import com.geekvigarista.services.CompraServiceLocal;
import com.geekvigarista.services.ProdutoServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class ManagedCompra extends ManagedCadastro implements Serializable {

    private static Logger logger = Logger.getLogger(ManagedCompra.class.getName());
    @EJB
    private CompraServiceLocal servico;
    @EJB
    private ProdutoServiceLocal servicoProduto;
    @EJB
    private CarrinhoServiceLocal servicoCarrinho;
    @ManagedProperty(value = "#{managedCarrinho}")
    private ManagedCarrinho carrinho;
    @ManagedProperty(value = "#{managedLogin}")
    private ManagedLogin managedLogin;
    private Compra compra = new Compra();
    private String numeroCartao;

    public ManagedCarrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ManagedCarrinho carrinho) {
        this.carrinho = carrinho;
    }

    public ManagedLogin getManagedLogin() {
        return managedLogin;
    }

    public void setManagedLogin(ManagedLogin managedLogin) {
        this.managedLogin = managedLogin;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public ManagedCompra() {
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Passo de compra atual:" + event.getOldStep());
        if (event.getOldStep().equals("produtos")) {
            iniciarCompra();
        }
        return event.getNewStep();
    }

    public String iniciarCompra() {
        if (managedLogin != null && managedLogin.getLogado() != null) {
            Compra c = new Compra();
            c.setCarrinho(carrinho.getCarrinho());
            c.setValorTotal(carrinho.getTotalCarrinho());
            c.setUsuario(managedLogin.getLogado());
            c.setDataCompra(new Date());
            setCompra(c);
            return "comprar";
        } else {
            return "deslogado";
        }
    }

    /**
     * método que efetua a compra ou não.
     * 
     * @author Carlos A Becker
     * @return String sucesso/erro
     */
    public String comprar() {
        // isso pode demorar se tiver muitos milhoes de produtos, banco lento e cia...
        logger.info("iniciando compra....");
        showMessage(new FacesMessage("Aguarde...", "Processando a compra... isso pode demorar um pouco..."));

        // primeiro, preciso do carrinho, e dos produtos dele.
        logger.info("carregando os produtos do carrinho....");
        List<Produto> produtos_carrinho = carrinho.getCarrinho().getProdutos();

        if (produtos_carrinho == null || produtos_carrinho.isEmpty()) {
            // TODO Msg nenhum produto, dar choque no usuario, por fogo no pc dele talvez.
            logger.severe("nenhum produto no carrinho....");
            showMessage(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Nenhum produto!",
                    "Adicione alguns produtos ao carrinho!"));
            return "erro";
        }

        Map<Long, itemMap> produtoQtde = new HashMap<Long, itemMap>();
        logger.info("iterando os produtos....");
        for (Produto pro : produtos_carrinho) {

            itemMap qtde = produtoQtde.get(pro.getId()); // quantos itens desses já tem no carrinho!

            if (qtde == null) { // se não tiver null, é pq não foi nenhum desse produto ainda
                qtde = new itemMap(pro.getQuantidadeEmEstoque(), 0);
            }

            qtde.setQuantidadeCarrinho(qtde.getQuantidadeCarrinho() + 1);


            if (qtde.quantidadeCarrinho > qtde.quantidadeEstoque) {
                // TODO DAR MSG DE ERRO AQUI
                logger.severe("sem unidades suficiente para o produto " + pro.getNome() + "...");
                showMessage(new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Esgotado!",
                        ("O produto \"" + pro.getNome() + "\" não possui unidades suficientes em estoque. "
                        + "Tente diminuir a quantidade desse item.")));
                return "erro";
            }

            // vou ter o tanto de cada produto que foi comprado no meu map
            produtoQtde.put(pro.getId(), qtde);

        }
        
        List<Produto> produtosAtualizados = new ArrayList<Produto>();
        
        logger.info("iterando map de produtos adicionados....");
        for (Long idPro : produtoQtde.keySet()) { //itero os itens do map!

            Produto p = servicoProduto.find(idPro); // pego o produto do banco
            logger.info("processando produto " + p.getNome() + "....");
            itemMap itens = produtoQtde.get(idPro); // pedo o item com o estoque e qtde usada

            p.setQuantidadeEmEstoque(p.getQuantidadeEmEstoque() - itens.quantidadeCarrinho);
            servicoProduto.edit(p); // um pouquinho menos de acessos ao DB assim, teoricamente, tinha que ser mais rápido!
            produtosAtualizados.add(p);
        }
        
        compra.getCarrinho().setProdutos(produtosAtualizados);
        
        // TODO talvez fazer cascade automatico? :P
        logger.info("persistindo o carrinho....");
        if(compra.getCarrinho().getId() == null)
        {
            servicoCarrinho.create(compra.getCarrinho());
        } else {
            servicoCarrinho.edit(compra.getCarrinho());
        }
        
        logger.info("persistindo a compra....");
        // também tenho que salvar a compra né manoloooooooo!!!!!!!!111onze!!!!!11
        if (compra.getId() == null) {
            servico.create(compra);
        } else {
            servico.edit(compra);
        }

        logger.info("limpando lixo....");
        // se nada explodiu até agora, limpo o lixo!
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("managedCarrinho", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("managedProduto", null);
        System.gc();

        //TODO MENSAGEM SUCESSO!
        logger.info("Fim do processo de compra! ID: " + compra.getId() + ", user: " + compra.getUsuario().getId());
        
         showMessage(new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Sucessp",
                        ("Compra realizada com sucesso. Obrigado.")));
        
        compra = new Compra();
        return "sucesso";
    }

    /**
     * Classe usada no método compra, para ser armazenada em um map junto
     * com o id do produto
     * 
     * @author Carlos A Becker
     * @see ManagedCompra comprar();
     */
    private class itemMap {

        private Integer quantidadeEstoque;
        private Integer quantidadeCarrinho;

        public itemMap(Integer quantidadeEstoque, Integer quantidadeCarrinho) {
            this.quantidadeCarrinho = quantidadeCarrinho;
            this.quantidadeEstoque = quantidadeEstoque;
        }

        public Integer getQuantidadeCarrinho() {
            return quantidadeCarrinho;
        }

        public void setQuantidadeCarrinho(Integer quantidadeCarrinho) {
            this.quantidadeCarrinho = quantidadeCarrinho;
        }

        public Integer getQuantidadeEstoque() {
            return quantidadeEstoque;
        }

        public void setQuantidadeEstoque(Integer quantidadeEstoque) {
            this.quantidadeEstoque = quantidadeEstoque;
        }
    }
}
