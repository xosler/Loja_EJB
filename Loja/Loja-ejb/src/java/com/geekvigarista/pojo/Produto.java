/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author carlos
 */
@Entity
public class Produto implements Serializable {

    @ManyToMany(mappedBy = "produtos")
    private List<Carrinho> carrinhos;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double preco;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date dataCadastro;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = true)
    private Date dataVencimentoOferta;
    @Column(nullable = false)
    private int quantidadeEmEstoque;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @ManyToMany
    private List<Categoria> categorias;
    private String imagem;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public String getImagem() {
        if (imagem == null || imagem.trim().equals("")) {
            return "/resources/images/sem-foto.gif";
        }
        return imagem;
    }

    public void setImagens(String imagem) {
        this.imagem = imagem;
    }

    public List<Carrinho> getCarrinhos() {
        return carrinhos;
    }

    public void setCarrinhos(List<Carrinho> carrinhos) {
        this.carrinhos = carrinhos;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataVencimentoOferta() {
        return dataVencimentoOferta;
    }

    public void setDataVencimentoOferta(Date dataVencimentoOferta) {
        this.dataVencimentoOferta = dataVencimentoOferta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoLabel() {
        return descricao != null ? descricao.replaceAll("\n", "<br />") : "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.carrinhos != other.carrinhos && (this.carrinhos == null || !this.carrinhos.equals(other.carrinhos))) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.preco != other.preco && (this.preco == null || !this.preco.equals(other.preco))) {
            return false;
        }
        if (this.dataCadastro != other.dataCadastro && (this.dataCadastro == null || !this.dataCadastro.equals(other.dataCadastro))) {
            return false;
        }
        if (this.dataVencimentoOferta != other.dataVencimentoOferta && (this.dataVencimentoOferta == null || !this.dataVencimentoOferta.equals(other.dataVencimentoOferta))) {
            return false;
        }
        if (this.quantidadeEmEstoque != other.quantidadeEmEstoque) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        if (this.categorias != other.categorias && (this.categorias == null || !this.categorias.equals(other.categorias))) {
            return false;
        }
        if ((this.imagem == null) ? (other.imagem != null) : !this.imagem.equals(other.imagem)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.carrinhos != null ? this.carrinhos.hashCode() : 0);
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 73 * hash + (this.preco != null ? this.preco.hashCode() : 0);
        hash = 73 * hash + (this.dataCadastro != null ? this.dataCadastro.hashCode() : 0);
        hash = 73 * hash + (this.dataVencimentoOferta != null ? this.dataVencimentoOferta.hashCode() : 0);
        hash = 73 * hash + this.quantidadeEmEstoque;
        hash = 73 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 73 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
        hash = 73 * hash + (this.categorias != null ? this.categorias.hashCode() : 0);
        hash = 73 * hash + (this.imagem != null ? this.imagem.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return id + ", " + nome;
    }
}
