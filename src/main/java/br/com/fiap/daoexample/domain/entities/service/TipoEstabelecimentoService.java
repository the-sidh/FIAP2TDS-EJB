package br.com.fiap.daoexample.domain.entities.service;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TipoEstabelecimentoService extends Remote {

    public String pesquisar(Integer idPesquisa) throws RemoteException;

}


