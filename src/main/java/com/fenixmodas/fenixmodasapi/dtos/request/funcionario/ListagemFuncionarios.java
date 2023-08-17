package com.fenixmodas.fenixmodasapi.dtos.request.funcionario;

import com.fenixmodas.fenixmodasapi.models.Funcionario;

public record ListagemFuncionarios(
        Long id,
        String nome,
        String sobrenome,
        String login
) {
    public ListagemFuncionarios(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getLogin());
    }
}
