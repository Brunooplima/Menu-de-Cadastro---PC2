package model;

/**
 *
 * @author alessio
 */
public class Extrato {
    
    private String menssagem;
    private Double valor;

    public Extrato(String menssagem, Double valor) {
        this.menssagem = menssagem;
        this.valor = valor;
    }

    
    
    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
    
}
