package com.fenixmodas.fenixmodasapi.controllers;

import com.fenixmodas.fenixmodasapi.dtos.produto.ListagemProdutosEmEstoque;
import com.fenixmodas.fenixmodasapi.services.EstoqueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
@Tag(name = "Estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping
    public ResponseEntity<Page<ListagemProdutosEmEstoque>> listarProdutosEmEstoque(Pageable paginacao) {
        return service.listarProdutosEmEstoque(paginacao);
    }
}
