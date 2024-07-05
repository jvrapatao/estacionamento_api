package com.estacionamento.estacionamento_api.cadastro;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="Estacionamento")
@Entity(name="estacionamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Estacionamento {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String modelo;
    private String marca;
    
    @Enumerated(EnumType.STRING)
    private Periodo período;
    
    @Enumerated(EnumType.STRING)
    private Vagas vagas;
    
    private String data;
    private Boolean ativo;
    private String hora;
    
    public Estacionamento(DadosEstacionamento dados) {
        this.ativo = true;
        this.modelo = dados.modelo();
        this.marca = dados.marca();
        this.período = dados.período();
        this.vagas = dados.vagas();
        this.data = dados.data();
        this.hora = dados.hora();
    }
    
    public void atulizarInformacoes(@Valid DadosAtualizarEstacionamento dados) {
        if (dados.modelo() != null) {
            this.modelo = dados.modelo();
        }
        if (dados.marca() != null) {
            this.marca = dados.marca();
        }
        if (dados.período() != null) {
            this.período = dados.período();
        }
        if (dados.vagas() != null) {
            this.vagas = dados.vagas(); 
        }
        if (dados.data() != null) {
            this.data = dados.data();
        }
        if (dados.hora() != null) {
            this.hora = dados.hora();
        }

    }

    public void inativar() {
        this.ativo = false;
    }

    public void reativar () {
        this.ativo = true;
    }
}
