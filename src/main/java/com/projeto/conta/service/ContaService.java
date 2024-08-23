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
import com.projeto.conta.strategy.factory.TransferenciaFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContaService {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;
    private final ClienteMapper clienteMapper;
    private final TransacaoMapper transacaoMapper;
    private final TransferenciaFactory transferenciaFactory;

    @Transactional
    @Retryable(noRetryFor = {HttpStatusException.class}, backoff = @Backoff(delay = 100, maxDelay = 1000))
    public ClienteResponseDTO realizarTransacao(final Integer clienteId, final TransacaoRequestDTO transacaoRequest) {
        final Cliente cliente = clienteRepository.findByIdToWrite(clienteId)
                .orElseThrow(NotFoundException::new);

        final Transacao transacao = transacaoMapper.transacaoRequestDTOtoTransacao(transacaoRequest, cliente);
        transferenciaFactory.buscarStrategy(transacao.getTipo())
                .executaTransacao(cliente, transacao);

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
