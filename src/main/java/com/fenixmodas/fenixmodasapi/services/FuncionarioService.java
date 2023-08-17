package com.fenixmodas.fenixmodasapi.services;

import com.fenixmodas.fenixmodasapi.dtos.request.funcionario.ListagemFuncionarios;
import com.fenixmodas.fenixmodasapi.dtos.request.funcionario.NovoFuncionario;
import com.fenixmodas.fenixmodasapi.models.Funcionario;
import com.fenixmodas.fenixmodasapi.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public ResponseEntity<?> criarNovoFuncionario(NovoFuncionario dados, UriComponentsBuilder uriBuilder) {
        Funcionario novoFuncionario = new Funcionario(dados);
        if (funcionarioRepository.existsByCpf(dados.cpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado!");
        }

        if (funcionarioRepository.existsByLogin(dados.login())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login já cadastrado, escolha outro!");
        }

        funcionarioRepository.save(novoFuncionario);
        URI uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(novoFuncionario.getId()).toUri();
        return ResponseEntity.created(uri).body(novoFuncionario);
    }

    public ResponseEntity<Page<ListagemFuncionarios>> listarFuncionarios(Pageable paginacao) {
        Page<ListagemFuncionarios> listagemFuncionarios = funcionarioRepository.findAll(paginacao).map(ListagemFuncionarios::new);
        return ResponseEntity.ok(listagemFuncionarios);
    }

    public ResponseEntity<?> excluirFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
