package com.projeto.conta.strategy;

import com.projeto.conta.entity.Cliente;
import com.projeto.conta.entity.Transacao;

public interface TransacaoStrategy {

    void executaTransacao(Cliente cliente, Transacao transacao);

    String buscarTipo();
}
