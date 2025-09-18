package model.pessoas;

import model.*;

public class PessoaJuridica extends Pessoa {

    public PessoaJuridica() {

    }

    public PessoaJuridica(String nome, String email, String cep, String cnpj) {
        super(nome, cep, email, cnpj);

    }

    @Override
    public String toString() {
        return "Nome Completo: " + this.getNome() + " CNPJ: " + this.getDoc();
    }

    public String detalhar() {
        return String.format("Nome: %s\nCNPJ: %s\nCep: %s\nEmail: %s\n", getNome(), getDoc(), getCep(), getEmail());

    }

    @Override
    public int hashCode() {
        return getDoc().hashCode();
    }

}
