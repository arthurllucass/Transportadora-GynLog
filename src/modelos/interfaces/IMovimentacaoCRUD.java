package modelos.interfaces;

import java.util.ArrayList;
import modelos.classes.Movimentacao;

public interface IMovimentacaoCRUD {

    void salvar(Movimentacao movimentacao) throws Exception;
    ArrayList<Movimentacao> listaDeMovimentacoes() throws Exception;
    void remover(int idMovimentacao) throws Exception;
    Movimentacao buscarPorId(int idmovimentacao) throws Exception;
}
