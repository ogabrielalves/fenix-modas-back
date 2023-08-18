package com.fenixmodas.fenixmodasapi.dtos.funcionario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosAtualizacaoFuncionario(
        @NotNull(message = "O id não pode ser nulo")
        Long id,
        String senha,
        @Positive(message = "O salário deve ser maior que 0.00.")
        Double salario
) {
}
