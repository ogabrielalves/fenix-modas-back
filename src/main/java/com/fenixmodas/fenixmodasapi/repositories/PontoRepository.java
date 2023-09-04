package com.fenixmodas.fenixmodasapi.repositories;

import com.fenixmodas.fenixmodasapi.models.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface PontoRepository extends JpaRepository<Ponto, Long> {
    @Query("SELECT p FROM pontos p WHERE DATE(p.horarioPonto)=?1 AND p.funcionario.id=?2")
    List<Ponto> existsByHorarioPontoAndFuncionario(LocalDate dataAtual, Long idFuncionario);

    @Query("SELECT p FROM pontos p WHERE DATE(p.horarioPonto)=?1 AND p.funcionario.id=?2")
    Ponto selecionaHora(LocalDate dataAtual, Long idFuncionario);

}
