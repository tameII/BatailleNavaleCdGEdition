package fr.ul.cdg.server;


import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StrategistServer
{
    public static void main(String[] args) throws IOException, NamingException, AlreadyBoundException {
        ClassServer server = new ClassFileServer(4230, ".");

        System.out.println("Constructing server implementation...");
        StrategistImpl centralStrategist = new StrategistImpl();

        System.out.println("Binding server implementation to registry...");
        LocateRegistry.createRegistry(1099);
        Registry registry = LocateRegistry.getRegistry(1099);
        registry.bind("central_strategist", centralStrategist);

        System.out.println("Waiting for invocations from clients...");
    }
}
