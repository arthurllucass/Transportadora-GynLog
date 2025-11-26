package modelos.interfaces;

import java.util.ArrayList;
import modelos.classes.Movimentacoes;

public interface MovimentacoesCRUD {

    void salvar(Movimentacoes movimentacao) throws Exception;
    ArrayList<Movimentacoes> listaDeMovimentacoes() throws Exception;
    Movimentacoes buscarPorId(int idMovimentacao) throws Exception;
    void atualizar(Movimentacoes movimentacao) throws Exception;
    void remover(int idMovimentacao) throws Exception;
}
