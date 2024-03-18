package com.projeto.conta.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacao")
@SequenceGenerator(name = "transacao_seq", sequenceName = "seq_transacao_id")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transacao_seq")
    private Integer id;

    private Integer valor;

    private String tipo;

    private String descricao;

    @Column(name = "realizada_em")
    private LocalDateTime realizadaEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    public Transacao() {
    }

    public Transacao(Integer valor, String tipo, String descricao, Cliente cliente) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = LocalDateTime.now();
        this.cliente = cliente;
    }

    public Integer getValor() {
        return valor;
    }


    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setRealizadaEm(LocalDateTime realizadaEm) {
        this.realizadaEm = realizadaEm;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
