package com.projeto.conta.entity;

import com.projeto.conta.constants.Tipo;
import com.projeto.conta.exception.UnprocessableEntityException;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@Entity
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

    public Cliente() {
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public Integer getLimite() {
        return limite;
    }

    public void executaTransacao(Transacao transacao) {
        switch (transacao.getTipo()) {
            case Tipo.CREDITO -> executaCredito(transacao.getValor());
            case Tipo.DEBITO -> executaDebito(transacao.getValor());
        }
        transacoes.add(transacao);
    }

    private void executaDebito(Integer debito) {
        if (!temSaldoSuficiente(debito))
            throw new UnprocessableEntityException();
        this.saldo -= debito;
    }

    private void executaCredito(Integer valor) {
        this.saldo += valor;
    }

    private boolean temSaldoSuficiente(Integer debito) {
        return debito <= this.saldo + this.limite;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}
