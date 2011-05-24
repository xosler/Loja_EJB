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
    AVENTAL(1, "Avental"), 
    BLUSA(2, "Blusa"), 
    CAMISETA(3, "Camiseta"), 
    CAMISA(4, "Camisa"), 
    CALCA(5, "Calça"), 
    JEANS(6, "Jeans"), 
    MOLETON(7, "Moleton"), 
    CASACO(8, "Casaco"), 
    JAQUETA(9, "Jaqueta"), 
    TERNO(10, "Terno"), 
    PALETO(11, "Paletó");
    
    private final int id;
    private final String nome;
    
    private Categoria(int id, String nome)
    {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    } 
}
