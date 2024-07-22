package com.estacionamento.estacionamento_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.estacionamento.estacionamento_api.cadastro.DadosAtualizarEstacionamento;
import com.estacionamento.estacionamento_api.cadastro.DadosDetalheEstacionamento;
import com.estacionamento.estacionamento_api.cadastro.DadosEstacionamento;
import com.estacionamento.estacionamento_api.cadastro.DadosListagemEstacionamento;
import com.estacionamento.estacionamento_api.cadastro.Estacionamento;
import com.estacionamento.estacionamento_api.cadastro.EstacionamentoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {
    
    @Autowired
    private EstacionamentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalheEstacionamento> cadastrar(@RequestBody @Valid DadosEstacionamento dados, UriComponentsBuilder uriBuilder) {
        var estacionamento = new Estacionamento(dados);
        repository.save(estacionamento);

        var uri =  uriBuilder.path("/estacionamento/{id}").buildAndExpand(estacionamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalheEstacionamento(estacionamento));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemEstacionamento>> Listar() {
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemEstacionamento::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalheEstacionamento> atualizar(@RequestBody @Valid DadosAtualizarEstacionamento dados) {
        var estacionamento = repository.getReferenceById(dados.id());
        estacionamento.atulizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalheEstacionamento(estacionamento));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar (@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar (@PathVariable Long id) {
        var estacionamento = repository.getReferenceById(id);
        estacionamento.inativar();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar (@PathVariable Long id) {
        var estacionamento = repository.getReferenceById(id);
        estacionamento.reativar();
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheEstacionamento> cadastroPorId (@PathVariable Long id) {
        var estacionamento = repository.getReferenceById(id);        
        return ResponseEntity.ok(new DadosDetalheEstacionamento(estacionamento));
    }
}
