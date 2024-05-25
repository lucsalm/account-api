package com.projeto.conta.strategy.impl;

import com.projeto.conta.entity.Cliente;
import com.projeto.conta.entity.Transacao;
import com.projeto.conta.exception.UnprocessableEntityException;
import com.projeto.conta.strategy.TransacaoStrategy;
import org.springframework.stereotype.Component;

@Component
public class DebitoStrategy implements TransacaoStrategy {

    public static final String DEBITO = "d";

    public void executaTransacao(Cliente cliente, Transacao transacao) {
        if (Boolean.FALSE.equals(temSaldoLimiteSuficiente(cliente, transacao))) {
            throw new UnprocessableEntityException();
        }
        cliente.debitar(transacao.getValor());
        cliente.addTransacao(transacao);
    }
    private Boolean temSaldoLimiteSuficiente(Cliente cliente, Transacao transacao) {
        return transacao.getValor() <= cliente.getSaldo() + cliente.getLimite();
    }

    public String buscarTipo() {
        return DEBITO;
    }


}