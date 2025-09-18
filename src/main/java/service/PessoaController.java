package service;

import model.enumeration.MenuOptions;
import model.enumeration.TipoRepository;
import view.PessoaView;
import model.*;
import utils.*;
import repository.lista.*;
import repository.IPessoaRepository;
import view.ViewCliSimples;

public class PessoaController {
    private IPessoaRepository pessoas;
    private PessoaView pessoaView;
    private ViewCliSimples viewCli;

    public Lista<Pessoa> getPessoaList() {
        return pessoas.getPessoas();
    }

    public PessoaController(TipoRepository tipo) {
        this.pessoas = new PessoaRepository();
        this.pessoaView = new PessoaView();
        this.viewCli = new ViewCliSimples();

        switch (tipo) {

            case LISTA:
                pessoas = new repository.lista.PessoaRepository();
                break;

            case VETOR:
                pessoas = new repository.vetor.PessoaRepository();
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
            option = pessoaView.askOptionMenu();

            switch (option) {

                case CREATE:
                    Pessoa pessoa = pessoaView.createCliente();
                    pessoas.inserir(pessoa);

                    break;

                case READ:
                    listPessoas();
                    break;

                case UPDATE:
                    atualizar();
                    break;

                case DELETE:
                    deletPessoa();
                    break;

                case LIST:
                    detalhePessoas();

                    break;

                case VOLTAR:

                    break;

            }
        } while (option != MenuOptions.VOLTAR);

    }

    public void listPessoas() {
        Pessoa[] c = pessoas.obterPessoas();
        pessoaView.listarClientes(c);
    }

    public void detalhePessoas() {
        Pessoa[] c = pessoas.obterPessoas();
        pessoaView.detalhesClientes(c);
    }

    public void atualizar() {
        String cpf = pessoaView.obterCPF();
        Pessoa pessoa = pessoas.buscar(cpf);
        if (pessoa != null) {
            pessoaView.atualizarPessoa(pessoa);
            pessoas.atualizar(pessoa);
        } else {
            viewCli.showMessage("Pessoa não existe!");
        }
    }

    public void deletPessoa() {
        Pessoa[] c = pessoas.obterPessoas();
        String cpf = viewCli.askForString("Digite o CPF que deseja excluir o Cliente: ");
        for (int i = 0; i < c.length; i++) {
            Pessoa p = c[i];
            if (p.getDoc().equals(cpf)) {
                pessoas.apagar(p);
            }
            if (pessoas.buscar(cpf) == null) {
                viewCli.showMessage("Cliente excluido com sucesso!!");
            }

        }

    }

    public Pessoa buscarPessoa(String cpf) {
        return pessoas.buscar(cpf);

    }

    public void persistAll() {
        pessoas.carregar();
    }

    public void loadAll() {
        pessoas.salvar();
    }

}
