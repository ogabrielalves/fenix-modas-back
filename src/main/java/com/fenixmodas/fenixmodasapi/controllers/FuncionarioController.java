package com.fenixmodas.fenixmodasapi.controllers;

import com.fenixmodas.fenixmodasapi.dtos.funcionario.DadosAtualizacaoFuncionario;
import com.fenixmodas.fenixmodasapi.dtos.funcionario.ListagemFuncionarios;
import com.fenixmodas.fenixmodasapi.dtos.funcionario.NovoFuncionario;
import com.fenixmodas.fenixmodasapi.services.FuncionarioService;
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
@RequestMapping("/funcionarios")
@Tag(name = "Funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarNovoFuncionario(@RequestBody @Valid NovoFuncionario novoFuncionario, UriComponentsBuilder uriBuilder) {
        return service.criarNovoFuncionario(novoFuncionario, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemFuncionarios>> listarFuncionarios(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.listarFuncionarios(paginacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluirFuncionario(@PathVariable Long id) {
        return service.excluirFuncionario(id);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizarFuncionaro(@RequestBody @Valid DadosAtualizacaoFuncionario dados) {
        return service.atualizarFuncionario(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharFuncionario(@PathVariable Long id) {
        return service.detalharFuncionario(id);
    }
}
