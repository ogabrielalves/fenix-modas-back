package com.fenixmodas.fenixmodasapi.models;

import com.fenixmodas.fenixmodasapi.dtos.ponto.NovoPonto;
import com.fenixmodas.fenixmodasapi.enums.TipoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity(name = "pontos")
@Table(name = "pontos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ponto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_registro")
    private TipoRegistro tipoRegistro;

    @Column(name = "horario_ponto")
    private Instant horarioPonto;

    public Ponto(NovoPonto dados) {
        this.funcionario = dados.funcionario();
        this.tipoRegistro = dados.tipoRegistro();
        this.horarioPonto = dados.horarioPonto();
    }
}
