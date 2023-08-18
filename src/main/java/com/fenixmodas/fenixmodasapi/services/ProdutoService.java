package com.fenixmodas.fenixmodasapi.services;

import com.fenixmodas.fenixmodasapi.dtos.request.produto.DadosAtualizacaoProduto;
import com.fenixmodas.fenixmodasapi.dtos.request.produto.ListagemProdutos;
import com.fenixmodas.fenixmodasapi.dtos.request.produto.NovoProduto;
import com.fenixmodas.fenixmodasapi.models.Produto;
import com.fenixmodas.fenixmodasapi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<?> criarNovoProduto(NovoProduto dados, UriComponentsBuilder uriBuilder) {
        Produto novoProduto = new Produto(dados);
        if (produtoRepository.existsByCodigo(dados.codigo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Código já existente!");
        }
        produtoRepository.save(novoProduto);
        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(novoProduto.getId()).toUri();
        return ResponseEntity.created(uri).body(novoProduto);
    }

    public ResponseEntity<Page<ListagemProdutos>> listarProdutos(Pageable paginacao) {
        Page<ListagemProdutos> listagemProdutos = produtoRepository.findAll(paginacao).map(ListagemProdutos::new);
        return ResponseEntity.ok(listagemProdutos);
    }

    public ResponseEntity<Produto> listarPorId(String codigo) {
        Optional<Produto> produtoOptional = produtoRepository.findByCodigo(codigo);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoRepository.getReferenceByCodigo(codigo);
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> atualizarProduto(DadosAtualizacaoProduto dados) {
        Produto produtoAtualizado = produtoRepository.getReferenceById(dados.id());
        produtoAtualizado.atualizarProduto(dados);
        return ResponseEntity.ok(new ListagemProdutos(produtoAtualizado));
    }

    public ResponseEntity<?> deletarProduto(Long id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
