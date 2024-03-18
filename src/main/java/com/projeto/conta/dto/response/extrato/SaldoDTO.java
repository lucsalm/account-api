package com.projeto.conta.dto.response.extrato;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


public record SaldoDTO(Integer total, @JsonProperty("data_extrato") LocalDateTime dataExtrato, Integer limite) {
}

