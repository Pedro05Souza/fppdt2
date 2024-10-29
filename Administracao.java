import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Administracao extends UnicastRemoteObject implements BancoService {

    private Map<Integer, Conta> contas = new HashMap<>();
    private int numeroConta = 1;

    protected Administracao() throws RemoteException {
        super();
    }

    public synchronized int abrirConta(String cliente) throws RemoteException {
        Conta novaConta = new Conta(numeroConta, cliente);
        contas.put(numeroConta, novaConta);
        return numeroConta++;
    }

    public synchronized void fecharConta(int numeroConta) throws RemoteException {
        contas.remove(numeroConta);
    }

    public synchronized void depositar(int numeroConta, double valor) throws RemoteException {
        Conta conta = contas.get(numeroConta);
        if (conta != null) {
            conta.depositar(valor);
        } else {
            throw new IllegalArgumentException("Conta inexistente");
        }
    }

    public synchronized void sacar(int numeroConta, double valor) throws RemoteException {
        Conta conta = contas.get(numeroConta);
        if (conta != null) {
            conta.saque(valor);
        } else {
            throw new IllegalArgumentException("Conta inexistente");
        }
    }

    public synchronized double getSaldoConta(int numeroConta) throws RemoteException {
        Conta conta = contas.get(numeroConta);
        if (conta != null) {
            return conta.getSaldo();
        } else {
            throw new IllegalArgumentException("Conta inexistente");
        }
    }
}
