/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import modelos.classes.TipoDeVeiculos;
import modelos.interfaces.ITipoDeVeiculoCRUD;

// Bibliotecas para IO em arquivo texto
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import modelos.classes.StatusVeiculo;
import persistencia.*;

/**
 *
 * @author artur
 */
public class TipoDeVeiculoDAO implements ITipoDeVeiculoCRUD {
    //Atributos

    private String nomeDoArquivoNoDisco = null;

    //Metodo Construtor
    public TipoDeVeiculoDAO() {
        String home = System.getProperty("user.home");
        String base = new File("").getAbsolutePath();
        BancoDeDados bd = new BancoDeDados();
        
        nomeDoArquivoNoDisco = bd.getCaminhoArquivo("TipoDeVeiculos.txt");
    }

    public boolean validarPlaca(String placa) {
        placa = placa.toUpperCase(); 

       
        if (placa.length() == 8 && placa.charAt(3) == '-') {

            // Letras nas 3 primeiras posições
            if (!Character.isLetter(placa.charAt(0))) {
                return false;
            }
            if (!Character.isLetter(placa.charAt(1))) {
                return false;
            }
            if (!Character.isLetter(placa.charAt(2))) {
                return false;
            }

            // Dígitos depois do hífen
            if (!Character.isDigit(placa.charAt(4))) {
                return false;
            }
            if (!Character.isDigit(placa.charAt(5))) {
                return false;
            }
            if (!Character.isDigit(placa.charAt(6))) {
                return false;
            }
            if (!Character.isDigit(placa.charAt(7))) {
                return false;
            }

            return true; // passou em tudo
        }

     
        if (placa.length() == 7) {

            // 3 letras no começo
            if (!Character.isLetter(placa.charAt(0))) {
                return false;
            }
            if (!Character.isLetter(placa.charAt(1))) {
                return false;
            }
            if (!Character.isLetter(placa.charAt(2))) {
                return false;
            }

            // 1 número
            if (!Character.isDigit(placa.charAt(3))) {
                return false;
            }

            // 1 letra
            if (!Character.isLetter(placa.charAt(4))) {
                return false;
            }

            // 2 números finais
            if (!Character.isDigit(placa.charAt(5))) {
                return false;
            }
            if (!Character.isDigit(placa.charAt(6))) {
                return false;
            }

            return true;
        }

        return false; 
    }

    public ArrayList<TipoDeVeiculos> buscarPorStatus(StatusVeiculo statusProcurado) throws Exception {
        try {
            ArrayList<TipoDeVeiculos> lista = new ArrayList<>();

            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            BufferedReader br = new BufferedReader(fr);

            String linha = "";

            while ((linha = br.readLine()) != null) {
                String vetorSTR[] = linha.split(";");

                StatusVeiculo statusLido = StatusVeiculo.valueOf(vetorSTR[5]);

                if (statusLido == statusProcurado) {
                    int id = Integer.parseInt(vetorSTR[0]);
                    String placa = vetorSTR[1];
                    String marca = vetorSTR[2];
                    String modelo = vetorSTR[3];
                    int anoDeFabricacao = Integer.parseInt(vetorSTR[4]);

                    TipoDeVeiculos obj = new TipoDeVeiculos(
                            id, placa, marca, modelo, anoDeFabricacao, statusLido
                    );

                    lista.add(obj);
                }
            }

            br.close();
            return lista;

        } catch (Exception erro) {
            String msg = "Persistencia - Metodo buscarPorStatus - " + erro.getMessage();
            throw new Exception(msg);
        }
    }

    public ArrayList<TipoDeVeiculos> buscarPorMarca(String marcaProcurada) throws Exception {
        try {
            ArrayList<TipoDeVeiculos> lista = new ArrayList<>();

            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            BufferedReader br = new BufferedReader(fr);

            String linha;

            while ((linha = br.readLine()) != null) {
                String vetorSTR[] = linha.split(";");

                String marca = vetorSTR[2];

                if (marca.equalsIgnoreCase(marcaProcurada)) {
                    int id = Integer.parseInt(vetorSTR[0]);
                    String placa = vetorSTR[1];
                    String modelo = vetorSTR[3];
                    int anoDeFabricacao = Integer.parseInt(vetorSTR[4]);
                    StatusVeiculo status = StatusVeiculo.valueOf(vetorSTR[5]);

                    TipoDeVeiculos obj = new TipoDeVeiculos(
                            id, placa, marca, modelo, anoDeFabricacao, status
                    );

                    lista.add(obj);
                }
            }

            br.close();
            return lista;

        } catch (Exception erro) {
            String msg = "Persistencia - Metodo buscarPorMarca - " + erro.getMessage();
            throw new Exception(msg);
        }
    }

