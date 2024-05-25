package com.projeto.conta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transacao")
@SequenceGenerator(name = "transacao_seq", sequenceName = "seq_transacao_id")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transacao_seq")
    private Integer id;

    private Integer valor;

    private String tipo;

    private String descricao;

    @Builder.Default
    @Column(name = "realizada_em")
    private LocalDateTime realizadaEm = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

}
