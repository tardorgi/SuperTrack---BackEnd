package com.backend.supertrack.controller;

import com.backend.supertrack.model.Funcionario;
import com.backend.supertrack.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "http://localhost:3000")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    // Get all employees
    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return repository.findAll();
    }

    // Create a new employee
    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {
        if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Nome obrigatório
        }
        if (funcionario.getAniversario() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Aniversário obrigatório
        }
        if (funcionario.getAdmissao() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Admissão obrigatória
        }
        if (funcionario.getSenha() == null || funcionario.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Senha obrigatória
        }
        if (funcionario.getFuncao() == null || funcionario.getFuncao().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Função obrigatória
        }

        Funcionario savedFuncionario = repository.save(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFuncionario);
    }

    // Get an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok) // Retorna 200 OK se encontrado
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    // Update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        return repository.findById(id).map(existing -> {
            existing.setNome(funcionario.getNome());
            existing.setAniversario(funcionario.getAniversario());
            existing.setAdmissao(funcionario.getAdmissao());
            existing.setSenha(funcionario.getSenha());
            existing.setFuncao(funcionario.getFuncao());
            Funcionario updatedFuncionario = repository.save(existing);
            return ResponseEntity.ok(updatedFuncionario); // Retorna o funcionário atualizado com status 200 OK
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFuncionario(@PathVariable Integer id) {
        return repository.findById(id).map(funcionario -> {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content após excluir
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Funcionario loginFuncionario) {
        // Verificar credenciais mestre
        if ("admin".equals(loginFuncionario.getNome()) && "admin".equals(loginFuncionario.getSenha())) {
            Funcionario admin = new Funcionario();
            admin.setIdFuncionario(0); // ID fictício para o admin
            admin.setNome("admin");
            admin.setFuncao("Administrador");
            return ResponseEntity.ok(admin); // Retorna o "admin" autenticado
        }

        // Verificar no banco de dados
        if (loginFuncionario.getNome() != null && loginFuncionario.getSenha() != null) {
            Optional<Funcionario> funcionarioOptional = repository.findByNomeAndSenha(loginFuncionario.getNome(), loginFuncionario.getSenha());
            if (funcionarioOptional.isPresent()) {
                return ResponseEntity.ok(funcionarioOptional.get()); // Usuário autenticado
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida"); // Senha inválida
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome e senha são obrigatórios para login");
        }
    }
}
