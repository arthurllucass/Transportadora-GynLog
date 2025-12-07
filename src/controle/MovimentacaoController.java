
package controle;

import modelos.classes.TipoDeVeiculos;
import modelos.classes.TipoDeDespesa;
import modelos.classes.Movimentacao;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import modelos.classes.*;
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
    
    public ArrayList<Movimentacao> buscarListaDespesasDoMes(int mes, int ano) throws Exception {
        int anoAtual = LocalDate.now().getYear();

        if (mes < 1 || mes > 12) throw new Exception("Mês inválido!");
        if (ano > anoAtual) throw new Exception("Ano inválido!");

        ArrayList<Movimentacao> lista = movimentacaoDAO.buscarDespesasDoMes(mes, ano);
        if (lista == null || lista.isEmpty()) throw new Exception("Nenhuma despesa encontrada para " + mes + "/" + ano + "!");

        return lista;
    }
    
    public ArrayList<Movimentacao> filtrarPorPeriodo(ArrayList<Movimentacao> lista, LocalDate dataInicial, LocalDate dataFinal) throws Exception {

        ArrayList<Movimentacao> listaAux = movimentacaoDAO.filtrarPorPeriodo(lista, dataInicial, dataFinal);
        if (listaAux == null || listaAux.isEmpty()) throw new Exception("Nenhuma despesa encontrada para " + dataInicial.toString() + "/" + dataFinal.toString() + "!");

        return listaAux;
    }
    
    public ArrayList<Movimentacao> filtrarPorVeiculo(ArrayList<Movimentacao> lista, TipoDeVeiculos veiculo) throws Exception {

        ArrayList<Movimentacao> listaAux = movimentacaoDAO.filtrarPorVeiculo(lista, veiculo);
        if (listaAux == null || listaAux.isEmpty()) throw new Exception("Nenhuma despesa encontrada para " + veiculo.getPlaca() + "!");

        return listaAux;
    }
    
    public ArrayList<Movimentacao> filtrarPorDespesa(ArrayList<Movimentacao> lista, TipoDeDespesa despesa) throws Exception {

        ArrayList<Movimentacao> listaAux = movimentacaoDAO.filtrarPorDespesa(lista, despesa);
        if (listaAux == null || listaAux.isEmpty()) throw new Exception("Nenhuma despesa encontrada para " + despesa.getDescricao() + "!");

        return listaAux;
    }
    
    public double buscarTotalDespesasDoMes(int mes, int ano) throws Exception {
        ArrayList<Movimentacao> lista = buscarListaDespesasDoMes(mes, ano);

        double somaTotal = 0;
        for (Movimentacao movimentacao : lista) {
            somaTotal += movimentacao.getValor();
        }

        return somaTotal;
    }

}

