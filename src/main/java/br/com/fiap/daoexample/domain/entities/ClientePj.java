package br.com.fiap.daoexample.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cliente_pj")
public class ClientePj extends Cliente {

    @Column(name="inscricao_estadual", length = 20)
    private String inscricaoEstadual;

    @Column(length = 50)
    private String nomeFantasia;

    public ClientePj(String inscricaoEstadual, String nomeFantasia) {
        this.inscricaoEstadual = inscricaoEstadual;
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
