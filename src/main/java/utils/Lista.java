
package utils;

import java.io.Serializable;

public class Lista<E> implements Serializable {

    private E[] elementos;
    private int quantidade;

    public Lista() {

        elementos = (E[]) new Object[0];
        this.quantidade = 0;
    }

    public E obterElemento(int indice) {
        if (indice >= 0 && (quantidade - 1) >= indice) {
            return elementos[indice];
        }
        return null;
    }

    public int obterIndice(E elemento) {
        for (int i = 0; i < quantidade; i++) {
            System.out.println(elementos[i]);
            System.out.println(elemento);
            if (elementos[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public void adicionar(int i, E elemento) {
        if (quantidade > i) {
            elementos[i] = elemento;
        }
    }

    public void adicionar(E elemento) {
        if (elementos.length < (quantidade + 1)) {
            E[] auxiliar = (E[]) new Object[elementos.length + 1];
            for (int i = 0; i < elementos.length; i++) {
                auxiliar[i] = elementos[i];

            }
            elementos = auxiliar;
        }
        elementos[quantidade] = elemento;
        quantidade++;
    }

    public void adicionar(E[] elementos) {
        for (int i = 0; i < elementos.length; i++) {
            E elemento = elementos[i];
            adicionar(elemento);
        }

    }

    public E[] obterArray(E[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = obterElemento(i);
        }
        return vetor;

    }

    public boolean remover(E elemento) {

        if (quantidade < 0) {
            return false;
        }

        for (int i = 0; i < quantidade; i++) {
            if (elementos[i].equals(elemento)) {
                if (i == (quantidade - 1)) {
                    elementos[i] = null;
                } else {
                    for (int j = i; j < quantidade - 1; j++) {
                        elementos[j] = elementos[j + 1];

                    }
                }
                quantidade--;

                return true;
            }

        }
        return false;

    }

    public void listar() {
        for (int i = 0; i < quantidade; i++) {
            System.out.println(elementos[i]);
        }
    }

    public int getQuantidade() {
        return quantidade;
    }
}
