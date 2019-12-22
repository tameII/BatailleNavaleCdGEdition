package server;


import fr.ul.cdg.model.Game;
import fr.ul.cdg.model.strategy.Ai;
import fr.ul.cdg.model.strategy.Strategist;
import fr.ul.cdg.model.strategy.Strategy;
import fr.ul.cdg.util.Vector2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StrategistImpl extends UnicastRemoteObject implements Strategist {
    private Ai ai;
    public StrategistImpl() throws RemoteException {
        ai = new Ai();
    }

    @Override
    public Vector2 nextShot(Game game) throws RemoteException{
        return ai.nextShot(game);
    }

    @Override
    public Vector2 nextShot(Strategy strategy, Game game) throws RemoteException {
        return ai.nextShot(strategy, game);
    }

}
