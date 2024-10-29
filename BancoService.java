import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoService extends Remote {
    int abrirConta(String cliente) throws RemoteException;
    void fecharConta(int numeroConta) throws RemoteException;
    void depositar(int numeroConta, double valor) throws RemoteException;
    void sacar(int numeroConta, double valor) throws RemoteException;
    double getSaldoConta(int numeroConta) throws RemoteException;

}
