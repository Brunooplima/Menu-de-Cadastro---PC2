package repository;

import utils.*;
import model.Pessoa;

public interface IPessoaRepository {
    public Lista<Pessoa> getPessoas();

    /**
     * Função que guardar uma nova pessoa no repositório
     * 
     * @return true se deu certo, false se algo deu errado
     */

    public boolean inserir(Pessoa pessoa);

    public Pessoa buscar(String documento);

    public Pessoa atualizar(Pessoa pessoa);

    public Pessoa[] obterPessoas();

    public boolean salvar();

    public boolean carregar();

    public boolean apagar(Pessoa pessoa);
}
