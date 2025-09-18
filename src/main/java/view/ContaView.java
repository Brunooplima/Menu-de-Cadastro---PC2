package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.Conta;
import model.contas.ContaCorrente;
import model.contas.ContaPoupanca;
import model.enumeration.*;
import model.*;

public class ContaView {
    private Scanner sc;
    private ViewCliSimples viewCli;

    public ContaView() {
        sc = new Scanner(System.in);
        this.viewCli = new ViewCliSimples();

    }

    public MenuOptions askOptionMenu() {
        int inteiro = 1;
        do {
            try {
                viewCli.showMessage("\n---------------------------");
                viewCli.showMessage("--    MENU CONTA       --");
                viewCli.showMessage("---------------------------");
                viewCli.showMessage("1 - Cadastrar Conta");
                viewCli.showMessage("2 - Listar Conta");
                viewCli.showMessage("3 - Atualizar Limite");
                viewCli.showMessage("4 - Excluir Conta");
                viewCli.showMessage("5 - Visualizar Extrato");
                viewCli.showMessage("0 - Voltar");

                viewCli.showMessag("Informe uma opção: ");
                inteiro = sc.nextInt();
                viewCli.showMessage(" ");

                switch (inteiro) {
                    case 1:
                        return MenuOptions.CREATE;
                    case 2:
                        return MenuOptions.READ;
                    case 3:
                        return MenuOptions.UPDATE;
                    case 4:
                        return MenuOptions.DELETE;
                    case 5:
                        return MenuOptions.LIST;
                    case 0:
                        return MenuOptions.VOLTAR;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Tente Denovo! (" + "Incorrect input: an integer is required)");
                sc.nextLine();
            }
        } while (true);
    }

    public TipoConta askForTipoConta() {

        do {
            viewCli.showMessage("---------------------");
            viewCli.showMessage("1 - Conta Corrente");
            viewCli.showMessage("2 - Conta Poupanca");
            viewCli.showMessage("---------------------");

            int option = viewCli.askForInt("Digite o tipo de Conta: ");

            switch (option) {
                case 1:
                    return TipoConta.CONTA_CORRENTE;

                case 2:
                    return TipoConta.CONTA_POUPANCA;
            }
        } while (true);
    }

    public Conta createConta(Pessoa p) {

        TipoConta tipo = askForTipoConta();
        double saldo = askForSaldo();

        if (tipo == TipoConta.CONTA_CORRENTE) {
            double limite = askForlimite(saldo);

            return new ContaCorrente(p, saldo, limite);

        } else {
            return new ContaPoupanca(p, saldo);

        }

    }

    public double askForlimite(double saldo) {
        double limite;
        do {
            limite = viewCli.askForDouble("Informe o valor do limite menor ou igual do que o Saldo: ");
        } while (limite > saldo);
        return limite;
    }

    public double askForSaldo() {
        double saldo;
        do {
            saldo = viewCli.askForDouble("Informe o valor do saldo maior ou igual a zero: ");
        } while (saldo < 0);
        return saldo;
    }

    public void readForConta(Conta[] contas) {
        if (contas.length == 0) {
            System.out.println("Não há nenhuma Cona!!");
            System.out.println(" ");
        } else {
            for (int i = 0; i < contas.length; i++) {
                Conta conta = contas[i];
                if (conta != null) {
                    viewCli.showMessage("Titular: " + conta.getTitular());
                    viewCli.showMessage("Número da Conta: " + conta.getNumero());
                    viewCli.showMessage("--- ---------------- ----");
                }
            }
        }
    }

    public void listConta(Conta[] contas) {
        if (contas.length == 0) {
            System.out.println("Não há nenhuma Conta!!");
            System.out.println(" ");
        } else {
            for (int i = 0; i < contas.length; i++) {
                Conta conta = contas[i];
                if (conta != null) {
                    conta.toString();
                }
                viewCli.showMessage("---- ---------------- ----");
            }
        }
    }

}
