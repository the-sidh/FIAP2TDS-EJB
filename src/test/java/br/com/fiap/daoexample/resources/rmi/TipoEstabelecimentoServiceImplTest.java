package br.com.fiap.daoexample.resources.rmi;

import br.com.fiap.daoexample.cfg.HibernateUtil;
import br.com.fiap.daoexample.dao.TipoEstabelecimentoDAO;
import br.com.fiap.daoexample.domain.entities.TipoEstabelecimento;
import br.com.fiap.daoexample.domain.entities.service.TipoEstabelecimentoService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TipoEstabelecimentoServiceImplTest {
    private static EntityManager em = HibernateUtil.getEntityManager();

    @BeforeEach
    public void setup() {
        TipoEstabelecimentoServidor server = new TipoEstabelecimentoServidor();
        TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
        cadastraNovoTipoEstabelecimento(dao, "Bar e Restaurante");
        cadastraNovoTipoEstabelecimento(dao, "Escolas e universidades");
        cadastraNovoTipoEstabelecimento(dao, "Cinemas");
        cadastraNovoTipoEstabelecimento(dao, "Hamburgueria");
        server.startServer();
    }

    @AfterEach
    public void tearDown() {
        TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
        dao.purge();
        if (em != null) em.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test
    @DisplayName("dado que o servidor RMI está rodando, quando recupera, via client, um estabelecimento, deve recuperar o estabelecimento correto")
    public void teste() {
        try {
            Registry registry =
                    LocateRegistry.getRegistry("127.0.0.1", 7777);


            TipoEstabelecimentoService stub =
                    (TipoEstabelecimentoService) registry
                            .lookup("TipoEstabelecimentoService");


            String resultado = stub.pesquisar(1);
            Assertions.assertEquals("Bar e Restaurante", resultado);

            if (resultado != null) {
                System.out.println(
                        "Registro encontrado: " + resultado);
            } else {
                System.out.println(
                        "Tipo de estabelecimento não encontrado");
            }

        } catch (RemoteException | NotBoundException e) {
            System.out.println(
                    "Erro ao tentar chamada para o servidor");
            e.printStackTrace();
        }
    }

    @NotNull
    private TipoEstabelecimento cadastraNovoTipoEstabelecimento(TipoEstabelecimentoDAO dao, String descricao) {
        TipoEstabelecimento tipo = new TipoEstabelecimento();
        try {
            tipo.setNome(descricao);
            dao.cadastrar(tipo);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipo;
    }
}
