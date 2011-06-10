/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author carlos
 */
@Entity
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double valorTotal;
    @OneToOne
    private Carrinho carrinho;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCompra;

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
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
        final Compra other = (Compra) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.valorTotal != other.valorTotal && (this.valorTotal == null || !this.valorTotal.equals(other.valorTotal))) {
            return false;
        }
        if (this.carrinho != other.carrinho && (this.carrinho == null || !this.carrinho.equals(other.carrinho))) {
            return false;
        }
        if (this.dataCompra != other.dataCompra && (this.dataCompra == null || !this.dataCompra.equals(other.dataCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.valorTotal != null ? this.valorTotal.hashCode() : 0);
        hash = 67 * hash + (this.carrinho != null ? this.carrinho.hashCode() : 0);
        hash = 67 * hash + (this.dataCompra != null ? this.dataCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.geekvigarista.pojo.Compra[ id=" + id + " ]";
    }
}
