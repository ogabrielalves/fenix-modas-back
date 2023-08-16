package com.fenixmodas.fenixmodasapi.repositories;

import com.fenixmodas.fenixmodasapi.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByCodigo(String codigo);
    Produto getReferenceByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
}
