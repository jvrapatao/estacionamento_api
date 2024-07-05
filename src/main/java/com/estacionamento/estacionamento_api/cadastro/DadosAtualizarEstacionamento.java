package com.estacionamento.estacionamento_api.cadastro;


import jakarta.validation.constraints.NotNull;

public record DadosAtualizarEstacionamento(
        @NotNull
        Long id,
        String modelo,
        String marca,
        Periodo período,
        Vagas vagas,
        String data,
        String hora) {

}
