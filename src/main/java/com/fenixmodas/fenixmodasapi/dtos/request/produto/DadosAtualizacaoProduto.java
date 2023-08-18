package com.fenixmodas.fenixmodasapi.dtos.request.produto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(
        @NotNull(message = "O id do produto não pode ser nulo")
        Long id,
        Double precoVenda,
        Double precoCompra,
        Integer quantidade
) {
}
