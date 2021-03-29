package br.com.fiap.daoexample.resources.rmi;

import br.com.fiap.daoexample.domain.entities.service.TipoEstabelecimentoService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TipoEstabelecimentoServidor {

    public void startServer() {
        try {

            TipoEstabelecimentoService service =
                    new TipoEstabelecimentoServiceImpl();

            TipoEstabelecimentoService skeleton =
                    (TipoEstabelecimentoService)
                            UnicastRemoteObject.exportObject(service, 0);

            Registry registry =
                    LocateRegistry.createRegistry(7777);

            registry.bind("TipoEstabelecimentoService", skeleton);

            System.out.println("TipoEstabelecimentoService registrado e pronto para aceitar solicitações.");

        } catch (AlreadyBoundException | RemoteException e) {

            System.out.println(
                    "Não foi possível iniciar o Servidor RMI!");
            e.printStackTrace();
        }

    }
}
