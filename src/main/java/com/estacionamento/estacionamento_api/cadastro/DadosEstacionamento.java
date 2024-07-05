package com.estacionamento.estacionamento_api.cadastro;


import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record DadosEstacionamento(

    @NotBlank
    String modelo,
    
    @NotBlank
    String marca,

    @Enumerated
    Periodo período,

    @Enumerated
    Vagas vagas,

    @NotBlank
    String data,
    
    @NotBlank
    String hora) {
    
}
