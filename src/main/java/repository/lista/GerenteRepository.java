package repository.lista;

import model.Gerente;
import utils.Lista;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import repository.IGerenteRepository;

public class GerenteRepository implements IGerenteRepository {
    private Lista<Gerente> gerentes;

    public GerenteRepository() {
        this.gerentes = new Lista<>();
    }

    @Override
    public Lista<Gerente> getGerentes() {
        return gerentes;
    }

    public boolean inserir(Gerente gerente) {
        gerentes.adicionar(gerente);
        return true;
    }

    public Gerente buscar(String cpf) {
        Gerente g = new Gerente();
        g.setDoc(cpf);
        int indice = gerentes.obterIndice(g);
        return gerentes.obterElemento(indice);
    }

    @Override
    public Gerente atualizar(Gerente gerente) {
        int i = gerentes.obterIndice(gerente);
        gerentes.adicionar(i, gerente);
        return gerente;
    }

    public Gerente[] obterGerentes() { // findAll
        Gerente[] clientesArray = new Gerente[gerentes.getQuantidade()];
        return gerentes.obterArray(clientesArray);
    }

    public boolean apagar(Gerente funcionario) {
        gerentes.remover(funcionario);
        return true;

    }

    public boolean salvar() { // loadAll
        try {
            FileOutputStream arquivo = new FileOutputStream("gerentesLista.dat");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            Gerente[] gerentesArray = new Gerente[gerentes.getQuantidade()];
            out.writeObject(gerentes.obterArray(gerentesArray));
            System.out.println("Gerentes salvos!!");
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean carregar() { // persistAll
        try {
            FileInputStream arq = new FileInputStream("gerentesLista.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            Gerente[] loader = (Gerente[]) in.readObject();
            gerentes.adicionar(loader);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

}
