package br.com.fiap.daoexample.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class GenericDAO<T, K> {

    protected EntityManager em;

    private Class<T> clazz;

    @SuppressWarnings("all")
    public GenericDAO(EntityManager em) {
        this.em = em;
        clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void cadastrar(T entidade) {
        em.persist(entidade);
    }

    public void atualizar(T entidade) {
        em.merge(entidade);
    }

    public void excluir(K codigo) throws Exception {
        T entidade = buscar(codigo);
        if (entidade == null) {
            throw new Exception("Codigo invalido");
        }
        em.remove(entidade);
    }

    public void purge() {
        em.getTransaction().begin();
        Query deleteAllQuery = em.createQuery("DELETE FROM " + clazz.getName());
        deleteAllQuery.executeUpdate();
        em.getTransaction().commit();
    }

    public List<T> listar() {
        return em.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    public T buscar(K codigo) {
        return em.find(clazz, codigo);
    }

    public void commit() throws Exception {
        try {
            em.getTransaction().begin();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Erro no commit");
        }
    }
}

