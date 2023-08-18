package com.fenixmodas.fenixmodasapi.services;

import com.fenixmodas.fenixmodasapi.dtos.produto.ListagemProdutos;
import com.fenixmodas.fenixmodasapi.dtos.produto.ListagemProdutosEmEstoque;
import com.fenixmodas.fenixmodasapi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<Page<ListagemProdutosEmEstoque>> listarProdutosEmEstoque(Pageable paginacao) {
        Page<ListagemProdutosEmEstoque> listagemProdutos = produtoRepository.findAllByEmEstoqueTrue(paginacao).map(ListagemProdutosEmEstoque::new);
        return ResponseEntity.ok(listagemProdutos);
    }
}
