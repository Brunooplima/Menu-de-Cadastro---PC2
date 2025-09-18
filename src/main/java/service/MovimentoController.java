package service;

import java.util.Scanner;
import java.util.InputMismatchException;

import view.MovimentoView;
import model.Conta;
import model.enumeration.MenuOptions;
import utils.Lista;
import model.Extrato;
import repository.lista.*;
import service.*;
import view.ViewCliSimples;

public class MovimentoController {

    ContaController controller;
    Lista<Extrato> extrato;
    private MovimentoView movimentoView;
    private Scanner sc;
    private ViewCliSimples viewCli;

    public MovimentoController(ContaController controller) {
        this.controller = controller;
        this.movimentoView = new MovimentoView();
        sc = new Scanner(System.in);
        this.viewCli = new ViewCliSimples();
    }

    public void start() {

        MenuOptions option;
        do {
            option = movimentoView.askOptionMenu();

            switch (option) {

                case SALDO:
                    saldoMenumoviment();
                    break;

                case DEPOSITO:
                    depositoMoviment();
                    break;

                case SAQUE:
                    saqueMoviment();
                    break;

                case TRANSFERENCIA:
                    transferMoviment();
                    break;

                case EXTRATO:
                    extratoMoviment();
                    break;
                case VOLTAR:

                    break;

            }
        } while (option != MenuOptions.VOLTAR);
    }

    public void saldoMenumoviment() {
        int num1 = viewCli.askForInt("Informe o Número da Conta: ");
        Conta c = controller.buscar(num1);

        if (c != null) {

            viewCli.showMessage("Saldo atual: R$ " + c.getSaldo());

            viewCli.showMessage("-----------------");

        } else {
            viewCli.showMessage("A conta de Origem não existe!!");
        }
    }

    public void depositoMoviment() {

        int num2 = viewCli.askForInt("Informe o Número da Conta: ");
        Conta c = controller.buscar(num2);

        if (c != null) {
            double deposit = viewCli.askForDouble("Valor a depositar: ");
            if (c.depositar(deposit)) {
                viewCli.showMessage("Deposito bem sucedido!!");
            } else {
                viewCli.showMessage("Deposito não efetuado!!");
            }
            viewCli.showMessage("-----------------");
        } else {
            viewCli.showMessage("A conta de Origem não existe!!");
        }
    }

    public void saqueMoviment() {

        int num3 = viewCli.askForInt("Informe o Número da Conta: ");
        Conta c = controller.buscar(num3);

        if (c != null) {
            double sac = viewCli.askForDouble("Valor do Saque: ");
            if (c.sacar(sac)) {
                viewCli.showMessage("Saque efetuado, novo saldo de " + c.getSaldo());
            } else {
                viewCli.showMessage("Saque não efetuado!!");
            }

            System.out.println("-----------------");

        } else {
            viewCli.showMessage("A conta de Origem não existe!!");
        }
    }

    public void transferMoviment() {
        int num1 = viewCli.askForInt("Informe o Número da Conta Origem: ");
        Conta c = controller.buscar(num1);

        if (c != null) {
            double valor = viewCli.askForDouble("Valor da Transferencia: ");

            int num2 = viewCli.askForInt("Informe o Número da Conta de Destine: ");
            Conta c2 = controller.buscar(num2);

            if (c2 != null) {
                if (c.transferir(valor, c2)) {
                    extrato = c2.getExtrato();
                    extrato.adicionar(new Extrato("Tranfer de", valor));
                    viewCli.showMessage("Transferência concluida!!");
                } else {
                    viewCli.showMessage("A transferencia não foi possível!!");
                }

            } else {
                viewCli.showMessage("A conta de Destino não existe!!");
            }

        } else {
            viewCli.showMessage("A conta de Origem não existe!!");
        }

        viewCli.showMessage("-----------------");
    }

    public void extratoMoviment() {
        int num1 = viewCli.askForInt("Informe o Número da Conta Origem: ");
        Conta c = controller.buscar(num1);

        if (c != null) {
            extrato = c.getExtrato();

            viewCli.showMessage("\nTitular: " + c.getTitular());
            viewCli.showMessage("Número da Conta: " + c.getNumero());
            viewCli.showMessage("Saque atual: " + c.getSaldo());
            viewCli.showMessage("Total de movimentações: " + extrato.getQuantidade() + "\n");

            Lista<Extrato> extrato = c.getExtrato();
            movimentoView.listarExtrato(extrato);
        } else {
            viewCli.showMessage("A conta de Origem não existe!!");
        }
    }

}
