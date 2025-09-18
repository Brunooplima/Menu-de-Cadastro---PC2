package model.contas;

import model.*;

public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(Pessoa titular, double saldo, double limite) {
        super(titular, saldo);
        this.limite = limite;

    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimite() {
        return limite;

    }

    @Override
    public boolean sacar(double x) {
        if (getSaldo() < x && (x - this.getSaldo()) < this.limite) {
            this.limite = limite - (x - this.getSaldo());
            this.setSaldo(0);
            return true;
        } else if (x <= this.getSaldo() - x) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Limite: " + this.getLimite();
    }

    public String detalhar() {
        return String.format("%s\n Limite: %s\n", limite);

    }

    // public boolean equals(Conta obj) {
    // return this.numero == (obj.getNumero());
    // }

    // @Override
    // public boolean equals(Object obj) {
    // if (obj instanceof Conta) {
    // Conta c = (Conta) obj;
    // return this.numero == (c.getNumero());
    // } else {
    // return false;
    // }
    // }

}
