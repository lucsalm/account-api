package com.projeto.conta.mapper;

import com.projeto.conta.dto.response.extrato.TransacaoDTO;
import com.projeto.conta.dto.request.transacao.TransacaoRequestDTO;
import com.projeto.conta.entity.Cliente;
import com.projeto.conta.entity.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public Transacao transacaoRequestDTOtoTransacao(final TransacaoRequestDTO request, final Cliente cliente) {
        return new Transacao(request.valor(),request.tipo(),request.descricao(),cliente);
    }

    public TransacaoDTO transacaoToTransacaoDTO(final Transacao transacao) {
        return new TransacaoDTO(transacao.getValor(), transacao.getDescricao(),transacao.getTipo(),transacao.getRealizadaEm());
    }

}
