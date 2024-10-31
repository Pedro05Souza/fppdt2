import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            BancoService bancoService = new Administracao();
            System.setProperty("java.rmi.server.hostname", "172.16.26.134");
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BancoService", bancoService);
            System.out.println("Servidor aberto");
        } catch (RemoteException e) {
            System.out.println("Servidor falhou");
            e.printStackTrace();
        }
    }
}
