package com.infnet.at.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cotacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fonteCotacao;
    private String valor;
    @ManyToOne
    private Produto produto;


    public Cotacao() {
    }

    public Cotacao(String fonteCotacao, String valor) {
        this.fonteCotacao = fonteCotacao;
        this.valor = valor;
    }

    public Cotacao(String fonteCotacao, String valor, Produto produto) {
        this.fonteCotacao = fonteCotacao;
        this.valor = valor;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFonteCotacao() {
        return fonteCotacao;
    }

    public void setFonteCotacao(String fonteCotacao) {
        this.fonteCotacao = fonteCotacao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Cotacao{" +
                "id=" + id +
                ", fonteCotacao='" + fonteCotacao + '\'' +
                ", valor=" + valor +
                ", produto=" + produto +
                '}';
    }
}