    @Override
    public void salvar(TipoDeVeiculos tipoDeVeiculo) throws Exception {
        try {
            ArrayList<TipoDeVeiculos> lista = this.listaDeTiposDeVeiculo();
            for (TipoDeVeiculos veiculo : lista) {
                if (veiculo.getIdVeiculo() == tipoDeVeiculo.getIdVeiculo()) {
                    throw new Exception("ID JA EXISTENTE!");
                }
            }
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivoNoDisco, true);
            //Criar o buffer do arquivo
            BufferedWriter bw = new BufferedWriter(fw);
            //Escreve no arquivo
            String str = tipoDeVeiculo.getIdVeiculo() + ";";
            str += tipoDeVeiculo.getPlaca() + ";"
                    + tipoDeVeiculo.getMarca() + ";"
                    + tipoDeVeiculo.getModelo() + ";"
                    + tipoDeVeiculo.getAnoDeFabricacao() + ";"
                    + tipoDeVeiculo.getStatus() + "\n";
            bw.write(str);
            //fecha o arquivo
            bw.close();
        } catch (Exception erro) {
            String msg = "Persistencia - Metodo Salvar - " + erro.getMessage();
            throw new Exception(msg);
        }

    }

    @Override
    public ArrayList<TipoDeVeiculos> listaDeTiposDeVeiculo() throws Exception {
        try {
            ArrayList<TipoDeVeiculos> listaDeTiposDeDespesas = new ArrayList<>();
            //abrir um arquivo existente
            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            //Criar o buffer do arquivo
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                String vetorStr[] = linha.split(";");
                TipoDeVeiculos objTipoDeDespesa = null;
                int idTipoDeVeiculosAux = Integer.parseInt(vetorStr[0]);
                String placa = vetorStr[1];
                String marca = vetorStr[2];
                String modelo = vetorStr[3];
                int anoDeFabricacao = Integer.parseInt(vetorStr[4]);
                StatusVeiculo statusVeiculo = StatusVeiculo.valueOf(vetorStr[5]);

                objTipoDeDespesa = new TipoDeVeiculos(idTipoDeVeiculosAux, placa, marca, modelo, anoDeFabricacao, statusVeiculo);
                listaDeTiposDeDespesas.add(objTipoDeDespesa);
            }
            br.close();
            return listaDeTiposDeDespesas;
        } catch (Exception erro) {
            String msg = "Persistencia - Metodo Lista - " + erro.getMessage();
            throw new Exception(msg);
        }
    }

    @Override
    public TipoDeVeiculos buscarPorId(int idTipoDeVeiculo) throws Exception {
        try {
            FileReader fw = new FileReader(nomeDoArquivoNoDisco);
            BufferedReader bw = new BufferedReader(fw);
            String linha = "";
            while ((linha = bw.readLine()) != null) {
                String vetorStr[] = linha.split(";");
                int idAux = Integer.parseInt(vetorStr[0]);
                if (idAux == idTipoDeVeiculo) {
                    String placa = vetorStr[1];
                    String marca = vetorStr[2];
                    String modelo = vetorStr[3];
                    int anoDeFabricacao = Integer.parseInt(vetorStr[4]);
                    StatusVeiculo statusVeiculo = StatusVeiculo.valueOf(vetorStr[5]);
                    TipoDeVeiculos objTipoDeVeiculo = null;
                    objTipoDeVeiculo = new TipoDeVeiculos(idAux, placa, marca, modelo, anoDeFabricacao, statusVeiculo);
                    bw.close();
                    return objTipoDeVeiculo;
                }

            }
            bw.close();
            return null;

        } catch (Exception erro) {
            String msg = "Persistencia - Metodo Buscar - " + erro.getMessage();
            throw new Exception(msg);
        }
    }

    @Override
   public void atualizar(TipoDeVeiculos tipoDeVeiculo) throws Exception {
    try {
        ArrayList<TipoDeVeiculos> listagem = this.listaDeTiposDeVeiculo();

        FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
        BufferedWriter bw = new BufferedWriter(fw);

        for (TipoDeVeiculos obj : listagem) {

            TipoDeVeiculos registro;

           
            if (obj.getIdVeiculo() == tipoDeVeiculo.getIdVeiculo()) {
                registro = tipoDeVeiculo;
            } 
           
            else {
                registro = obj;
            }

            String str = registro.getIdVeiculo() + ";"
                    + registro.getPlaca() + ";"
                    + registro.getMarca() + ";"
                    + registro.getModelo() + ";"
                    + registro.getAnoDeFabricacao() + ";"
                    + registro.getStatus() + "\n";

            bw.write(str);
        }

        bw.close();

    } catch (Exception erro) {
        throw new Exception("Persistencia - Metodo Atualizar - " + erro.getMessage());
    }
}

    @Override
    public void remover(int idTipoDeVeiculo) throws Exception {
        try {
            ArrayList<TipoDeVeiculos> listagem = null;
            listagem = this.listaDeTiposDeVeiculo();
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
            //Criar o buffer do arquivo
            BufferedWriter bw = new BufferedWriter(fw);
            for (TipoDeVeiculos obj : listagem) {
                if (obj.getIdVeiculo() != idTipoDeVeiculo) {
                    String str = obj.getIdVeiculo() + ";"
                            + obj.getPlaca() + ";"
                            + obj.getMarca() + ";"
                            + obj.getModelo() + ";"
                            + obj.getAnoDeFabricacao() + ";"
                            + obj.getStatus() + "\n";
                    bw.write(str);

                }
            }
            bw.close();
        } catch (Exception erro) {
            String msg = "Persistencia - Metodo Remover - " + erro.getMessage();
            throw new Exception(msg);
        }
    }

}
