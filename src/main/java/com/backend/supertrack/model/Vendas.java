package com.backend.supertrack.model;

import jakarta.persistence.*;

@Entity
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENDA")
    private Integer idVenda;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO")
    private Produtos produto;

    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "ID_FUNCIONARIO")
    private Funcionario funcionario;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Column(name = "CLIENTE")
    private String cliente;

    // Getters and Setters
    public Integer getIdVenda() {
        return idVenda;
    }
    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Produtos getProduto() {
        return produto;
    }
    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
