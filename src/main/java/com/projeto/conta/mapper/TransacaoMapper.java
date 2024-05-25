package com.projeto.conta.mapper;

import com.projeto.conta.dto.request.transacao.TransacaoRequestDTO;
import com.projeto.conta.dto.response.extrato.TransacaoDTO;
import com.projeto.conta.entity.Cliente;
import com.projeto.conta.entity.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public Transacao transacaoRequestDTOtoTransacao(final TransacaoRequestDTO request, final Cliente cliente) {
        return Transacao.builder()
                .valor(request.valor())
                .tipo(request.tipo())
                .descricao(request.descricao())
                .cliente(cliente).build();
    }

    public TransacaoDTO transacaoToTransacaoDTO(final Transacao transacao) {
        return new TransacaoDTO(transacao.getValor(), transacao.getDescricao(),transacao.getTipo(),transacao.getRealizadaEm());
    }

}
