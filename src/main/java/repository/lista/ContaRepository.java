package repository.lista;

import model.Conta;
import utils.Lista;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import view.*;
import repository.IContaRepository;

public class ContaRepository implements IContaRepository {

    private Lista<Conta> contas;
    private ViewCliSimples viewCli;

    public ContaRepository() {
        this.contas = new Lista<>();
        this.viewCli = new ViewCliSimples();
    }

    @Override
    public Lista<Conta> getContas() {
        return contas;
    }

    public boolean inserir(Conta conta) {
        contas.adicionar(conta);
        return true;
    }

    public Conta buscar(int numero) {
        Conta cont = new Conta();
        int indice = contas.obterIndice(cont);
        return contas.obterElemento(indice);
    }

    @Override
    public Conta atualizar(Conta conta) {
        int i = contas.obterIndice(conta);
        contas.adicionar(i, conta);
        return conta;
    }

    public Conta[] obterContas() { // findAll
        Conta[] contasArray = new Conta[contas.getQuantidade()];
        return contas.obterArray(contasArray);
    }

    public boolean apagar(Conta conta) {
        contas.remover(conta);
        return true;
    }

    public boolean salvar() { // loadAll
        try {
            FileOutputStream arquivo = new FileOutputStream("contasLista.dat");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            Conta[] contasArray = new Conta[contas.getQuantidade()];
            out.writeObject(contas.obterArray(contasArray));
            viewCli.showMessage("Contas salvas!!");
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean carregar() { // persistAll
        try {
            FileInputStream arq = new FileInputStream("contasLista.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            Conta[] loader = (Conta[]) in.readObject();
            contas.adicionar(loader);
            return true;
        } catch (Exception ex) {
            viewCli.showMessage("Erro!!");
            System.out.println(ex);
            return false;
        }
    }

}
