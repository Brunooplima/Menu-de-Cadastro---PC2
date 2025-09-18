package model;

import java.io.Serializable;

/**
 *
 * @author BRUNO
 */
// abstract
public class Pessoa extends Object implements Serializable {

    private String nome;
    private String cep;
    private String email;
    private String documento;

    public Pessoa() {

    }

    public Pessoa(String nome, String cep, String email, String documento) {
        // this.setDoc(documento);
        this.nome = nome;
        this.cep = cep;
        this.email = email;
        this.documento = documento;

    }

    // public String getId() {
    // return this.nome;

    // }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDoc(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }

    public String getDoc() {
        return documento;
    }

    public String detalhar() {
        return String.format("Nome: %s\nCEP: %s\nEmail: %s\n", nome, cep, email);

    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Pessoa) {
            Pessoa c = (Pessoa) obj;

            return getDoc().equals(c.getDoc());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getDoc().hashCode();
    }

}
