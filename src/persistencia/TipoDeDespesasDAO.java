/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.util.ArrayList;
import modelos.classes.TipoDeDespesa;
// Bibliotecas para IO em arquivo texto
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import modelos.interfaces.TipoDeDespesasCRUD;

/**
 *
 * @author ejmcc
 */
public class TipoDeDespesasDAO implements TipoDeDespesasCRUD{
  //Atributos
  private String nomeDoArquivoNoDisco = null;
  //Metodo Construtor
  public TipoDeDespesasDAO(){
    nomeDoArquivoNoDisco = "./src/bancodedados/TiposDeDespesas.txt";
  }
  @Override
  public void salvar(TipoDeDespesa tipoDeDespesa) throws Exception {
    try{
      //cria o arquivo
      FileWriter fw = new FileWriter(nomeDoArquivoNoDisco,true);
      //Criar o buffer do arquivo
      BufferedWriter bw =new BufferedWriter(fw);
      //Escreve no arquivo
      String str = tipoDeDespesa.getIdTipoDeDespesa() + ";";
      str += tipoDeDespesa.getDescricao() + "\n";
      bw.write(str);
      //fecha o arquivo
      bw.close();		
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Salvar - "+erro.getMessage();
      throw new Exception(msg);
    }
  }

  @Override
  public ArrayList<TipoDeDespesa> listaDeTiposDeDespesas() throws Exception {
    try{
      ArrayList<TipoDeDespesa> listaDeTiposDeDespesas = new ArrayList<>();
      //abrir um arquivo existente
      FileReader fr = new FileReader(nomeDoArquivoNoDisco);
      //Criar o buffer do arquivo
      BufferedReader br  = new BufferedReader(fr);
      String linha = "";
      while((linha=br.readLine())!=null){
        String vetorStr[] = linha.split(";");
        TipoDeDespesa objTipoDeDespesa = null;
        int idTipoDeDespesaAux = Integer.parseInt(vetorStr[0]);
        String descricao = vetorStr[1];
        objTipoDeDespesa = new TipoDeDespesa(idTipoDeDespesaAux,descricao);
        listaDeTiposDeDespesas.add(objTipoDeDespesa);
      }
      br.close();
      return listaDeTiposDeDespesas;
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Lista - "+erro.getMessage();
      throw new Exception(msg);
    }  
  }

  @Override
  public TipoDeDespesa buscarPorId(int idTipoDeDespesa) throws Exception {
    try{
      //abrir um arquivo existente
      FileReader fr = new FileReader(nomeDoArquivoNoDisco);
      //Criar o buffer do arquivo
      BufferedReader br  = new BufferedReader(fr);
      String linha = "";
      while((linha=br.readLine())!=null){
        String vetorStr[] = linha.split(";");
        int idTipoDeDespesaAux = Integer.parseInt(vetorStr[0]);
        if(idTipoDeDespesaAux == idTipoDeDespesa){
          String descricao = vetorStr[1];
          TipoDeDespesa objTipoDeDespesa = null;
          objTipoDeDespesa = new TipoDeDespesa(idTipoDeDespesaAux,descricao);
          br.close();
          return objTipoDeDespesa;
        }
      }
      br.close();
      return null;
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Buscar - "+erro.getMessage();
      throw new Exception(msg);
    }   
  }
  @Override
  public TipoDeDespesa buscarPorDescricao(String descricao) throws Exception {
    try{
      //abrir um arquivo existente
      FileReader fr = new FileReader(nomeDoArquivoNoDisco);
      //Criar o buffer do arquivo
      BufferedReader br  = new BufferedReader(fr);
      String linha = "";
      while((linha=br.readLine())!=null){
        String vetorStr[] = linha.split(";");
        String descricaoAux = vetorStr[1];
        if(descricaoAux.toUpperCase().equals(descricao.toUpperCase())){
          int idTipoDespesa = Integer.parseInt(vetorStr[0]);
          TipoDeDespesa objTipoDeDespesa = null;
          objTipoDeDespesa = new TipoDeDespesa(idTipoDespesa,descricao);
          br.close();
          return objTipoDeDespesa;
        }
      }
      br.close();
      return null;
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Buscar - "+erro.getMessage();
      throw new Exception(msg);
    }   
  }

  @Override
  public void atualizar(TipoDeDespesa tipoDeDespesa) throws Exception {
  try {
      ArrayList<TipoDeDespesa> listagem = null;
      listagem = this.listaDeTiposDeDespesas();
      //cria o arquivo
      FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
      //Criar o buffer do arquivo
      BufferedWriter bw =new BufferedWriter(fw);
      for(TipoDeDespesa obj : listagem){
        if(obj.getIdTipoDeDespesa() == tipoDeDespesa.getIdTipoDeDespesa()){
          String str = tipoDeDespesa.getIdTipoDeDespesa() + ";";
          str += tipoDeDespesa.getDescricao() + "\n";
          bw.write(str);
        }else{
          String str = obj.getIdTipoDeDespesa() + ";";
          str += obj.getDescricao() + "\n";
          bw.write(str);
        }
      }
      bw.close();
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Atualizar - "+erro.getMessage();
      throw new Exception(msg);
    } 
  }

  @Override
  public void remover(int idTipoDeDespesa) throws Exception {
    try {
      ArrayList<TipoDeDespesa> listagem = null;
      listagem = this.listaDeTiposDeDespesas();
      //cria o arquivo
      FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
      //Criar o buffer do arquivo
      BufferedWriter bw =new BufferedWriter(fw);
      for(TipoDeDespesa obj : listagem){
        if(obj.getIdTipoDeDespesa() != idTipoDeDespesa){
          String str = obj.getIdTipoDeDespesa() + ";";
          str += obj.getDescricao() + "\n";
          bw.write(str);
        }
      }
      bw.close();
    }catch(Exception erro){
      String msg = "Persistencia - Metodo Remover - "+erro.getMessage();
      throw new Exception(msg);
    } 
  }
  
}
