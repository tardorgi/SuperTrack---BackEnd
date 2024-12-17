package com.backend.supertrack.model;

import jakarta.persistence.*;

@Entity
public class Produtos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO") // Nome da coluna no banco de dados
    private Integer idProduto;

    @Column(name = "DESCRICAO") // Nome da coluna no banco de dados
    private String descricao;

    @Column(name = "QUANTIDADE") // Nome da coluna no banco de dados
    private Integer quantidade;

    @Column(name = "VALOR") // Nome da coluna no banco de dados
    private Double valor;

    // Getters and Setters
    public Integer getIdProduto() { 
        return idProduto; 
    }
    public void setIdProduto(Integer idProduto) { 
        this.idProduto = idProduto; 
    }

    public String getDescricao() { 
        return descricao; 
    }
    public void setDescricao(String descricao) { 
        this.descricao = descricao; 
    }

    public Integer getQuantidade() { 
        return quantidade; 
    }
    public void setQuantidade(Integer quantidade) { 
        this.quantidade = quantidade; 
    }

    public Double getValor() { 
        return valor; 
    }
    public void setValor(Double valor) { 
        this.valor = valor; 
    }
}
