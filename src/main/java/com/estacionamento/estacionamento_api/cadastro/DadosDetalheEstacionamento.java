package com.estacionamento.estacionamento_api.cadastro;

public record DadosDetalheEstacionamento(
    Long id,
    String modelo,
    String marca,
    Periodo período,
    Vagas vagas,
    String data,
    Boolean ativo,
    String hora) {

        public DadosDetalheEstacionamento (Estacionamento estacionamento) {
            this(estacionamento.getId(),
                estacionamento.getModelo(),
                estacionamento.getMarca(),
                estacionamento.getPeríodo(),
                estacionamento.getVagas(),
                estacionamento.getData(),
                estacionamento.getAtivo(),
                estacionamento.getHora());
        }

}
