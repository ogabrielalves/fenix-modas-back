package com.fenixmodas.fenixmodasapi.services;

import com.fenixmodas.fenixmodasapi.dtos.ponto.NovoPonto;
import com.fenixmodas.fenixmodasapi.enums.TipoRegistro;
import com.fenixmodas.fenixmodasapi.models.Funcionario;
import com.fenixmodas.fenixmodasapi.repositories.FuncionarioRepository;
import com.fenixmodas.fenixmodasapi.repositories.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class PontoService {

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public ResponseEntity<?> pontoEntrada(TipoRegistro tipoRegistro, Long idFuncionario) {
        baterPonto(tipoRegistro, idFuncionario);
    }

    public void baterPonto(TipoRegistro tipoRegistro, Long idFuncionario) {
        Funcionario funcionario = funcionarioRepository.getReferenceById(idFuncionario);
        Instant horario = Instant.now();
        NovoPonto novoPonto = new NovoPonto(funcionario, tipoRegistro, horario);
    }
}
