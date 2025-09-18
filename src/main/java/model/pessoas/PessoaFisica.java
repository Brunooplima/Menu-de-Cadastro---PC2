package model.pessoas;

import model.*;
import model.enumeration.TipoSexo;

public class PessoaFisica extends Pessoa {

    private String sobrenome;
    private String nascimento;
    private TipoSexo sexo; // fzr algum dia

    public PessoaFisica() {

    }

    public PessoaFisica(String nome, String sobrenome, String cpf, String nascimento, String email, String cep) {
        super(nome, cep, email, cpf);
        this.sobrenome = sobrenome;
        this.nascimento = nascimento;

    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    // public void setNascimento(String nascimento) {
    // try {
    // Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
    // this.nascimento = data.toInstant();
    // } catch (Exception E) {

    // }
    // }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNascimento() {
        return nascimento;
    }

    @Override
    public String toString() {
        return "Nome Completo: " + this.getNome() + this.getSobrenome() + " CPF: " + this.getDoc();
    }

    public String detalhar() {
        return String.format("Nome: %s\nSobrenome: %s\nCPF: %s\nEmail: %s\n\nCEP: %s\nNascimento: %s\n", getNome(),
                sobrenome, getDoc(), getEmail(), getCep(), nascimento);

    }

    // public boolean equals(PessoaFisica obj) {
    // return getDoc().equals(c.getDoc());
    // }

    @Override
    public int hashCode() {
        return getDoc().hashCode();
    }

}
