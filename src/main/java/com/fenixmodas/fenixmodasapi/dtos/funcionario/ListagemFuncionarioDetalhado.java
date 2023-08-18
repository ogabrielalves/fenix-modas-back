package com.fenixmodas.fenixmodasapi.dtos.funcionario;

import com.fenixmodas.fenixmodasapi.models.Funcionario;

public record ListagemFuncionarioDetalhado(
        Long id,
        String nome,
        String sobrenome,
        String login,
        Double salario,
        String cpf
) {
    public ListagemFuncionarioDetalhado(Funcionario funcionario) {
        this(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getLogin(),
                funcionario.getSalario(),
                funcionario.getCpf()
        );
    }
}
