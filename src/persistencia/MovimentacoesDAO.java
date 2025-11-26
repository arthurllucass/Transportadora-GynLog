
package persistencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import modelos.classes.Movimentacoes;
import modelos.interfaces.MovimentacoesCRUD;

public class MovimentacoesDAO implements MovimentacoesCRUD {
  private String nomeDoArquivoNoDisco = null;

  public MovimentacoesDAO(){
    nomeDoArquivoNoDisco = "./src/bancodedados/Movimentacoes.txt";
  }
  @Override
  public void salvar(Movimentacoes movimentacao) throws Exception {
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
