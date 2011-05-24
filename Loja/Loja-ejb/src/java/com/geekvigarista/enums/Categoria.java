/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geekvigarista.enums;

/**
 *
 * @author carlos
 */
public enum Categoria {
    ADORNO("Adornos"),
    BLUSA("Blusas"), 
    CAMISETA("Camisetas"), 
    CAMISA("Camisas"),
    CALCA("Calças"), 
    CASACO("Casacos"), 
    JAQUETA("Jaquetas"),
    OUTRO("Outros");
    
    private final String nome;
    
    private Categoria(String nome)
    {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    } 
}
