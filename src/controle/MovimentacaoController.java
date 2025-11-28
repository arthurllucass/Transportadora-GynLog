
package controle;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import modelos.classes.Movimentacao;
import persistencia.MovimentacaoDAO;

public class MovimentacaoController {
    private MovimentacaoDAO movimentacaoDAO = null;

    public MovimentacaoController() {
        movimentacaoDAO = new MovimentacaoDAO();
    }
    
    public void registrarMovimentacao(int idVeiculo, int idTipoDespesa, String descricao, String dataMovimentacao, double valor) throws Exception{
        try {
            if (idVeiculo <= 0) throw new Exception ("ID do veículo inválido!");
            if (idTipoDespesa <= 0) throw new Exception ("ID da despesa inválida!");
            if (descricao ==  null || descricao.trim().equals("")) throw new Exception ("Descrição vazia!");
            if (eDataValida(dataMovimentacao) == false) throw new Exception ("Data inválida!");
            if (valor <= 0) throw new Exception ("Valor inválido!");
            int idMovimentacao = movimentacaoDAO.gerarId();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataConvertida = sdf.parse(dataMovimentacao);
            Movimentacao movimentacao = new Movimentacao(idMovimentacao, idVeiculo, idTipoDespesa, descricao, dataConvertida, valor);
            movimentacaoDAO.salvar(movimentacao);
            
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    public ArrayList<Movimentacao> listarMovimentacoes() throws Exception {
        return movimentacaoDAO.listaDeMovimentacoes();
    }
    
    public boolean eDataValida(String dataMovimentacao ) {
        try {
            DateTimeFormatter formatacaoDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataLocal = LocalDate.parse(dataMovimentacao, formatacaoDeData);
            return true;
        } catch (Exception erro) {
            return false;
        }
    }
}
