package br.com.fiap.daoexample.testes;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.fiap.daoexample.dao.TipoEstabelecimentoDAO;
import br.com.fiap.daoexample.domain.entities.TipoEstabelecimento;

public class DAOTeste {

    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = Persistence.createEntityManagerFactory("smartcities-orm").createEntityManager();
            TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);

            salvaTipoEstabelecimento(dao);
            TipoEstabelecimento tipoEstabelecimento = buscaTipoEstabelecimento(dao);

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
            System.exit(0);
        }

    }

    private static TipoEstabelecimento buscaTipoEstabelecimento(TipoEstabelecimentoDAO dao) {
        TipoEstabelecimento entidade  = dao.buscar(1);

        if (entidade == null) {
            System.out.println("Não existe tipo de estabelecimento para a chave 1");
        }
        else {
            System.out.println(" > " + entidade.getId() + " - " + entidade.getNome());
        }
        return entidade;
    }

    private static void salvaTipoEstabelecimento(TipoEstabelecimentoDAO dao) throws Exception {
        TipoEstabelecimento tipo = new TipoEstabelecimento();
        tipo.setNome("Bar e Restaurante");

        dao.cadastrar(tipo);
        dao.commit();

    }
}
