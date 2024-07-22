package com.estacionamento.estacionamento_api.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroDeSeguranca extends OncePerRequestFilter {

    @Autowired
    private ServicoToken servicoToken; 
    
    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

            var tokenJWT = recuperarToken(request);
            var subject = servicoToken.getSubject(tokenJWT);

            filterChain.doFilter(request, response);

    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizacaoheader = request.getHeader("Autorização");
        if (autorizacaoheader == null) {
            throw  new RuntimeException(" token não enviado");
        }
         return autorizacaoheader;
    }
    
}
