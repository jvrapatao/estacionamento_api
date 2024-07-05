package com.estacionamento.estacionamento_api.cadastro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

    List<Estacionamento> findAllByAtivoTrue();

}
