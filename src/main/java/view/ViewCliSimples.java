package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewCliSimples {
    protected final Scanner entrada;

    public ViewCliSimples() {
        entrada = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return entrada;
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showMessag(String msg) {
        System.out.print(msg);
    }

    public int askForInt(String question) {
        do {
            try {
                showMessage(question);
                int inteiro = entrada.nextInt();
                return inteiro;
            } catch (InputMismatchException ex) {
                System.out.println("Tente Denovo. (" + "Incorrect input: an integer is required)");
                entrada.nextLine();
            }
        } while (true);
    }

    public double askForDouble(String question) {
        do {

            try {
                showMessag(question);
                double d = entrada.nextDouble();
                return d;
            } catch (InputMismatchException ex) {
                System.out.println("Tente Denovo. (" + "Incorrect input: an integer is required)");
                entrada.nextLine();
            }
        } while (true);

    }

    public String askForString(String question) {
        showMessage(question);
        String resposta = entrada.next();
        return resposta;

    }

}
