import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Agencia {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            String ip = "localhost";
            Registry registry = LocateRegistry.getRegistry(ip, 1099);
            BancoService banco = (BancoService) registry.lookup("BancoService");

            while (true) {
                System.out.println("Qual das operações deseja realizar na AGÊNCIA:");
                System.out.println("[1] Abrir conta");
                System.out.println("[2] Fechar conta");
                System.out.println("[3] Depositar em conta");
                System.out.println("[4] Sacar em conta");
                System.out.println("[5] Consulta de saldo em conta");
                int operacao = in.nextInt();

                if (operacao == 1) {
                    System.out.println("Digite o nome do titular da nova conta:");
                    String novoTitular = in.next();
                    int numeroNovaconta = banco.abrirConta(novoTitular);
                    System.out.println("Conta de " + novoTitular + " com numero " + numeroNovaconta + " criada.");
                } else if (operacao == 2) {
                    System.out.println("Digite o numero da conta a ser fechada:");
                    int numeroContaFechar = in.nextInt();
                    banco.fecharConta(numeroContaFechar);
                    System.out.println("Conta " + numeroContaFechar + " fechada");
                } else if (operacao == 3) {
                    System.out.println("Digite o numero da conta a depositar:");
                    int numeroContaDepositar = in.nextInt();
                    System.out.println("Digite o valor de deposito:");
                    double valorDeposito = in.nextDouble();
                    banco.depositar(numeroContaDepositar, valorDeposito);
                    System.out.println(valorDeposito + " depositado na conta " + numeroContaDepositar);
                } else if (operacao == 4) {
                    System.out.println("Digite o numero da conta a sacar:");
                    int numeroContaSacar = in.nextInt();
                    System.out.println("Digite o valor de saque:");
                    double valorSaque = in.nextDouble();
                    banco.sacar(numeroContaSacar, valorSaque);
                    System.out.println(valorSaque + " sacado da conta " + numeroContaSacar);
                } else if (operacao == 5) {
                    System.out.println("Digite o numero da conta para consultar saldo:");
                    int numeroContaConsulta = in.nextInt();
                    System.out.println("Saldo conta " + numeroContaConsulta + ": " + banco.getSaldoConta(numeroContaConsulta));
                } else {
                    System.out.println("Operacao inválida");
                }

                System.out.println("Deseja realizar mais alguma operação na AGÊNCIA?");
                System.out.println("[1] Sim\n[2] Nao");
                int operacao2 = in.nextInt();
                if (operacao2 == 2)
                    break;
            }
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
