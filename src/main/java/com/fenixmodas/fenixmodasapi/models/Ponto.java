package com.fenixmodas.fenixmodasapi.models;

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

    @Column(name = "id_funcionario")
    @ManyToMany
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_registro")
    private TipoRegistro tipoRegistro;

    @Column(name = "horario_ponto")
    private Instant horarioPonto;
}
