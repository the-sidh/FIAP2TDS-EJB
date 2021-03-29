package br.com.fiap.daoexample.dao;


import javax.persistence.EntityManager;

import br.com.fiap.daoexample.domain.entities.TipoEstabelecimento;

import java.util.List;

public class TipoEstabelecimentoDAO extends GenericDAO<TipoEstabelecimento, Integer> {

    public TipoEstabelecimentoDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<TipoEstabelecimento> listar() {
        return this.em.createQuery(
                "from TipoEstabelecimento"
        ).getResultList();
    }

    public List<TipoEstabelecimento> listarOrdenadoNome() {
        return this.em.createQuery(
                "from TipoEstabelecimento order by nome"
        ).getResultList();
    }

    public List<TipoEstabelecimento> listarTresUltimos() {
        //Página 15
        return null;
    }

    public List<TipoEstabelecimento>
    listarPaginado(int itensPorPagina, int pagina) {
        //página 16
        return null;
    }
}
