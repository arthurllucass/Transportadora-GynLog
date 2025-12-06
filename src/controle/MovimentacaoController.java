
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
 // devo conversar com Augusto e Akira para ver se id veiculo e despesa existem!
            if (idVeiculo <= 0) throw new Exception ("ID do veículo inválido!");
            if (idTipoDespesa <= 0) throw new Exception ("ID da despesa inválida!");
            if (descricao ==  null || descricao.trim().equals("")) throw new Exception ("Descrição vazia!");
            if (eDataValida(dataMovimentacao) == false) throw new Exception ("Data inválida!");
            if (valor <= 0) throw new Exception ("Valor inválido!");
            validarPontoVirgula(descricao, "Descrição");
            validarPontoVirgula(dataMovimentacao, "Data");
            int idMovimentacao = movimentacaoDAO.gerarId();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataConvertida = sdf.parse(dataMovimentacao);
            Movimentacao movimentacao = new Movimentacao(idMovimentacao, idVeiculo, idTipoDespesa, descricao, dataConvertida, valor);
            movimentacaoDAO.salvar(movimentacao);
    }
    
    public ArrayList<Movimentacao> listarMovimentacoes() throws Exception {
        return movimentacaoDAO.listaDeMovimentacoes();
    }
    
    public void remover (int idMovimentacao) throws Exception {
        if (movimentacaoDAO.buscarPorId(idMovimentacao) == null) throw new Exception("Movimentação não encontrada!");
        movimentacaoDAO.remover(idMovimentacao);
    }
    
    public boolean eDataValida(String dataMovimentacao ) {
        try {
            if (dataMovimentacao == null || dataMovimentacao.length() != 10) return false;
            dataMovimentacao = dataMovimentacao.trim(); //tirar espaços do começo e final da data
            DateTimeFormatter formatacaoDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataLocal = LocalDate.parse(dataMovimentacao, formatacaoDeData);
            return true;
        } catch (Exception erro) {
            return false;
        }
    }
    
    public void validarPontoVirgula(String texto, String campo) throws Exception {
        if (texto != null && texto.contains(";")) { //metodo para encontar uma determinada string, retorna um boolean
            throw new Exception ("Não pode conter ponto e virgula (;) no campo " + campo);
        }
    }
}
