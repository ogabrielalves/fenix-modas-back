package com.fenixmodas.fenixmodasapi.models;

import com.fenixmodas.fenixmodasapi.dtos.request.produto.DadosAtualizacaoProduto;
import com.fenixmodas.fenixmodasapi.dtos.request.produto.NovoProduto;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "produtos")
@Table(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", unique = true)
    private String codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "preco_venda")
    private Double precoVenda;

    @Column(name = "preco_compra")
    private Double precoCompra;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "em_estoque")
    private Boolean emEstoque;

    public Produto(NovoProduto novoProduto) {
        this.nome = novoProduto.nome();
        this.categoria = novoProduto.categoria();
        this.codigo = novoProduto.codigo();
        this.precoCompra = novoProduto.precoCompra();
        this.precoVenda = novoProduto.precoVenda();
        this.quantidade = novoProduto.quantidade();
        this.emEstoque = novoProduto.quantidade() > 0;
    }

    public void atualizarProduto(DadosAtualizacaoProduto dados) {
        if (dados.precoCompra() != null) {
            this.precoCompra = dados.precoCompra();
        }

        if (dados.precoVenda() != null) {
            this.precoVenda = dados.precoVenda();
        }

        if (dados.quantidade() != null) {
            this.quantidade = dados.quantidade();
            this.emEstoque = dados.quantidade() > 0;
        }
    }
}
