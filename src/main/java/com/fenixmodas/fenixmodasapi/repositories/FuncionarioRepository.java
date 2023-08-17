package com.fenixmodas.fenixmodasapi.repositories;

import com.fenixmodas.fenixmodasapi.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByLogin(String login);
}
