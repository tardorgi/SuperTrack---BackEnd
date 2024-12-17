package com.backend.supertrack.repository;

import com.backend.supertrack.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos, Integer> { }
