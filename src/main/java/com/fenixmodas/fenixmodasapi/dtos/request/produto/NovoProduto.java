package com.fenixmodas.fenixmodasapi.dtos.request.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record NovoProduto(
        @NotBlank(message = "O código não pode ser nulo.")
        String codigo,
        @NotBlank(message = "O nome não pode ser nulo.")
        String nome,
        String categoria,
        @NotNull(message = "O preço da venda não pode ser nulo.")
        @Positive(message = "O preço deve ser um valor positivo.")
        Double precoVenda,
        Double precoCompra,
        @NotNull(message = "A quantidade deve ser inserida.")
        @PositiveOrZero(message = "A quantidade não pode ser menor que 0.")
        Integer quantidade
) {
}
