package br.com.fiap.daoexample.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cliente")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="tipo_pessoa" )
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    @SequenceGenerator(name = "cliente", sequenceName = "sq_cliente", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente")
    private int id;

    @Column(length = 50)
    private String nome;


    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_estabelecimento"), name = "cliente_estabelecimento")
    private List<Estabelecimento> estabelecimentos;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Estabelecimento> getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }
}
