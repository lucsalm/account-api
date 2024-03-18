package com.projeto.conta.dto.response.extrato;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


public record TransacaoDTO (Integer valor, String descricao,String tipo, @JsonProperty("realizada_em") LocalDateTime realizadaEm) {
}
