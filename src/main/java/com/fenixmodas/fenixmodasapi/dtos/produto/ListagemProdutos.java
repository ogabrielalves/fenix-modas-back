package com.fenixmodas.fenixmodasapi.dtos.produto;

import com.fenixmodas.fenixmodasapi.models.Produto;

public record ListagemProdutos(
        Long id,
        String codigo,
        String nome,
        String categoria,
        Double precoVenda,
        Double precoCompra,
        Integer quantidade,
        Boolean emEstoque
) {
    public ListagemProdutos(Produto produto) {
        this(
                produto.getId(),
                produto.getCodigo(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPrecoVenda(),
                produto.getPrecoCompra(),
                produto.getQuantidade(),
                produto.getEmEstoque()
        );
    }
}
