package br.com.fiap.daoexample.domain.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contrato_aluguel")
public class ContratoAluguel {

    @Id
    @Column(name = "id_contrato")
    private int id;

    @Column(name = "valor_contrato", nullable = false)
    private float valor;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento ", nullable = false)
    private Calendar dataVencimento;

    @OneToOne
    @JoinColumn(name = "id_estabelecimento")
    private Estabelecimento estabelecimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

//gets e sets
}

