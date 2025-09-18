package repository.lista_com_arrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Pessoa;

public class PessoaRepository {

    protected List<Pessoa> lista;

    public PessoaRepository() {
        lista = new ArrayList<>();
    }

    public PessoaRepository(Pessoa listaNova[]) {
        this.lista = new ArrayList<>(Arrays.asList(listaNova));
    }

    public boolean create(Pessoa novo) {
        return lista.add(novo);
    }

    public Pessoa[] findAll() {
        return (Pessoa[]) lista.toArray();
    }

    public Pessoa update(Pessoa toUpdate) {
        int i = lista.indexOf(toUpdate);
        lista.add(i, toUpdate);
        return toUpdate;
    }

    public boolean delete(Pessoa aRemover) {
        return lista.remove(aRemover);
    }

    public Pessoa find(Pessoa pessoa) {

        int index = lista.indexOf(pessoa);
        return lista.get(index);
    }

    public void persistAll() {
        try {
            FileOutputStream arquivo = new FileOutputStream("pessoasLista.dat");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            out.writeObject((Pessoa[]) lista.toArray(new Pessoa[lista.size()]));
            System.out.println("Clientes Salvos");
        } catch (IOException e) {
            System.out.println("Problemas ao Salvar");
        }
    }

    public void loadAll() {
        try {
            FileInputStream arq = new FileInputStream("pessoasLista.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            Pessoa[] loader = (Pessoa[]) in.readObject();
            lista.addAll(Arrays.asList(loader));
        } catch (Exception ex) {
            System.out.println("Erro na hora de gravar o arquivo");
            System.out.println(ex);
        }
    }

}
