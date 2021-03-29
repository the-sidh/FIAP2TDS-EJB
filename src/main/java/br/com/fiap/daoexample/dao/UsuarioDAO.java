package br.com.fiap.daoexample.dao;

import javax.persistence.EntityManager;

import br.com.fiap.daoexample.domain.entities.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario,Integer> {

	public UsuarioDAO(EntityManager em) {
		super(em);
	}

}
