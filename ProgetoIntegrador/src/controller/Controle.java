/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import modelos.classes.*;
import persistencia.TipoDeVeiculoDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.classes.StatusVeiculo;

/**
 *
 * @author artur
 */
public class Controle {

    private TipoDeVeiculoDAO dao = new TipoDeVeiculoDAO();

    public ArrayList<TipoDeVeiculos> listar() throws Exception {

        return dao.listaDeTiposDeVeiculo();

    }

    private void validar(TipoDeVeiculos veiculo) throws Exception {
        if (veiculo == null) {
            throw new Exception("Veiculo inválido");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            throw new Exception("A placa não pode estar vazio");
        }
        if (!dao.validarPlaca(veiculo.getPlaca())) {
            throw new Exception("Modelo de placa invalido");
        }
        if (veiculo.getMarca() == null || veiculo.getMarca().trim().isEmpty()) {
            throw new Exception("A marca não pode estar vazio");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
            throw new Exception("O modelo não pode estar vazio");
        }
        int ano = veiculo.getAnoDeFabricacao();
        if (ano < 1900 || ano > 2100) {
            throw new Exception("Ano de fabricação invalido");
        }
    }

    private int geradorId() throws Exception {
        int maiorId = 0;
        for (TipoDeVeiculos veiculo : dao.listaDeTiposDeVeiculo()) {
            if (veiculo.getIdVeiculo() > maiorId) {
                maiorId = veiculo.getIdVeiculo();
            }
        }
        return maiorId + 1;
    }

    public void adicionarVeiculo(TipoDeVeiculos veiculo) throws Exception {
        validar(veiculo);
        for (TipoDeVeiculos v : dao.listaDeTiposDeVeiculo()) {
            if (v.getPlaca().equalsIgnoreCase(veiculo.getPlaca())) {
                throw new Exception("Placa ja cadastrada, insere outra placa valida");

            }
        }
        veiculo.setIdVeiculo(geradorId());
        dao.salvar(veiculo);
    }
    
    public void atualizarVeiculo (TipoDeVeiculos veiculo) throws Exception{
        //validar(veiculo);
        dao.atualizar(veiculo);
    }
    public void excluirVeiculo (int idVeiculo) throws Exception{
        dao.remover(idVeiculo);
    }

    public TipoDeVeiculos buscarPorIdVeiculo(int id) throws Exception {
        return dao.buscarPorId(id);
    }

    public ArrayList<TipoDeVeiculos> buscarPorStatus(StatusVeiculo status) throws Exception {
        return dao.buscarPorStatus(status);
    }

    public TipoDeVeiculos buscarPorPlaca(String placa) throws Exception {
        for (TipoDeVeiculos veiculo : dao.listaDeTiposDeVeiculo()) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }
    
    public ArrayList<TipoDeVeiculos> buscarPorMarca(String marca) throws Exception{
        return dao.buscarPorMarca(marca);
    }

}
