package br.com.fiap.daoexample.domain.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "tbl_estabelecimento")
public class Estabelecimento {

    @Id
    @SequenceGenerator(name="estabelecimento",sequenceName="sq_tbl_estabelecimento",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="estabelecimento")
    @Column(name = "id_estabelecimento")
    private Integer id;

    @Column(name = "nome_estabelecimento", length = 50)
    private String nome;

    @JoinColumn(name = "id_tipo_estabelecimento")
    @ManyToOne
    private TipoEstabelecimento tipo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_criacao")
    private Calendar dataCriacao;

    @OneToOne(mappedBy="estabelecimento")
    private ContratoAluguel contrato;

    @ManyToMany(mappedBy="estabelecimentos")
    private List<Cliente> clientes;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEstabelecimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEstabelecimento tipo) {
        this.tipo = tipo;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ContratoAluguel getContrato() {
        return contrato;
    }

    public void setContrato(ContratoAluguel contrato) {
        this.contrato = contrato;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
