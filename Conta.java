public class Conta {
    private int numero;
    private String titular;
    private double saldo;

    public Conta(int numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public synchronized void depositar(double valor) {
        if (valor> 0) {
            saldo += valor;
        } else {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo");
        }
    }

    public synchronized void saque(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
        } else {
            throw new IllegalArgumentException("Saldo insuficente ou valor invalido");
        }
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}
