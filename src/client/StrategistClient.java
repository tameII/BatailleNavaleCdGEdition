package client;


import fr.ul.cdg.model.Game;
import fr.ul.cdg.model.strategy.Ai;
import fr.ul.cdg.model.strategy.Strategist;
import fr.ul.cdg.model.strategy.Strategy;
import fr.ul.cdg.util.Vector2;

import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StrategistClient implements Serializable {
    Ai ai;

    public StrategistClient(Ai ai){
        this.ai = ai;
    }
    /**
     * Call the server to get the nextShot
     * If the server is unable to give out the nextShot, the shot is calculated by the client with the given strategy
     */
    public Vector2 nextShotRMI(Strategy strategy, Game game) throws NamingException, RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry();

        System.out.println("RMI registry binding:");
        String[] e = registry.list();

        for (int i = 0; i < e.length; i++) {
            System.out.println(e[i]);
        }

        String remoteObjectName = "central_strategist";
        Strategist centralStrategist = (Strategist) registry.lookup(remoteObjectName);

        if(centralStrategist != null){
            return centralStrategist.nextShot(strategy, game);
        }
        //Backup fail
        return ai.nextShot(strategy, game);
    }

}
