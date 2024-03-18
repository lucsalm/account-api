package com.projeto.conta.service;

import com.projeto.conta.dto.request.transacao.TransacaoRequestDTO;
import com.projeto.conta.exception.NotFoundException;
import com.projeto.conta.mapper.ClienteMapper;
import com.projeto.conta.mapper.TransacaoMapper;
import com.projeto.conta.repository.ClienteRepository;
import com.projeto.conta.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        when(clienteRepository.findByIdToRead(any())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> contaService.realizarTransacao(1, getTransacaoRequestDTO()));
    }

    @Test
    public void consultaExtrato() {


    }

    private TransacaoRequestDTO getTransacaoRequestDTO() {
        return new TransacaoRequestDTO(1, "c", "Teste");
    }

}