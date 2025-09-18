package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.*;
import model.pessoas.PessoaFisica;
import model.pessoas.PessoaJuridica;
import utils.Validador;
import model.enumeration.*;
import java.util.InputMismatchException;

public class PessoaView {
    private Scanner sc;
    private ViewCliSimples viewCli;

    public PessoaView() {
        sc = new Scanner(System.in);
        this.viewCli = new ViewCliSimples();

    }

    public MenuOptions askOptionMenu() {
        int inteiro = 1;

        do {
            try {
                // ARRUMAR
                viewCli.showMessage("\n---------------------------");
                viewCli.showMessage("--    MENU CLIENTE       --");
                viewCli.showMessage("---------------------------");
                viewCli.showMessage("1 - Cadastros de Clientes");
                viewCli.showMessage("2 - Listar Cliente");
                viewCli.showMessage("3 - Atualizar Cliente");
                viewCli.showMessage("4 - Excluir Cliente");
                viewCli.showMessage("5 - Detalhes do Cliente");
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
                viewCli.showMessage("Tente Denovo! (" + "Incorrect input: an integer is required)");
                sc.nextLine();
            }
        } while (true);
    }

    public String askForCPF() {
        while (true) {
            String cpf = viewCli.askForString("Digite seu CPF:");
            if (Validador.isValidCPF(cpf))
                return cpf;
        }

    }

    public String askForCnpj() {
        while (true) {
            String cnpj = viewCli.askForString("Digite seu CNPJ");
            if (Validador.isValidCNPJ(cnpj))
                return cnpj;
        }
    }

    public TipoPessoa askForTipoPessoa() {

        do {
            viewCli.showMessage("---------------------");
            viewCli.showMessage("1 - Pessoa Física");
            viewCli.showMessage("2 - Pessoa Jurídica");
            viewCli.showMessage("---------------------");

            int option = viewCli.askForInt("Digite o tipo da pessoa: ");

            switch (option) {
                case 1:
                    return TipoPessoa.FISICA;

                case 2:
                    return TipoPessoa.JURIDICA;
            }
        } while (true);
    }

    public Pessoa createCliente() {
        TipoPessoa tipo = askForTipoPessoa();
        if (tipo == TipoPessoa.FISICA) {
            String nome = viewCli.askForString("Digite seu Nome: ");
            String sobrenome = viewCli.askForString("Digite o seu Sobrenome: ");
            String nascimento = viewCli.askForString("Informe sua data de Nascimento: ");
            String cpf = viewCli.askForString("Digite o seu CPF: ");
            String cep = viewCli.askForString("Informe o CEP: ");
            String email = viewCli.askForString("Informe um Email: ");
            return new PessoaFisica(nome, sobrenome, cpf, nascimento, email, cep);

        } else {
            String nome = viewCli.askForString("Digite seu Nome: ");
            String cnpj = viewCli.askForString("Digite o seu CNPJ: ");
            String email = viewCli.askForString("Informe um Email: ");
            String cep = viewCli.askForString("Informe o CEP: ");
            return new PessoaJuridica(nome, email, cep, cnpj);

        }

    }

    public void listarClientes(Pessoa[] pessoas) {

        if (pessoas.length == 0) {
            viewCli.showMessage("Não há nenhum cliente!!");
            viewCli.showMessage(" ");
        } else {
            for (int i = 0; i < pessoas.length; i++) {
                Pessoa pessoa = pessoas[i];
                viewCli.showMessage(pessoa.toString()); //*dando erro
                viewCli.showMessage("------------------------");
            }
        }
    }

    public void detalhesClientes(Pessoa[] pessoas) {

        if (pessoas.length == 0) {
            viewCli.showMessage("Não há nenhum cliente!!");
            viewCli.showMessage(" ");
        } else {
            for (int i = 0; i < pessoas.length; i++) {
                Pessoa pessoa = pessoas[i];
                viewCli.showMessage(pessoa.detalhar()); //*dando erros

                viewCli.showMessage("Quantidade atual de Clientes: " + pessoas.length);
            }
            viewCli.showMessage("------------------------------");
        }

    }

    public String obterCPF() {
        while (true) {
            String cpf = viewCli.askForString("Digite o seu CPF: ");
            if (Validador.isValidCPF(cpf)) {
                return cpf;
            }
        }
    }

    public void atualizarPessoa(Pessoa pessoa) {
        String nome = viewCli.askForString("Digite seu Nome: ");
        String email = viewCli.askForString("Informe um Email: ");
        String cep = viewCli.askForString("Informe o CEP: ");

        pessoa.setNome(nome);
        pessoa.setEmail(email);
        pessoa.setCEP(cep);

        if (pessoa instanceof PessoaFisica) {
            String sobrenome = viewCli.askForString("Digite o seu Sobrenome: ");
            String nascimento = viewCli.askForString("Informe sua data de Nascimento: ");
            String cpf = obterCPF();

            ((PessoaFisica) pessoa).setSobrenome(sobrenome);
            ((PessoaFisica) pessoa).setNascimento(nascimento);
            pessoa.setDoc(cpf);
        } else {
            String cnpj = viewCli.askForString("Digite o seu CNPJ: ");
            pessoa.setDoc(cnpj);
        }
    }

}
