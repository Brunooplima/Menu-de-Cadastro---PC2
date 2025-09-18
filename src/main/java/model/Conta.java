package model;

import java.time.Instant;
import utils.Lista;

/**
 *
 * @author BRUNO
 */
public class Conta {

    private static int quantidade = 0;
    private int numero;
    private Pessoa titular;
    private double saldo;
    private double limite;
    private Lista<Extrato> extrato;
    private Gerente gerente;

    public Conta() {
        extrato = new Lista<Extrato>();
    }

    public Conta(Pessoa titular, double saldo) {
        this();
        this.numero = quantidade++;
        this.titular = titular;
        this.saldo = saldo;
        extrato.adicionar(new Extrato(("Saldo Inicial: "), saldo));
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean depositar(double deposit) {
        if (deposit > 0) {
            double valorTotal = this.saldo + deposit;
            this.saldo = valorTotal;
            extrato.adicionar(new Extrato((Instant.now() + " - Deposito de "), deposit));
            return true;
        }
        return false;
    }

    public boolean sacar(double saque) {
        if (saque <= this.saldo + this.limite) {
            double subitrairSaldo = this.saldo - saque;
            this.saldo = subitrairSaldo;
            extrato.adicionar(new Extrato((Instant.now() + " - Saque de "), saque));
            return true;

        }
        return false;
    }

    public boolean transferir(double valor, Conta conta) {
        if (isSaldoDisponivel(valor) && valor > 0) {
            this.sacar(valor);
            conta.depositar(valor);
            return true;
            // é apenas verdadeiro se houve saque e deposito
        }
        return false;

    }

    public boolean isSaldoDisponivel(double valor) {
        return this.saldo >= valor;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getDepositar() {
        return saldo;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public Lista<Extrato> getExtrato() {
        return extrato;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
        gerente.incrementarNumContas(this);
    }

    public Gerente getGerente() {
        return gerente;
    }

    @Override
    public String toString() {
        return "Titular: " + this.getTitular() + " Numero: " + this.getNumero();
    }

    public String detalhar() {
        return String.format("Numero: %d\nTitular: %s\nSaldo: %s\n", numero, titular, saldo);

    }

    @Override
    public boolean equals(Object obj) {

        Conta c = (Conta) obj;
        return this.numero == (c.getNumero());

    }

    @Override
    public int hashCode() {
        return numero;
    }

}