
package persistencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import modelos.classes.Movimentacao;
import modelos.interfaces.MovimentacaoCRUD;

public class MovimentacaoDAO implements MovimentacaoCRUD {
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
      str += movimentacao.getDescrição() + 
      str += tipoDeDespesa.getDescricao() + "\n";
      bw.write(str);
      //fecha o arquivo
      bw.close();		
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Salvar - "+erro.getMessage();
      throw new Exception(msg);
    }
  }
}
