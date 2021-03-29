package br.com.fiap.daoexample.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@IdClass(AvaliacaoId.class)
public class Avaliacao implements Serializable {

    @Id
    @JoinColumn(name = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    @Id
    @JoinColumn(name = "id_estabelecimento")
    @ManyToOne(optional = false)
    private Estabelecimento estabelecimento;

    private int nota;

    // @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_avaliacao")
    private Calendar dataAvaliacao;


}
