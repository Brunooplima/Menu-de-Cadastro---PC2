package view;

import java.time.Instant;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.*;
import utils.Validador;
import model.enumeration.*;
import java.util.InputMismatchException;
import model.pessoas.PessoaFisica;

public class GerenteView {
    private Scanner sc;
    private ViewCliSimples viewCli;

    public GerenteView() {
        sc = new Scanner(System.in);
        this.viewCli = new ViewCliSimples();
    }

    public MenuOptions askOptionMenu() {
        int inteiro = 1;

        do {
            try {
                // ARRUMAR
                viewCli.showMessage("\n---------------------------");
                viewCli.showMessage("--    MENU GERENTE      --");
                viewCli.showMessage("---------------------------");
                viewCli.showMessage("1 - Cadastros de Gerentes");
                viewCli.showMessage("2 - Listar Gerente");
                viewCli.showMessage("3 - Atualizar Gerente");
                viewCli.showMessage("4 - Excluir Gerente");
                viewCli.showMessage("5 - Detalhes do Gerente");
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

    public Gerente createGerente() {

        String nome = viewCli.askForString("Digite seu Nome: ");
        String sobrenome = viewCli.askForString("Digote seu Sobrenome: ");
        String cpf = viewCli.askForString("Digite o seu CPF: ");
        String nascimento = viewCli.askForString("Digite sua data de nascimento: ");
        String cep = viewCli.askForString("Digite seu CEP: ");
        String email = viewCli.askForString("Digite sei Email: ");
        double salario = viewCli.askForDouble("Informe o seu salario: ");
        return new Gerente(nome, sobrenome, cpf, nascimento, email, cep, salario);

    }

    public String obterCPF() {
        while (true) {
            String cpf = viewCli.askForString("Digite o seu CPF: ");
            if (Validador.isValidCPF(cpf)) {
                return cpf;
            }
        }
    }

    public void listarGerentes(Pessoa[] pessoas) {

        if (pessoas.length == 0) {
            System.out.println("Não há nenhum cliente!!");
            System.out.println(" ");
        } else {
            for (int i = 0; i < pessoas.length; i++) {
                Pessoa pessoa = pessoas[i];
                viewCli.showMessage(pessoa.toString());
                viewCli.showMessage("------------------------");
            }
        }
    }

    public void detalhesGerentes(Pessoa[] pessoas) {

        if (pessoas.length == 0) {
            System.out.println("Não há nenhum cliente!!");
            System.out.println(" ");
        } else {
            for (int i = 0; i < pessoas.length; i++) {
                Pessoa pessoa = pessoas[i];
                viewCli.showMessage(pessoa.detalhar());

                viewCli.showMessage("Quantidade atual de Clientes: " + pessoas.length);
            }
            viewCli.showMessage("------------------------------");
        }

    }

    public void atualizarGerente(Gerente gerente) {
        String nome = viewCli.askForString("Digite seu Nome: ");
        String email = viewCli.askForString("Informe um Email: ");
        String cep = viewCli.askForString("Informe o CEP: ");

        gerente.setNome(nome);
        gerente.setEmail(email);
        gerente.setCEP(cep);

        if (gerente instanceof PessoaFisica) {
            String sobrenome = viewCli.askForString("Digite o seu Sobrenome: ");
            String nascimento = viewCli.askForString("Informe sua data de Nascimento: ");
            String cpf = obterCPF();

            ((PessoaFisica) gerente).setSobrenome(sobrenome);
            ((PessoaFisica) gerente).setNascimento(nascimento);
            gerente.setDoc(cpf);
        } else {
            String cnpj = viewCli.askForString("Digite o seu CNPJ: ");
            gerente.setDoc(cnpj);
        }
    }

}
