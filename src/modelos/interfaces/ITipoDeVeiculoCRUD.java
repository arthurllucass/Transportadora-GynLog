/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelos.interfaces;
import java.util.ArrayList;
import modelos.classes.TipoDeVeiculos;

/**
 *
 * @author artur
 */
public interface ITipoDeVeiculoCRUD {
  void salvar(TipoDeVeiculos tipoDeVeiculo) throws Exception;
  ArrayList<TipoDeVeiculos> listaDeTiposDeVeiculo() throws Exception;
  TipoDeVeiculos buscarPorId(int idTipoDeDespesa) throws Exception;
  void atualizar(TipoDeVeiculos tipoDeVeiculo) throws Exception;
  void remover(int idTipoDeVeiculo) throws Exception;
    
}
