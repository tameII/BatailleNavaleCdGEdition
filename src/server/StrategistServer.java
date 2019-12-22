package server;


import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StrategistServer
{
    public static void main(String[] args) throws IOException, NamingException, AlreadyBoundException {
        ClassServer server = new ClassFileServer(8080, ".");

        System.out.println("Constructing server implementation...");
        StrategistImpl centralStrategist = new StrategistImpl();

        System.out.println("Binding server implementation to registry...");
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("central_strategist", centralStrategist);

        System.out.println("Waiting for invocations from clients...");
    }
}
