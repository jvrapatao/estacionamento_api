package com.estacionamento.estacionamento_api.cadastro;


public record DadosListagemEstacionamento(
    Long id,
    String modelo,
    String marca,
    Periodo período,
    Vagas vagas,
    String data,
    String hora) {

    public DadosListagemEstacionamento(Estacionamento estacionamento) {
        this(estacionamento.getId(),
            estacionamento.getModelo(),
            estacionamento.getMarca(),
            estacionamento.getPeríodo(),
            estacionamento.getVagas(),
            estacionamento.getData(),
            estacionamento.getHora()
        );
    }
}
