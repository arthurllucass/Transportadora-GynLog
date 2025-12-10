/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelos.interfaces;
import java.util.ArrayList;
import modelos.classes.TipoDeDespesa;

public interface ITipoDeDespesasCRUD {
  void salvar(TipoDeDespesa tipoDeDespesa) throws Exception;
  ArrayList<TipoDeDespesa> listaDeTiposDeDespesas() throws Exception;
  TipoDeDespesa buscarPorId(int idTipoDeDespesa) throws Exception;
  TipoDeDespesa buscarPorDescricao(String descricao) throws Exception;
  void atualizar(TipoDeDespesa tipoDeDespesa) throws Exception;
  void remover(int idTipoDeDespesa) throws Exception;
}
