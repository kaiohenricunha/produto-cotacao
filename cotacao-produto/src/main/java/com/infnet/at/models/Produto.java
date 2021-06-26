package com.infnet.at.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Produto {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String imagem;
    @OneToMany
    private List<Cotacao> cotacoes;

    public Produto() {
    }

    public Produto(String nome, String imagem, List<Cotacao> cotacoes) {
        this.nome = nome;
        this.imagem = imagem;
        this.cotacoes = cotacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Cotacao> getCotacoes() {
        return cotacoes;
    }

    public void setCotacoes(List<Cotacao> cotacoes) {
        this.cotacoes = cotacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", imagem='" + imagem + '\'' +
                ", cotacoes=" + cotacoes +
                '}';
    }
}
