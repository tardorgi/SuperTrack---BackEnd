package com.backend.supertrack.controller;

import com.backend.supertrack.model.Produtos;
import com.backend.supertrack.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutosController {

    @Autowired
    private ProdutosRepository repository;

    // Get all products
    @GetMapping
    public List<Produtos> getAllProdutos() {
        return repository.findAll();
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Produtos> createProduto(@RequestBody Produtos produto) {
        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Descrição obrigatória
        }
        if (produto.getQuantidade() == null || produto.getQuantidade() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Quantidade inválida
        }
        if (produto.getValor() == null || produto.getValor() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Valor inválido
        }

        // Salva o produto e retorna a resposta com status 201 (CREATED)
        Produtos savedProduto = repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Produtos> getProdutoById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(produto -> ResponseEntity.ok(produto)) // Retorna 200 OK se encontrado
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Produtos> updateProduto(@PathVariable Integer id, @RequestBody Produtos produto) {
        return repository.findById(id).map(existing -> {
            existing.setDescricao(produto.getDescricao());
            existing.setQuantidade(produto.getQuantidade());
            existing.setValor(produto.getValor());
            Produtos updatedProduto = repository.save(existing);
            return ResponseEntity.ok(updatedProduto); // Retorna o produto atualizado com status 200 OK
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable Integer id) {
        return repository.findById(id).map(produto -> {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content após excluir
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }
}
