package repository.vetor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Pessoa;
import utils.Lista;
import repository.IPessoaRepository;
import view.ViewCliSimples;

public class PessoaRepository implements IPessoaRepository {

    protected Pessoa pessoas[];
    protected int tamanho;
    protected ViewCliSimples view;

    public PessoaRepository() {
        this.pessoas = new Pessoa[100];
        this.view = new ViewCliSimples();
    }

    public PessoaRepository(int tamanho) {
        this();
        this.pessoas = new Pessoa[tamanho];
    }

    public PessoaRepository(Pessoa x[]) {
        this.pessoas = x;
    }

    public Lista<Pessoa> getPessoas() {
        Lista<Pessoa> ps = new Lista<>();
        ps.adicionar(pessoas);
        return ps;
    }

    public boolean inserir(Pessoa novo) {
        boolean incluido = false;

        for (int i = 0; i < pessoas.length && !incluido; i++) {
            if (pessoas[i] == null) {
                pessoas[i] = novo;
                incluido = true;
            }
        }
        return incluido;
    }

    public Pessoa[] obterPessoas() {
        return pessoas;
    }

    public boolean apagar(String documento) {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i] != null) {
                if (documento.equals(pessoas[i].getDoc())) {
                    if (i == pessoas.length - 1) {
                        pessoas[i] = null;
                    } else {
                        for (int j = 0; j < pessoas.length; j++) {
                            pessoas[j] = pessoas[j + 1];
                        }
                    }
                    view.showMessage("Usuário excluido!!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean apagar(Pessoa pessoa) {
        for (int i = 0; i < pessoas.length; i++) {
            Pessoa auxPessoa = pessoas[i];
            if (pessoa != null && pessoa.equals(auxPessoa)) {
                pessoas[i] = null;
                return true;
            }
        }

        return false;
    }

    public Pessoa buscar(String documento) {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i] != null) {
                if (pessoas[i].getDoc().equals(documento)) {
                    return pessoas[i];
                }
            }
        }
        return null;
    }

    public Pessoa atualizar(Pessoa toUpdate) {
        for (int i = 0; i < pessoas.length; i++) {
            Pessoa pessoa = pessoas[i];
            if (pessoa != null && pessoa.equals(toUpdate)) {
                pessoas[i] = toUpdate;
                return toUpdate;
            }
        }
        return null;
    }

    public boolean carregar() {
        try {
            FileOutputStream arquivo = new FileOutputStream("pessoasVetor.dat");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            out.writeObject(pessoas);
            // view.showMessage("Pessoas Salvas");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean salvar() {
        try {
            FileInputStream arq = new FileInputStream("pessoasVetor.dat");
            ObjectInputStream in = new ObjectInputStream(arq);
            pessoas = (Pessoa[]) in.readObject();
            return true;
        } catch (Exception ex) {
            // System.out.println("Erro na hora de gravar o arquivo");
            return false;
        }
    }

}
