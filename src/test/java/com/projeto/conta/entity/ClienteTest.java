package com.projeto.conta.entity;

import com.projeto.conta.exception.UnprocessableEntityException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteTest {

    @Test
    public void executaTransacaoDebitoComSaldoELimiteTest() {
        Cliente cliente = new Cliente();
        cliente.setSaldo(1);
        cliente.setLimite(1);
        cliente.setTransacoes(new ArrayList<>());
        Transacao transacao = new Transacao();
        transacao.setValor(1);
        transacao.setTipo("d");
        cliente.executaTransacao(transacao);
        assertEquals(0, cliente.getSaldo());
    }

    @Test
    public void executaTransacaoDebitoComSaldoESemLimiteTest() {
        Cliente cliente = new Cliente();
        cliente.setSaldo(1);
        cliente.setLimite(0);
        cliente.setTransacoes(new ArrayList<>());
        Transacao transacao = new Transacao();
        transacao.setValor(1);
        transacao.setTipo("d");
        cliente.executaTransacao(transacao);
        assertEquals(0, cliente.getSaldo());
    }

    @Test
    public void executaTransacaoDebitoSemSaldoEComLimiteTest() {
        Cliente cliente = new Cliente();
        cliente.setSaldo(0);
        cliente.setLimite(1);
        cliente.setTransacoes(new ArrayList<>());
        Transacao transacao = new Transacao();
        transacao.setValor(1);
        transacao.setTipo("d");
        cliente.executaTransacao(transacao);
        assertEquals(-1, cliente.getSaldo());
    }

    @Test
    public void executaTransacaoDebitoSemSaldoESemLimiteTest() {
        Cliente cliente = new Cliente();
        cliente.setSaldo(0);
        cliente.setLimite(0);
        cliente.setTransacoes(new ArrayList<>());
        Transacao transacao = new Transacao();
        transacao.setValor(1);
        transacao.setTipo("d");
        assertThrows(UnprocessableEntityException.class, () -> cliente.executaTransacao(transacao));
    }

    @Test
    public void executaTransacaoCreditoTest() {
        Cliente cliente = new Cliente();
        cliente.setSaldo(0);
        cliente.setLimite(0);
        cliente.setTransacoes(new ArrayList<>());
        Transacao transacao = new Transacao();
        transacao.setValor(1);
        transacao.setTipo("c");
        cliente.executaTransacao(transacao);
        assertEquals(1, cliente.getSaldo());
    }

}