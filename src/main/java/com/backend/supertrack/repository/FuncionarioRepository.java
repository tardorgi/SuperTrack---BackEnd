package com.backend.supertrack.repository;

import com.backend.supertrack.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByNomeAndSenha(String nome, String senha);
}

