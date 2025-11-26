
package persistencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
<<<<<<< HEAD
import java.util.ArrayList;
import modelos.classes.Movimentacao;
import modelos.interfaces.IMovimentacaoCRUD;

public class MovimentacaoDAO implements IMovimentacaoCRUD {
=======
import modelos.classes.Movimentacao;
import modelos.interfaces.MovimentacaoCRUD;

public class MovimentacaoDAO implements MovimentacaoCRUD {
>>>>>>> branch_arthur_lucas
  private String nomeDoArquivoNoDisco = null;

  public MovimentacaoDAO(){
    nomeDoArquivoNoDisco = "./src/bancodedados/Movimentacoes.txt";
  }
  @Override
  public void salvar(Movimentacao movimentacao) throws Exception {
    try{
      BufferedWriter bw =new BufferedWriter(new FileWriter(nomeDoArquivoNoDisco,true));
      //Escreve no arquivo
      String str = movimentacao.getIdMovimentacao() + ";";
      str += movimentacao.getIdVeiculo() + ";";
<<<<<<< HEAD
      str += movimentacao.getDescricao() + ";";
      str += movimentacao.getDescricao() + "\n";
=======
      str += movimentacao.getDescrição() + 
      str += tipoDeDespesa.getDescricao() + "\n";
>>>>>>> branch_arthur_lucas
      bw.write(str);
      //fecha o arquivo
      bw.close();		
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Salvar - "+erro.getMessage();
      throw new Exception(msg);
    }
  }
<<<<<<< HEAD

    @Override
    public ArrayList<Movimentacao> listaDeMovimentacoes() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Movimentacao buscarPorId(int idMovimentacao) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Movimentacao movimentacao) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remover(int idMovimentacao) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
=======
>>>>>>> branch_arthur_lucas
}
