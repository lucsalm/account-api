package com.projeto.conta.controller;

import com.projeto.conta.dto.request.transacao.TransacaoRequestDTO;
import com.projeto.conta.dto.response.cliente.ClienteResponseDTO;
import com.projeto.conta.dto.response.error.ErrorResponseDTO;
import com.projeto.conta.dto.response.extrato.ExtratoResponseDTO;
import com.projeto.conta.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
@Tag(name = "ContaController", description = "Operações relacionadas a conta de um cliente")
public class ContaController {

    private final ContaService service;


    @PostMapping("/{idCliente}/transacoes")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Realiza uma transação para um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação realizada com sucesso",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClienteResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "422", description = "Requisição não é valida",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no sistema",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDTO.class))})})
    public ClienteResponseDTO realizaTransacao(@PathVariable(name = "idCliente") @Positive final Integer clienteId,
                                               @RequestBody @Valid final TransacaoRequestDTO transacao) {
        return service.realizarTransacao(clienteId, transacao);
    }

    @GetMapping("/{idCliente}/extrato")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Realiza uma consulta de extrato de um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realiza uma consulta de extrato de um cliente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExtratoResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "422", description = "Requisição não é valida",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no sistema",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDTO.class))})})
    public ExtratoResponseDTO consultaExtrato(@PathVariable(name = "idCliente") @Positive final Integer clienteId) {
        return service.consultaExtrato(clienteId);
    }

}
