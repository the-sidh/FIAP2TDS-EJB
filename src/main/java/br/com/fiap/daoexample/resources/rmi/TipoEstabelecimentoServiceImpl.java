package br.com.fiap.daoexample.resources.rmi;

import br.com.fiap.daoexample.cfg.HibernateUtil;
import br.com.fiap.daoexample.dao.TipoEstabelecimentoDAO;
import br.com.fiap.daoexample.domain.entities.TipoEstabelecimento;
import br.com.fiap.daoexample.domain.entities.service.TipoEstabelecimentoService;

import javax.persistence.EntityManager;
import java.rmi.RemoteException;

public class TipoEstabelecimentoServiceImpl implements TipoEstabelecimentoService {

    protected TipoEstabelecimentoServiceImpl()
            throws RemoteException {
        super();
    }

    public String pesquisar(Integer idPesquisa)
            throws RemoteException {

        EntityManager em = HibernateUtil.getEntityManager();


        TipoEstabelecimentoDAO dao =
                new TipoEstabelecimentoDAO(em);

        TipoEstabelecimento tipoEstabelecimento =
                dao.buscar(idPesquisa);

        if (tipoEstabelecimento != null) {
            return tipoEstabelecimento.getNome();
        } else {
            return null;
        }
    }
}
