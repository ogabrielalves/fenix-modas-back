package com.fenixmodas.fenixmodasapi.repositories;

import com.fenixmodas.fenixmodasapi.models.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PontoRepository extends JpaRepository<Ponto, Long> {
    @Query(value = "SELECT * FROM ponto WHERE data = CURDATE() AND tipoRegistro = 'ENTRADA' AND idFuncionario =?1")
    List<Ponto> findPontoByIdFuncionarioAndTipoRegistro(Long idFuncionario);

    @Query(value = "SELECT * FROM ponto WHERE data = CURDATE() AND tipoRegistro = 'ENTRADA' AND idFuncionario =?1")
    Boolean existsByPontoByIdFuncionarioAndTipoRegistro(Long idFuncionario);
}
