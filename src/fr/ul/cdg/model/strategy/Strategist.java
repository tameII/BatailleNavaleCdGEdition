package fr.ul.cdg.model.strategy;

import fr.ul.cdg.model.Game;
import fr.ul.cdg.util.Vector2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Strategist extends Remote {
    public Vector2 nextShot(Game game) throws RemoteException;
    public Vector2 nextShot(Strategy strategy, Game game) throws RemoteException;

}