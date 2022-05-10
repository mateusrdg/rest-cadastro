package br.com.cadastro.dto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private String id;
    private String nome;
    private String sku;
    private Integer quantidade;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", sku='" + sku + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
