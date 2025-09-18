package repository;

import model.Conta;
import utils.*;

public interface IContaRepository {

    public Lista<Conta> getContas();

    /**
     * Função que guardar uma nova pessoa no repositório
     * 
     * @return true se deu certo, false se algo deu errado
     */

    public boolean inserir(Conta conta);

    public Conta buscar(int numero);

    public Conta atualizar(Conta conta);

    public Conta[] obterContas();

    public boolean salvar();

    public boolean carregar();

    public boolean apagar(Conta conta);

}
