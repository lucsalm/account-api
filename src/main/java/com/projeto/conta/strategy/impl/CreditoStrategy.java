package com.projeto.conta.strategy.impl;

import com.projeto.conta.entity.Cliente;
import com.projeto.conta.entity.Transacao;
import com.projeto.conta.strategy.TransacaoStrategy;
import org.springframework.stereotype.Component;

@Component
public class CreditoStrategy implements TransacaoStrategy {

    public static final String CREDITO = "c";

    public void executaTransacao(Cliente cliente, Transacao transacao) {
        cliente.creditar(transacao.getValor());
        cliente.addTransacao(transacao);
    }

    public String buscarTipo(){
        return CREDITO;
    }
}
