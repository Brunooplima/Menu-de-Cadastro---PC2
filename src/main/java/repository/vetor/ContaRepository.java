package repository.vetor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Conta;
import repository.IContaRepository;
import utils.*;
import view.ViewCliSimples;

public class ContaRepository implements IContaRepository {

    protected Conta objetos[];
    protected int tamanho;
    protected ViewCliSimples view;

    public ContaRepository() {
        this.objetos = new Conta[100];
        this.view = new ViewCliSimples();

    }

    public ContaRepository(int tamanho) {
        this();
        this.objetos = new Conta[tamanho];
    }

    public ContaRepository(Conta x[]) {
        this.objetos = x;
    }

    public Lista<Conta> getContas() {
        Lista<Conta> contas = new Lista<>();
        contas.adicionar(objetos);
        return contas;
    }

    public boolean inserir(Conta novo) {
        boolean incluido = false;

        for (int i = 0; i < objetos.length && !incluido; i++) {
            if (objetos[i] == null) {
                objetos[i] = novo;
                incluido = true;
            }
        }
        return incluido;
    }

    public Conta[] obterContas() {
        return objetos;
    }

    public boolean apagar(int numero) {
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null) {
                if (numero == objetos[i].getNumero()) {
                    if (i == objetos.length - 1) {
                        objetos[i] = null;
                    } else {
                        for (int j = 0; j < objetos.length; j++) {
                            objetos[j] = objetos[j + 1];
                        }
                    }
                    view.showMessage("Usuário excluido!!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean apagar(Conta pessoaRemover) {

        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null) {
                if (objetos[i].equals(pessoaRemover)) {
                    objetos[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public Conta buscar(int numero) {

        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null) {
                if (objetos[i].getNumero() == numero) {
                    return objetos[i];
                }
            }
        }

        return null;
    }

    public Conta atualizar(Conta toUpdate) {
        for (int i = 0; i < objetos.length; i++) {
            Conta conta = objetos[i];
            if (conta != null && conta.equals(toUpdate)) {
                objetos[i] = toUpdate;
                return toUpdate;
            }
        }
        return null;
    }

    public boolean carregar() {
        try {
            FileOutputStream arquivo = new FileOutputStream("contasVetor.dat");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            out.writeObject(objetos);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean salvar() {
        try {
            FileInputStream arq = new FileInputStream("contasVetor.dat");
            ObjectInputStream in = new ObjectInputStream(arq);
            objetos = (Conta[]) in.readObject();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
