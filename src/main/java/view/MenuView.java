package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.enumeration.*;

public class MenuView {

    private Scanner sc;
    private ViewCliSimples viewCli;

    public MenuView() {
        sc = new Scanner(System.in);
        this.viewCli = new ViewCliSimples();
    }

    public MenuOptions askOptionMenuPrincial() {
        int inteiro = 1;

        do {
            try {
                viewCli.showMessage("\n---------------------------");
                viewCli.showMessage("--  MENU PRINCIPAL       --");
                viewCli.showMessage("---------------------------");
                viewCli.showMessage("1 - Cadastro de Clientes");
                viewCli.showMessage("2 - Cadastro de Contas");
                viewCli.showMessage("3 - Movimentação");
                viewCli.showMessage("---------------------------");
                viewCli.showMessage("8 - Carregar Dados");
                viewCli.showMessage("9 - Salvar Dados");
                viewCli.showMessage("0 - Sair");

                viewCli.showMessag("Informe uma opção: ");
                inteiro = sc.nextInt();

                switch (inteiro) {
                    case 1:
                        return MenuOptions.CLIENTE;
                    case 2:
                        return MenuOptions.CONTA;
                    case 3:
                        return MenuOptions.MOVIMENTACAO;
                    case 8:
                        return MenuOptions.CARREGAR;
                    case 9:
                        return MenuOptions.SALVAR;
                    case 0:
                        return MenuOptions.SAIR;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Tente Denovo. (" + "Incorrect input: an integer is required)");
                sc.nextLine();
            }
        } while (true);
    }

    public TipoRepository askForTipo() {
        int inteiro = 1;

        do {
            try {
                viewCli.showMessage("\n---------------------------");
                viewCli.showMessage("--        MENU TIPO       --");
                viewCli.showMessage("---------------------------");
                viewCli.showMessage("1 - LISTA");
                viewCli.showMessage("2 - VETOR");
                viewCli.showMessage("3 - BANCO");
                viewCli.showMessage("---------------------------");

                viewCli.showMessag("Informe uma opção: ");
                inteiro = sc.nextInt();

                switch (inteiro) {
                    case 1:
                        return TipoRepository.LISTA;
                    case 2:
                        return TipoRepository.VETOR;
                    case 3:
                        return TipoRepository.BANCO;
                }

            } catch (InputMismatchException ex) {
                System.out.println("Tente Denovo. (" + "Incorrect input: an integer is required)");
                sc.nextLine();
            }
        } while (true);
    }
}
