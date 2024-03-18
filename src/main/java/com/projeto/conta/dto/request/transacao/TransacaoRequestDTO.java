package com.projeto.conta.dto.request.transacao;

import jakarta.validation.constraints.*;



public record TransacaoRequestDTO(
        @NotNull @Positive Integer valor,
        @NotNull @Pattern(regexp = "^[cd]$") String tipo,
        @NotNull @NotBlank @Size(min = 1, max = 10) String descricao) {
}
