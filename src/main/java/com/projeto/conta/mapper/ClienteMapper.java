package com.projeto.conta.mapper;

import com.projeto.conta.dto.response.cliente.ClienteResponseDTO;
import com.projeto.conta.dto.response.extrato.ExtratoResponseDTO;
import com.projeto.conta.dto.response.extrato.SaldoDTO;
import com.projeto.conta.dto.response.extrato.TransacaoDTO;
import com.projeto.conta.entity.Cliente;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ClienteMapper {

    public ClienteResponseDTO clienteToClienteResponseDTO(final Cliente cliente) {
        return new ClienteResponseDTO(cliente.getLimite(),cliente.getSaldo());
    }

    public SaldoDTO clienteToSaldoDTO(final Cliente cliente) {
        return new SaldoDTO(cliente.getSaldo(), LocalDateTime.now(),cliente.getLimite());
    }

    public ExtratoResponseDTO clienteToExtratoResponseDTO(final SaldoDTO saldo, final List<TransacaoDTO> transacoes) {
        return new ExtratoResponseDTO(saldo,transacoes);
    }




}
