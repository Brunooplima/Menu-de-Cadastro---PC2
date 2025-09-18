package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.Extrato;
import model.enumeration.*;
import utils.Lista;

public class MovimentoView {
    private Scanner sc;
    private ViewCliSimples viewCli;

    public MovimentoView() {
        sc = new Scanner(System.in);
        this.viewCli = new ViewCliSimples();

    }

    public MenuOptions askOptionMenu() {
        int inteiro = 1;
        do {
            try {
                viewCli.showMessage("\n-------------------------------");
                viewCli.showMessage("--     MENU MOVIMENTAÇÃO     --");
                viewCli.showMessage("-------------------------------");
                viewCli.showMessage("1 - ver Saldo");
                viewCli.showMessage("2 - Depositar");
                viewCli.showMessage("3 - Saque");
                viewCli.showMessage("4 - Transferência");
                viewCli.showMessage("5 - Extrato");
                viewCli.showMessage("0 - Voltar");

                viewCli.showMessag("Informe uma opção: ");
                inteiro = sc.nextInt();
                viewCli.showMessage(" ");

                switch (inteiro) {
                    case 1:
                        return MenuOptions.SALDO;
                    case 2:
                        return MenuOptions.DEPOSITO;
                    case 3:
                        return MenuOptions.SAQUE;
                    case 4:
                        return MenuOptions.TRANSFERENCIA;
                    case 5:
                        return MenuOptions.EXTRATO;
                    case 0:
                        return MenuOptions.VOLTAR;
                }
            } catch (InputMismatchException ex) {
                viewCli.showMessage("Tente Novamente! (" + "Incorrect input: an integer is required)");
                sc.nextLine();
            }
        } while (true);
    }

    public void listarExtrato(Lista<Extrato> extratos) {

        if (extratos.getQuantidade() == 0) {
            viewCli.showMessage("Não há nenhum cliente!!");
            viewCli.showMessage(" ");
        } else {
            for (int i = 0; i < extratos.getQuantidade(); i++) {
                Extrato extrato = extratos.obterElemento(i);
                viewCli.showMessage(extrato.getMenssagem() + " " + String.valueOf(extrato.getValor()));

                viewCli.showMessage("------------------------");
            }
        }
    }

}