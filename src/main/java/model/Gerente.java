package model;

import model.pessoas.PessoaFisica;
import java.util.ArrayList;
import utils.*;

public class Gerente extends PessoaFisica {
    private Lista<Conta> contas;
    private double salario;
    private static int numContas;
    private final int LIMITE_DE_CONTAS = 3;
    private final int LIMITE_DE_GERENTES = 10;

    public Gerente() {

    }

    public Gerente(String nome, String sobrenome, String cpf, String nascimento, String email, String cep,
            double salario) {
        super(nome, sobrenome, cpf, nascimento, email, cep);
        this.salario = salario;

        this.contas = new Lista<>();

    }

    public boolean incrementarNumContas(Conta cont) {

        if (contas.getQuantidade() < LIMITE_DE_CONTAS && numContas < LIMITE_DE_GERENTES) {
            contas.adicionar(cont);
            numContas++;
            return true;
        } else {
            return false;
        }
    }

    public static int obterContas() {
        return numContas;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Nome Completo: " + this.getNome() +" "+ this.getSobrenome() + " CPF: " + this.getDoc();
    }

    public String detalhar() {
        return String.format("Nome: %s\nSobrenome: %s\nCPF: %s\nEmail: %s\n\nCEP: %s\nNascimento: %s\nSalario: %s",
                getNome(), getSobrenome(), getDoc(), getEmail(), getCep(), getSalario());

    }

    @Override
    public int hashCode() {
        return getDoc().hashCode();
    }

}