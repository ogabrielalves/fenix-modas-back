package com.fenixmodas.fenixmodasapi.controllers;

import com.fenixmodas.fenixmodasapi.dtos.request.produto.ListagemProdutos;
import com.fenixmodas.fenixmodasapi.dtos.request.produto.NovoProduto;
import com.fenixmodas.fenixmodasapi.services.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarNovoProduto(@RequestBody @Valid NovoProduto dados, UriComponentsBuilder uriBuilder) {
        return service.criarNovoProduto(dados, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemProdutos>> listarProdutos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.listarProdutos(paginacao);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> listarPorId(@PathVariable String codigo) {
        return service.listarPorId(codigo);
    }
}
