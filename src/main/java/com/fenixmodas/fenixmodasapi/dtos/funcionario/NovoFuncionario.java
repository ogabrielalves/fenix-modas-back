package com.fenixmodas.fenixmodasapi.dtos.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record NovoFuncionario(
        @NotBlank(message = "O nome do funcionário deve ser preenchido.")
        @Length(min = 2, message = "O nome do funcionário deve ter nó minimo 2 caracteres.")
        String nome,
        @NotBlank(message = "O sobrenome do funcionário deve ser preenchido.")
        @Length(min =2, message = "O sobrenome do funcionário deve ter nó minimo 2 caracteres.")
        String sobrenome,
        @CPF(message = "Deve-se preencher um CPF válido.")
        String cpf,
        @NotNull(message = "O salário do funcionario não pode ser nulo.")
        @Positive(message = "O valor do salário deve ser maior que 0.00")
        Double salario,
        @NotBlank(message = "O login não pode ser nulo.")
        @Length(min = 2, message = "O login do funcionário deve ter nó minimo 2 caracteres.")
        String login,
        @NotBlank(message = "A senha não pode ser nula.")
        @Length(min = 6,message = "A senha do funcionário deve ter no minimo 6 caracteres.")
        String senha
) {
}
