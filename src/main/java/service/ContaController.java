package service;

import view.ViewCliSimples;
import view.ContaView;
import model.enumeration.MenuOptions;
import model.enumeration.TipoRepository;
import model.*;
import utils.*;
import repository.IContaRepository;
import repository.lista.*;

public class ContaController {
    private IContaRepository contas;
    private ViewCliSimples viewCli;
    private ContaView contaView;
    private PessoaController clienteC;

    public Lista<Conta> getContasList() {
        return contas.getContas();
    }

    public ContaController(TipoRepository tipo, PessoaController pessoaController) {

        this.clienteC = pessoaController;
        this.contas = new ContaRepository();
        this.viewCli = new ViewCliSimples();
        this.contaView = new ContaView();

        switch (tipo) {

            case LISTA:
                contas = new repository.lista.ContaRepository();
                break;

            case VETOR:
                contas = new repository.vetor.ContaRepository();
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
            option = contaView.askOptionMenu();

            switch (option) {

                case CREATE:
                    cadastrar();
                    break;

                case READ:
                    lerContas();
                    break;

                case UPDATE:
                    // updateConta();
                    break;

                case DELETE:
                    deletConta();
                    break;

                case LIST:
                    detalheContas();
                    break;
                case VOLTAR:

                    break;

            }
        } while (option != MenuOptions.VOLTAR);

    }

    public Conta buscar(int b) {
        return contas.buscar(b);
    }

    public void cadastrar() {

        String documento = viewCli.askForString("Informe o CPF ou CNPJ: ");
        Pessoa c = clienteC.buscarPessoa(documento);
        if (c == null) {
            viewCli.showMessage("Cliente não existe!!");
        } else {
            Conta conta = contaView.createConta(c);
            contas.inserir(conta);
        }

    }

    public void lerContas() {
        Conta[] c = contas.obterContas();
        contaView.readForConta(c);
    }

    public void detalheContas() {
        Conta[] c = contas.obterContas();
        contaView.listConta(c);
    }

    public void deletConta() {
        Conta[] conta = contas.obterContas();
        int excluir = viewCli.askForInt("Informe o número da Conta que deseja excluir: ");
        contas.apagar(contas.buscar(excluir));
        if (contas.buscar(excluir) == null) {
            viewCli.showMessage("Conta excluida com sucesso!!");
        }
        viewCli.showMessag("Número atual de contas: " + conta.length);
        viewCli.showMessag("\nver se a conta está no Array:" + contas.buscar(excluir));
        viewCli.showMessage("\n------------------------------");

    }

    public void persistAll() {
        contas.carregar();
    }

    public void loadAll() {
        contas.salvar();
    }

    /*
     * public void updateConta() {
     * contaView.askForString("Informe o Número da Conta: "); Conta update =
     * contas.buscar(num);
     * 
     * double newLimite = contaView.askForDouble("Novo valor do limite: "); if
     * (newLimite > 0 && newLimite <= update.getSaldo()) { // dando erro double
     * valor = newLimite; update.setLimite(valor);
     * contaView.showMessag("Atualização feita com sucesso!!"); }
     * 
     * }
     */

}

