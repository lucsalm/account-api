package com.projeto.conta.service;

import com.projeto.conta.dto.request.transacao.TransacaoRequestDTO;
import com.projeto.conta.dto.response.cliente.ClienteResponseDTO;
import com.projeto.conta.dto.response.extrato.ExtratoResponseDTO;
import com.projeto.conta.dto.response.extrato.SaldoDTO;
import com.projeto.conta.dto.response.extrato.TransacaoDTO;
import com.projeto.conta.entity.Cliente;
import com.projeto.conta.entity.Transacao;
import com.projeto.conta.exception.HttpStatusException;
import com.projeto.conta.exception.NotFoundException;
import com.projeto.conta.mapper.ClienteMapper;
import com.projeto.conta.mapper.TransacaoMapper;
import com.projeto.conta.repository.ClienteRepository;
import com.projeto.conta.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ContaService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private TransacaoMapper transacaoMapper;

    @Transactional
    @Retryable(noRetryFor = {HttpStatusException.class}, backoff = @Backoff(delay = 100, maxDelay = 1000))
    public ClienteResponseDTO realizarTransacao(final Integer clienteId, final TransacaoRequestDTO transacaoRequest) {
        final Cliente cliente = clienteRepository.findByIdToWrite(clienteId)
                .orElseThrow(NotFoundException::new);

        final Transacao transacao = transacaoMapper.transacaoRequestDTOtoTransacao(transacaoRequest, cliente);
        cliente.executaTransacao(transacao);

        return clienteMapper.clienteToClienteResponseDTO(cliente);
    }

    @Transactional
    @Retryable(noRetryFor = {HttpStatusException.class}, backoff = @Backoff(delay = 100, maxDelay = 1000))
    public ExtratoResponseDTO consultaExtrato(final Integer clienteId) {
        final Cliente cliente = clienteRepository.findByIdToRead(clienteId)
                .orElseThrow(NotFoundException::new);

        final List<TransacaoDTO> transacoes = transacaoRepository
                .findByClienteOrderByRealizadaEmDesc(cliente, Limit.of(10)).stream()
                .map(transacaoMapper::transacaoToTransacaoDTO).toList();

        final SaldoDTO saldo = clienteMapper.clienteToSaldoDTO(cliente);

        return clienteMapper.clienteToExtratoResponseDTO(saldo, transacoes);
    }


}
