package com.backend.supertrack.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO")
    private Integer idFuncionario;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "ANIVERSARIO")
    private LocalDate aniversario;

    @Column(name = "ADMISSAO")
    private LocalDate admissao;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "FUNCAO")
    private String funcao;

    // Getters and Setters
    public Integer getIdFuncionario() {
        return idFuncionario;
    }
    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getAniversario() {
        return aniversario;
    }
    public void setAniversario(LocalDate aniversario) {
        this.aniversario = aniversario;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }
    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFuncao() {
        return funcao;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
