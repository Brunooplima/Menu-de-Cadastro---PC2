package service;

import model.enumeration.MenuOptions;
import view.MenuView;
import view.*;
import model.enumeration.*;

public class MenuController {
    private MenuView menuView;
    private PessoaController pessoaController;
    private ContaController contaController;
    private MovimentoController movimentoController;
    private TipoRepository tipo;
    private GerenteController gerenteController;

    public MenuController() {
        this.menuView = new MenuView();
        this.tipo = menuView.askForTipo();
        this.pessoaController = new PessoaController(tipo);
        this.contaController = new ContaController(tipo, pessoaController);
        this.movimentoController = new MovimentoController(contaController);
        this.gerenteController = new GerenteController(tipo);
    }

    public void showMessage(String msg) {
        System.out.print(msg);
    }

    public void start() {

        MenuOptions option;
        do {
            option = menuView.askOptionMenuPrincial();
            switch (option) {

                case CLIENTE:

                    pessoaController.start();

                    break;

                case CONTA:

                    contaController.start();

                    break;

                case MOVIMENTACAO:

                    movimentoController.start();

                    break;

                case CARREGAR:
                    pessoaController.persistAll();
                    contaController.persistAll();
                    break;

                case SALVAR:
                    pessoaController.loadAll();
                    contaController.loadAll();
                    break;
                case SAIR:

                    break;

            }
        } while (option != MenuOptions.SAIR);

    }

}
