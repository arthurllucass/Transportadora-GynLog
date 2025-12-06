/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import modelos.classes.TipoDeDespesa;
import persistencia.TipoDeDespesasDAO;
/**
 *
 * @author Augusto
 */
public class TipoDespesaController {
    
    private TipoDeDespesasDAO objDeDespesasDAO = null;

    public TipoDespesaController() {
        objDeDespesasDAO = new TipoDeDespesasDAO();
    }
    
    public void removerTipodeDespesa(TipoDeDespesa obj) throws Exception{ 
        
        if(objDeDespesasDAO.buscarPorId(obj.getIdTipoDeDespesa()) == null) throw new Exception("Tipo de Despesa não existe para remoção!!");   
        
        objDeDespesasDAO.remover(obj.getIdTipoDeDespesa());        
    }
    
    public void editarTipoDespesa(TipoDeDespesa obj) throws Exception{
            
        if(objDeDespesasDAO.buscarPorId(obj.getIdTipoDeDespesa()) == null) throw new Exception("Tipo de Despesa não existe para edição!!");
        if(objDeDespesasDAO.buscarPorDescricao(obj.getDescricao()) != null) throw new Exception("Essa Descricao Ja existe!!.");   
        
        objDeDespesasDAO.atualizar(obj);
    }
    
    public void adicionarTipoDespesa(TipoDeDespesa obj) throws Exception{
        
        if(objDeDespesasDAO.buscarPorId(obj.getIdTipoDeDespesa()) != null) throw new Exception("Esse ID ja existe.");
        if(objDeDespesasDAO.buscarPorDescricao(obj.getDescricao())!= null) throw new Exception("Essa Descricao ja existe.");
        
        objDeDespesasDAO.salvar(obj);
        
    }
    
}
