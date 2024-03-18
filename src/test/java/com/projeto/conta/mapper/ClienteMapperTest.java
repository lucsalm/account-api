package com.projeto.conta.mapper;

import com.projeto.conta.dto.response.cliente.ClienteResponseDTO;
import com.projeto.conta.dto.response.extrato.ExtratoResponseDTO;
import com.projeto.conta.dto.response.extrato.SaldoDTO;
import com.projeto.conta.dto.response.extrato.TransacaoDTO;
import com.projeto.conta.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteMapperTest {

    @InjectMocks
    private ClienteMapper clienteMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void clienteToClienteResponseDTOTest() {
        Cliente cliente = getCliente();
        ClienteResponseDTO clienteResponseDTO = clienteMapper.clienteToClienteResponseDTO(cliente);
        assertEquals(cliente.getLimite(), clienteResponseDTO.limite());
        assertEquals(cliente.getSaldo(), clienteResponseDTO.saldo());
    }

    @Test
    public void clienteToSaldoDTOTest() {
        Cliente cliente = getCliente();
        SaldoDTO saldoDTO = clienteMapper.clienteToSaldoDTO(cliente);
        assertEquals(cliente.getLimite(), saldoDTO.limite());
        assertEquals(cliente.getSaldo(), saldoDTO.total());
        assertTrue(Duration.between(saldoDTO.dataExtrato(), LocalDateTime.now()).getSeconds() < 1);
    }

    @Test
    public void clienteToExtratoResponseDTOTest() {
        SaldoDTO saldoDTO = new SaldoDTO(1, LocalDateTime.now(), 2);
        List<TransacaoDTO> transacaoDTOList = List.of(new TransacaoDTO(1, "Teste", "c", LocalDateTime.now()));
        ExtratoResponseDTO extratoResponseDTO = clienteMapper.clienteToExtratoResponseDTO(saldoDTO, transacaoDTOList);
        assertEquals(saldoDTO, extratoResponseDTO.saldo());
        assertEquals(transacaoDTOList, extratoResponseDTO.transacoes());
    }

    private Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setLimite(1);
        cliente.setSaldo(2);
        return cliente;
    }
}