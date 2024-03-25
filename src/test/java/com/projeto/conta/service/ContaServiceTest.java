package com.projeto.conta.service;

import com.projeto.conta.dto.request.transacao.TransacaoRequestDTO;
import com.projeto.conta.dto.response.cliente.ClienteResponseDTO;
import com.projeto.conta.dto.response.extrato.ExtratoResponseDTO;
import com.projeto.conta.dto.response.extrato.SaldoDTO;
import com.projeto.conta.dto.response.extrato.TransacaoDTO;
import com.projeto.conta.entity.Cliente;
import com.projeto.conta.entity.Transacao;
import com.projeto.conta.exception.NotFoundException;
import com.projeto.conta.mapper.ClienteMapper;
import com.projeto.conta.mapper.TransacaoMapper;
import com.projeto.conta.repository.ClienteRepository;
import com.projeto.conta.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private TransacaoRepository transacaoRepository;
    @Mock
    private ClienteMapper clienteMapper;
    @Mock
    private TransacaoMapper transacaoMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void clienteNaoEncontrado() {
        TransacaoRequestDTO transacaoRequestDTO = Mockito.mock(TransacaoRequestDTO.class);
        when(clienteRepository.findByIdToWrite(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> contaService.realizarTransacao(1, transacaoRequestDTO));
    }

    @Test
    public void clienteNaoEncontradoConsulta() {
        when(clienteRepository.findByIdToRead(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> contaService.consultaExtrato(1));
    }

    @Test
    public void executaTransacaoSucesso() {
        TransacaoRequestDTO transacaoRequestDTO = Mockito.mock(TransacaoRequestDTO.class);
        Cliente cliente = Mockito.mock(Cliente.class);
        Transacao transacao = Mockito.mock(Transacao.class);
        ClienteResponseDTO clienteResponseDTO = Mockito.mock(ClienteResponseDTO.class);

        when(clienteRepository.findByIdToWrite(any())).thenReturn(Optional.of(cliente));
        when(transacaoMapper.transacaoRequestDTOtoTransacao(any(), any())).thenReturn(transacao);
        when(clienteMapper.clienteToClienteResponseDTO(any())).thenReturn(clienteResponseDTO);

        ClienteResponseDTO clienteResponse = contaService.realizarTransacao(1, transacaoRequestDTO);

        assertEquals(clienteResponse, clienteResponse);
    }

    @Test
    public void consultaSucesso() {
        TransacaoDTO transacaoDTO = Mockito.mock(TransacaoDTO.class);
        Cliente cliente = Mockito.mock(Cliente.class);
        List<Transacao> transacoes = Mockito.mock(ArrayList.class);
        SaldoDTO saldoDTO = Mockito.mock(SaldoDTO.class);
        ExtratoResponseDTO extratoResponseDTO = Mockito.mock(ExtratoResponseDTO.class);

        when(clienteRepository.findByIdToRead(any())).thenReturn(Optional.of(cliente));
        when(transacaoRepository.findByClienteOrderByRealizadaEmDesc(any(), any())).thenReturn(transacoes);
        when(transacaoMapper.transacaoToTransacaoDTO(any())).thenReturn(transacaoDTO);
        when(clienteMapper.clienteToSaldoDTO(any())).thenReturn(saldoDTO);
        when(clienteMapper.clienteToExtratoResponseDTO(any(),any())).thenReturn(extratoResponseDTO);

        ExtratoResponseDTO extrato = contaService.consultaExtrato(1);

        assertEquals(extratoResponseDTO, extrato);
    }


}