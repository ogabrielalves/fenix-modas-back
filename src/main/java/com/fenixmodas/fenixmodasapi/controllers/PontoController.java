package com.fenixmodas.fenixmodasapi.controllers;

import com.fenixmodas.fenixmodasapi.services.PontoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/pontos")
@Tag(name = "Pontos")
public class PontoController {

    @Autowired
    private PontoService service;

    @PostMapping("/bater-ponto-entrada/{id}")
    public ResponseEntity<?> baterPontoEntrada(UriComponentsBuilder uriBuilder, @PathVariable Long id) {
        return service.pontoEntrada(id, uriBuilder);
    }

    @PostMapping("/bater-ponto-saida/{id}")
    public ResponseEntity<?> baterPontoSaida(UriComponentsBuilder uriBuilder, @PathVariable Long id) {
        return service.pontoSaida(id, uriBuilder);
    }

    @GetMapping("/obter-horas-dia/{id}/{data}")
    public ResponseEntity<?> obterHorasPorDia(@PathVariable Long id, LocalDate data) {
        return service.obterHorasPorDia(id, data);
    }

    @GetMapping("/obter-horas-mes/{id}/{ano}/{mes}")
    public ResponseEntity<?> obterHorasMes(Long idFuncionario, Integer mes, Integer ano) {
        return service.obterHorasMes(idFuncionario, mes, ano);
    }
}
