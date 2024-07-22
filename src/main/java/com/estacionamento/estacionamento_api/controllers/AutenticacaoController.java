package com.estacionamento.estacionamento_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamento.estacionamento_api.infra.DadosTokenJWT;
import com.estacionamento.estacionamento_api.infra.ServicoToken;
import com.estacionamento.estacionamento_api.usuarios.DadosAutenticacao;
import com.estacionamento.estacionamento_api.usuarios.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private ServicoToken servicoToken;

    @PostMapping
    public ResponseEntity<?> login (@RequestBody @Valid DadosAutenticacao dados) {
        
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autenticacao = manager.authenticate(token);

        var tokenJWT = servicoToken.criarToken((Usuario) autenticacao.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
