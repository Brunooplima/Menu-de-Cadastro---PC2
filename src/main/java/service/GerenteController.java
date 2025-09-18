package service;

import model.enumeration.MenuOptions;
import model.enumeration.TipoRepository;
import view.GerenteView;
import model.*;
import utils.*;
import repository.lista.*;
import repository.IGerenteRepository;
import view.ViewCliSimples;

public class GerenteController {
    private IGerenteRepository gerentes;
    private GerenteView gerenteView;
    private ViewCliSimples viewCli;

    public Lista<Gerente> getGerenteList() {
        return gerentes.getGerentes();
    }

    public GerenteController(TipoRepository tipo) {
        this.gerentes = new GerenteRepository();
        this.gerenteView = new GerenteView();
        this.viewCli = new ViewCliSimples();

        switch (tipo) {

            case LISTA:
                gerentes = new repository.lista.GerenteRepository();
                break;

            case VETOR:
                gerentes = new repository.vetor.GerenteRepository();
                break;

            case BANCO:

                break;

            default:
                break;
        }
    }

    public void start() {

        MenuOptions option;
        do {
            option = gerenteView.askOptionMenu();

            switch (option) {

                case CREATE:
                    Gerente gerente = gerenteView.createGerente();
                    gerentes.inserir(gerente);

                    break;

                case READ:
                    listGerentes();
                    break;

                case UPDATE:
                    atualizar();
                    break;

                case DELETE:
                    deletGerente();
                    break;

                case LIST:
                    detalheGerentes();

                    break;

                case VOLTAR:

                    break;

            }
        } while (option != MenuOptions.VOLTAR);

    }

    public void listGerentes() {
        Gerente[] g = gerentes.obterGerentes();
        gerenteView.listarGerentes(g);
    }

    public void detalheGerentes() {
        Gerente[] g = gerentes.obterGerentes();
        gerenteView.detalhesGerentes(g);
    }

    public void atualizar() {
        String cpf = gerenteView.obterCPF();
        Gerente gerente = gerentes.buscar(cpf);
        if (gerente != null) {
            gerenteView.atualizarGerente(gerente);
            gerentes.atualizar(gerente);
        } else {
            viewCli.showMessage("Pessoa não existe!");
        }
    }

    public void deletGerente() {
        Gerente[] g = gerentes.obterGerentes();
        String cpf = viewCli.askForString("Digite o CPF que deseja excluir o Gerente: ");
        for (int i = 0; i < g.length; i++) {
            Gerente p = g[i];
            if (p.getDoc().equals(cpf)) {
                gerentes.apagar(p);
            }
            if (gerentes.buscar(cpf) == null) {
                viewCli.showMessage("Gerente excluido com sucesso!!");
            }

        }

    }

    public Pessoa buscarGerente(String cpf) {
        return gerentes.buscar(cpf);

    }

    public void persistAll() {
        gerentes.carregar();
    }

    public void loadAll() {
        gerentes.salvar();
    }

}
