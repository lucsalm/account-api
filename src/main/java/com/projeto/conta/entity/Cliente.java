package com.projeto.conta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
@SequenceGenerator(name = "cliente_seq", sequenceName = "seq_cliente_id", allocationSize = 1)
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    private Integer id;

    private Integer saldo;

    private Integer limite;

    @Fetch(FetchMode.SELECT)
    @OrderBy("realizada_em desc")
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transacao> transacoes;


    public void addTransacao(final Transacao transacao) {
        if (!transacoes.isEmpty()) {
            transacoes.add(transacao);
        } else {
            transacoes = List.of(transacao);
        }
    }

    public void debitar(Integer valor) {
        saldo -= valor;
    }

    public void creditar(Integer valor) {
        saldo += valor;
    }


}
