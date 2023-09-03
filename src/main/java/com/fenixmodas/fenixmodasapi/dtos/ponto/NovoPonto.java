package com.fenixmodas.fenixmodasapi.dtos.ponto;

import com.fenixmodas.fenixmodasapi.enums.TipoRegistro;
import com.fenixmodas.fenixmodasapi.models.Funcionario;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.ZonedDateTime;

public record NovoPonto(
        Funcionario funcionario,
        TipoRegistro tipoRegistro,
        Instant horarioPonto
) {
}
