package repository;

import utils.*;
import model.Gerente;

public interface IGerenteRepository {
    
    public Lista<Gerente> getGerentes();

    /**
     * Função que guardar uma nova pessoa no repositório
     * 
     * @return true se deu certo, false se algo deu errado
     */

    public boolean inserir(Gerente gerente);

    public Gerente buscar(String documento);

    public Gerente atualizar(Gerente gerente);

    public Gerente[] obterGerentes();

    public boolean salvar();

    public boolean carregar();

    public boolean apagar(Gerente gerente);
}
