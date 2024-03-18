package com.projeto.conta.mapper;

import com.projeto.conta.dto.request.transacao.TransacaoRequestDTO;
import com.projeto.conta.dto.response.extrato.TransacaoDTO;
import com.projeto.conta.entity.Cliente;
import com.projeto.conta.entity.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransacaoMapperTest {
    @InjectMocks
    private TransacaoMapper transacaoMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void transacaoToTransacaoDTOTest() {
        Transacao transacao = getTransacao();
        TransacaoDTO transacaoDTO = transacaoMapper.transacaoToTransacaoDTO(transacao);
        assertEquals(transacao.getDescricao(), transacaoDTO.descricao());
        assertEquals(transacao.getValor(), transacaoDTO.valor());
        assertEquals(transacao.getTipo(), transacaoDTO.tipo());
        assertEquals(transacao.getRealizadaEm(), transacaoDTO.realizadaEm());
    }

    @Test
    public void transacaoRequestDTOtoTransacaoTest() {
        TransacaoRequestDTO transacaoRequestDTO = new TransacaoRequestDTO(1, "c", "Teste");
        Cliente cliente = new Cliente();
        Transacao transacao = transacaoMapper.transacaoRequestDTOtoTransacao(transacaoRequestDTO, cliente);
        assertEquals(transacaoRequestDTO.descricao(), transacao.getDescricao());
        assertEquals(transacaoRequestDTO.valor(), transacao.getValor());
        assertEquals(transacaoRequestDTO.tipo(), transacao.getTipo());
        assertEquals(cliente, transacao.getCliente());
    }

    private Transacao getTransacao() {
        Transacao transacao = new Transacao();
        transacao.setDescricao("Teste");
        transacao.setTipo("c");
        transacao.setValor(2);
        transacao.setRealizadaEm(LocalDateTime.now());
        return transacao;
    }
}