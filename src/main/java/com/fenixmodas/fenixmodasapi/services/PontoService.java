package com.fenixmodas.fenixmodasapi.services;

import com.fenixmodas.fenixmodasapi.dtos.ponto.HorasPontoBatidoMes;
import com.fenixmodas.fenixmodasapi.dtos.ponto.NovoPonto;
import com.fenixmodas.fenixmodasapi.enums.TipoRegistro;
import com.fenixmodas.fenixmodasapi.models.Funcionario;
import com.fenixmodas.fenixmodasapi.models.Ponto;
import com.fenixmodas.fenixmodasapi.repositories.FuncionarioRepository;
import com.fenixmodas.fenixmodasapi.repositories.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZonedDateTime;
import java.time.Duration;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PontoService {

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public ResponseEntity<?> pontoEntrada(Long idFuncionario, UriComponentsBuilder uriBuilder) {
        Instant instanteAtual = Instant.now();
        LocalDate dataAtual = instanteAtual.atZone(ZoneId.systemDefault()).toLocalDate();
        List<Ponto> pontoEntrada = pontoRepository.existsByHorarioPontoAndFuncionario(dataAtual, idFuncionario);

        if (pontoEntrada.isEmpty()) {
            Ponto novoPonto = new Ponto(baterPonto(TipoRegistro.ENTRADA, idFuncionario));
            pontoRepository.save(novoPonto);
            URI uri = uriBuilder.path("/bater-ponto-entrada/{id}").buildAndExpand(novoPonto.getId()).toUri();
            return ResponseEntity.created(uri).body(novoPonto);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("O usuário já bateu o ponto de entrada.");
    }

    public ResponseEntity<?> pontoSaida(Long idFuncionario, UriComponentsBuilder uriBuilder) {
        Instant instanteAtual = Instant.now();
        LocalDate dataAtual = instanteAtual.atZone(ZoneId.systemDefault()).toLocalDate();
        List<Ponto> pontoEntrada = pontoRepository.existsByHorarioPontoAndFuncionario(dataAtual, idFuncionario);

        if (!pontoEntrada.isEmpty()) {
            Ponto novoPonto = new Ponto(baterPonto(TipoRegistro.SAIDA, idFuncionario));
            pontoRepository.save(novoPonto);
            URI uri = uriBuilder.path("/bater-ponto-saida/{id}").buildAndExpand(novoPonto.getId()).toUri();
            return ResponseEntity.created(uri).body(novoPonto);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("O usuário não bateu o ponto de entrada.");
    }

    public ResponseEntity<?> obterHorasPorDia(Long idFuncionario, LocalDate data) {
        List<Ponto> pontoBatido = pontoRepository.existsByHorarioPontoAndFuncionario(data, idFuncionario);

        if (pontoBatido.size() == 2) {
            Ponto entrada = pontoBatido.get(0);
            Ponto saida = pontoBatido.get(1);

            ZonedDateTime entradaDataHora = entrada.getHorarioPonto().atZone(ZoneId.systemDefault());
            ZonedDateTime saidaDataHora = saida.getHorarioPonto().atZone(ZoneId.systemDefault());
            Duration diferenca = Duration.between(entradaDataHora, saidaDataHora);
            long horas = diferenca.toHours();
            long minutos = diferenca.minusHours(horas).toMinutes();
            return ResponseEntity.ok().body(horas + "h" + minutos + "m");
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> obterHorasMes(Long idFuncionario, Integer mes, Integer ano) {
        long minutos = 0;
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 1; i < 31; i++) {
            String data = String.format("%d-%02d-%02d", ano, mes, i);
            LocalDate dataFormatada = LocalDate.parse(data, formatarData);
            minutos += obterHorasDias(idFuncionario, dataFormatada);
        }
        Funcionario funcionario = funcionarioRepository.getReferenceById(idFuncionario);

        long horas = minutos / 60;
        long minutosRestantes = minutos % 60;

        HorasPontoBatidoMes pontoBatidoMes = new HorasPontoBatidoMes(idFuncionario, funcionario.getNome(), horas, minutosRestantes);
        return ResponseEntity.ok().body(pontoBatidoMes);
    }

    private NovoPonto baterPonto(TipoRegistro tipoRegistro, Long idFuncionario) {
        Funcionario funcionario = funcionarioRepository.getReferenceById(idFuncionario);
        Instant horario = Instant.now();
        return new NovoPonto(funcionario, tipoRegistro, horario);
    }

    private long obterHorasDias(Long idFuncionario, LocalDate data) {
        List<Ponto> pontoBatido = pontoRepository.existsByHorarioPontoAndFuncionario(data, idFuncionario);

        if (pontoBatido.size() == 2) {
            Ponto entrada = pontoBatido.get(0);
            Ponto saida = pontoBatido.get(1);

            ZonedDateTime entradaDataHora = entrada.getHorarioPonto().atZone(ZoneId.systemDefault());
            ZonedDateTime saidaDataHora = saida.getHorarioPonto().atZone(ZoneId.systemDefault());
            Duration diferenca = Duration.between(entradaDataHora, saidaDataHora);
            long minutos = diferenca.toMinutes();
            return minutos;
        }
        return 0;
    }
}
