package com.fenixmodas.fenixmodasapi.dtos.produto;

import com.fenixmodas.fenixmodasapi.models.Produto;

public record ListagemProdutosEmEstoque(
        Long id,
        String nome,
        String codigo,
        Integer quantidade
) {
    public ListagemProdutosEmEstoque(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getCodigo(), produto.getQuantidade());
    }
}
