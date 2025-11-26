package modelos.interfaces;

import java.util.ArrayList;
import modelos.classes.Movimentacao;

public interface MovimentacaoCRUD {

    void salvar(Movimentacao movimentacao) throws Exception;
    ArrayList<Movimentacao> listaDeMovimentacoes() throws Exception;
    Movimentacao buscarPorId(int idMovimentacao) throws Exception;
    void atualizar(Movimentacao movimentacao) throws Exception;
    void remover(int idMovimentacao) throws Exception;
}
