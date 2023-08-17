package com.fenixmodas.fenixmodasapi.models;

import com.fenixmodas.fenixmodasapi.dtos.request.funcionario.NovoFuncionario;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "funcionarios")
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sobrenome")
    private String sobrenome;
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Column(name = "salario")
    private Double salario;
    @Column(name = "login")
    private String login;
    @Column(name = "senha")
    private String senha;

    public Funcionario(NovoFuncionario novoFuncionario) {
        this.nome = novoFuncionario.nome();
        this.sobrenome = novoFuncionario.sobrenome();
        this.cpf = novoFuncionario.cpf();
        this.salario = novoFuncionario.salario();
        this.login = novoFuncionario.login();
        this.senha = novoFuncionario.senha();
    }
}
