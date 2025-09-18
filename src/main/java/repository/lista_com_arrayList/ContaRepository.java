package repository.lista_com_arrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Conta;

public class ContaRepository {

    protected List<Conta> lista;

    public ContaRepository() {
        lista = new ArrayList<>();
    }

    public ContaRepository(Conta listaNova[]) {
        this.lista = new ArrayList<>(Arrays.asList(listaNova));
    }

    public boolean create(Conta novo) {
        return lista.add(novo);
    }

    public Conta[] findAll() {
        return (Conta[]) lista.toArray();
    }

    public Conta update(Conta toUpdate) {
        int i = lista.indexOf(toUpdate);
        lista.add(i, toUpdate);
        return toUpdate;
    }

    public boolean delete(Conta aRemover) {
        return lista.remove(aRemover);
    }

    public void persistAll() {
        try {
            FileOutputStream arquivo = new FileOutputStream("pessoasLista.dat");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            out.writeObject((Conta[]) lista.toArray(new Conta[lista.size()]));
            System.out.println("Contas Salvas");
        } catch (IOException e) {
            System.out.println("Problemas ao Salvar");
        }
    }

    public void loadAll() {
        try {
            FileInputStream arq = new FileInputStream("contasLista.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            Conta[] loader = (Conta[]) in.readObject();
            lista.addAll(Arrays.asList(loader));
        } catch (Exception ex) {
            System.out.println("Erro na hora de gravar o arquivo");
            System.out.println(ex);
        }
    }

    public Conta find(Conta conta) {

        int index = lista.indexOf(conta);
        return lista.get(index);
    }

}
