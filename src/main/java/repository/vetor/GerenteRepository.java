package repository.vetor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Gerente;
import view.ViewCliSimples;
import repository.IGerenteRepository;
import utils.*;

public class GerenteRepository implements IGerenteRepository {

    protected Gerente gerentes[];
    protected int tamanho;
    private ViewCliSimples viewCli;

    public GerenteRepository() {
        this.gerentes = new Gerente[100];
        this.viewCli = new ViewCliSimples();

    }

    public GerenteRepository(int tamanho) {
        this();
        this.gerentes = new Gerente[tamanho];
    }

    public GerenteRepository(Gerente x[]) {
        this.gerentes = x;
    }

    public Lista<Gerente> getGerentes() {
        Lista<Gerente> gr = new Lista<>();
        gr.adicionar(gerentes);
        return gr;
    }

    public boolean inserir(Gerente novo) {
        boolean incluido = false;

        for (int i = 0; i < gerentes.length && !incluido; i++) {
            if (gerentes[i] == null) {
                gerentes[i] = novo;
                incluido = true;
            }
        }
        return incluido;
    }

    public Gerente[] obterGerentes() {
        return gerentes;
    }

    public boolean apagar(String gerenteRemover) {

        for (int i = 0; i < gerentes.length; i++) {
            if (gerentes[i] != null) {
                if (gerenteRemover.equals(gerentes[i].getDoc())) {
                    if (i == gerentes.length - 1) {
                        gerentes[i] = null;
                    } else {
                        for (int j = 0; j < gerentes.length; j++) {
                            gerentes[j] = gerentes[j + 1];
                        }
                    }
                    viewCli.showMessage("Usuário excluido!!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean apagar(Gerente gerente) {
        for (int i = 0; i < gerentes.length; i++) {
            Gerente auxGerente = gerentes[i];
            if (gerente != null && gerente.equals(auxGerente)) {
                gerentes[i] = null;
                return true;
            }
        }

        return false;
    }

    public Gerente buscar(String documento) {

        for (int i = 0; i < gerentes.length; i++) {
            if (gerentes[i] != null) {
                if (gerentes[i].getDoc().equals(documento)) {
                    return gerentes[i];
                }
            }
        }
        return null;
    }

    public Gerente atualizar(Gerente toUpdate) {
        for (int i = 0; i < gerentes.length; i++) {
            Gerente gerente = gerentes[i];
            if (gerente != null && gerente.equals(toUpdate)) {
                gerentes[i] = toUpdate;
                return toUpdate;
            }
        }
        return null;
    }

    public boolean carregar() {
        try {
            FileOutputStream arquivo = new FileOutputStream("GerentesVetor.dat");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            out.writeObject(gerentes);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean salvar() {
        try {
            FileInputStream arq = new FileInputStream("GerentesVetor.dat");
            ObjectInputStream in = new ObjectInputStream(arq);
            gerentes = (Gerente[]) in.readObject();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
