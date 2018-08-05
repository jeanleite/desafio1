package com.jm.desafio1;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author michel
 */
public class Produto {
    
    private String link;
    
    private String nome;
    
    private String categoria;
    
    private Float peso;
    
    private String cor;
    
    private Float desconto;
    
    private Float rating;
    
    private BigDecimal preco;

    public Produto() {
    }

    public Produto(String link, String nome, String categoria, Float peso, String cor, Float desconto, Float rating, BigDecimal preco) {
        this.link = link;
        this.nome = nome;
        this.categoria = categoria;
        this.peso = peso;
        this.cor = cor;
        this.desconto = desconto;
        this.rating = rating;
        this.preco = preco;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.link);
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.categoria);
        hash = 67 * hash + Objects.hashCode(this.peso);
        hash = 67 * hash + Objects.hashCode(this.cor);
        hash = 67 * hash + Objects.hashCode(this.desconto);
        hash = 67 * hash + Objects.hashCode(this.rating);
        hash = 67 * hash + Objects.hashCode(this.preco);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        if (!Objects.equals(this.peso, other.peso)) {
            return false;
        }
        if (!Objects.equals(this.desconto, other.desconto)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.preco, other.preco)) {
            return false;
        }
        return true;
    }
    
}
