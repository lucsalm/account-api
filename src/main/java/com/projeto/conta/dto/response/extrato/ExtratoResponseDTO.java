package com.projeto.conta.dto.response.extrato;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record ExtratoResponseDTO(SaldoDTO saldo, @JsonProperty("ultimas_transacoes") List<TransacaoDTO> transacoes) {

}
