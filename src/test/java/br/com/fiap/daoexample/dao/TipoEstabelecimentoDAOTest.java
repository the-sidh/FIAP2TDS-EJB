package br.com.fiap.daoexample.dao;

import br.com.fiap.daoexample.cfg.HibernateUtil;
import br.com.fiap.daoexample.domain.entities.TipoEstabelecimento;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import java.util.List;

public class TipoEstabelecimentoDAOTest {

    private static EntityManager em;

    @BeforeAll
    public static void setup() {
        em = HibernateUtil.getEntityManager();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tearDown() {
        if (em != null) em.close();
        System.out.println("SessionFactory destroyed");
    }

    @AfterEach
    public void delete() {
        TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
        dao.purge();
    }

    @Test()
    @DisplayName("dado um tipo de estabelecimento, quando tenta gravar , deve recuperar o tipo de estabelecimento no banco")
    public void testeCadastrarBuscar() {
        TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
        TipoEstabelecimento tipo = cadastraNovoTipoEstabelecimento(dao, "Bar e Restaurante");
        try {
            dao.commit();
            TipoEstabelecimento inserted = dao.buscar(1);
            Assertions.assertEquals(tipo.getNome(), inserted.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    @DisplayName("dado 3 tipos de estabelecimento cadastrados, quando tenta recuperar todos os tipos de estabelecimento , deve recuperar uma lista com 3 itens")
    public void testeListar() {
        TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
        cadastraNovoTipoEstabelecimento(dao, "Bar e Restaurante");
        cadastraNovoTipoEstabelecimento(dao, "Escolas e universidades");
        cadastraNovoTipoEstabelecimento(dao, "Cinemas");
        try {
            List<TipoEstabelecimento> tipoEstabelecimentos = dao.listar();
            Assertions.assertEquals(3, tipoEstabelecimentos.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    @DisplayName("dado 3 tipos de estabelecimento cadastrados, quando tenta recuperar em ordem todos os tipos de estabelecimento , deve recuperar uma lista com 3 itens e com os itens ordenados")
    public void testeListarOrdenado() {
        TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
        cadastraNovoTipoEstabelecimento(dao, "Bar e Restaurante");
        cadastraNovoTipoEstabelecimento(dao, "Escolas e universidades");
        cadastraNovoTipoEstabelecimento(dao, "Cinemas");
        try {
            List<TipoEstabelecimento> tipoEstabelecimentos = dao.listarOrdenadoNome();
            Assertions.assertEquals(3, tipoEstabelecimentos.size());
            Assertions.assertEquals("Bar e Restaurante",tipoEstabelecimentos.get(0).getNome());
            Assertions.assertEquals("Cinemas",tipoEstabelecimentos.get(1).getNome());
            Assertions.assertEquals("Escolas e universidades",tipoEstabelecimentos.get(2).getNome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    @DisplayName("dados 4 estabelecimentos, quando lista os 3 últimos deve retornar uma lista com 3 items")
    public void testeListarTresUltimos() {
        TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
        cadastraNovoTipoEstabelecimento(dao, "Bar e Restaurante");
        cadastraNovoTipoEstabelecimento(dao, "Escolas e universidades");
        cadastraNovoTipoEstabelecimento(dao, "Cinemas");
        cadastraNovoTipoEstabelecimento(dao, "Hamburgueria");
        try {
            List<TipoEstabelecimento> tipoEstabelecimentos = dao.listarTresUltimos();
            Assertions.assertEquals(3, tipoEstabelecimentos.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test()
    @DisplayName("dados 4 estabelecimentos, quando lista paginados em tamanho como 2, deve retornar uma lista com 2 items")
    public void testeListarPaginados() {
        TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
        cadastraNovoTipoEstabelecimento(dao, "Bar e Restaurante");
        cadastraNovoTipoEstabelecimento(dao, "Escolas e universidades");
        cadastraNovoTipoEstabelecimento(dao, "Cinemas");
        cadastraNovoTipoEstabelecimento(dao, "Hamburgueria");
        try {
            List<TipoEstabelecimento> tipoEstabelecimentos = dao.listarPaginado(2,1);
            Assertions.assertEquals(2, tipoEstabelecimentos.size());
        } catch (Exception e) {
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
