package repository.lista;

import model.Pessoa;
import utils.Lista;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import repository.IPessoaRepository;

public class PessoaRepository implements IPessoaRepository {
    private Lista<Pessoa> pessoas;

    public PessoaRepository() {
        this.pessoas = new Lista<>();
    }

    @Override
    public Lista<Pessoa> getPessoas() {
        return pessoas;
    }

    public boolean inserir(Pessoa pessoa) {
        pessoas.adicionar(pessoa);
        return true;
    }

    public Pessoa buscar(String cpf) {
        Pessoa c = new Pessoa();
        c.setDoc(cpf);
        int indice = pessoas.obterIndice(c);
        return pessoas.obterElemento(indice);
    }

    @Override
    public Pessoa atualizar(Pessoa pessoa) {
        int i = pessoas.obterIndice(pessoa);
        pessoas.adicionar(i, pessoa);
        return pessoa;
    }

    public Pessoa[] obterPessoas() { // findAll
        Pessoa[] clientesArray = new Pessoa[pessoas.getQuantidade()];
        return pessoas.obterArray(clientesArray);
    }

    public boolean apagar(Pessoa cliente) {
        pessoas.remover(cliente);
        return true;

    }

    public boolean salvar() { // loadAll
        try {
            FileOutputStream arquivo = new FileOutputStream("clientesLista.dat");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            Pessoa[] clientesArray = new Pessoa[pessoas.getQuantidade()];
            out.writeObject(pessoas.obterArray(clientesArray));
            System.out.println("Clientes salvos!!");
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean carregar() { // persistAll
        try {
            FileInputStream arq = new FileInputStream("clientesLista.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            Pessoa[] loader = (Pessoa[]) in.readObject();
            pessoas.adicionar(loader);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

}
