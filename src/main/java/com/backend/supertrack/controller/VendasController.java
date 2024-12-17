package com.backend.supertrack.controller;

import com.backend.supertrack.model.Vendas;
import com.backend.supertrack.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class VendasController {

    @Autowired
    private VendasRepository vendasRepository;

    @GetMapping("/vendas")
    public List<Vendas> getAllVendas() {
        return vendasRepository.findAll();
    }
}
