package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modelos.classes.Movimentacao;
import modelos.interfaces.IMovimentacaoCRUD;

public class MovimentacaoDAO implements IMovimentacaoCRUD {

    private String nomeDoArquivoNoDisco = null;

    public MovimentacaoDAO() {
        nomeDoArquivoNoDisco = "./src/bancodedados/Movimentacoes.txt";
    }

    @Override
    public void salvar(Movimentacao movimentacao) throws Exception {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeDoArquivoNoDisco, true));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String str = movimentacao.getIdMovimentacao() + ";";
            str += movimentacao.getIdVeiculo() + ";";
            str += movimentacao.getIdTipoDespesa() + ";";
            str += movimentacao.getDescricao() + ";";
            str += sdf.format(movimentacao.getDataMovimentacao()) + ";";
            str += movimentacao.getValor() + "\n";
            bw.write(str);
            bw.close();
        } catch (Exception erro) {
            throw new Exception("Erro ao salvar a movimentação: " + erro.getMessage());
        }
    }

    @Override
    public ArrayList<Movimentacao> listaDeMovimentacoes() throws Exception {
        try {
            ArrayList<Movimentacao> listaDeMovimentacoes = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivoNoDisco));
            String linhaDoArquivo = br.readLine();

            while (linhaDoArquivo != null) {
                String campos[] = linhaDoArquivo.split(";");
                int idMovimentacao = Integer.parseInt(campos[0]);
                int idVeiculo = Integer.parseInt(campos[1]);
                int idTipoDespesa = Integer.parseInt(campos[2]);
                String descricao = campos[3];
                Date data = sdf.parse(campos[4]);
                double valor = Double.parseDouble(campos[5]);

                Movimentacao movimentacoesDoArquivo = new Movimentacao(idMovimentacao, idVeiculo, idTipoDespesa,
                        descricao, data, valor);
                listaDeMovimentacoes.add(movimentacoesDoArquivo);
                linhaDoArquivo = br.readLine();
            }
            br.close();
            return listaDeMovimentacoes;
        } catch (Exception erro) {
            throw new Exception("Erro ao ler as movimentações: " + erro.getMessage());
        }
    }
    
    @Override
    public void remover(int idMovimentacao) throws Exception {
        try {
            ArrayList<Movimentacao> listagem = null;
            listagem = this.listaDeMovimentacoes();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeDoArquivoNoDisco));

            for (Movimentacao movimentacao : listagem) {
                if (movimentacao.getIdMovimentacao() != idMovimentacao) {
                    String str = movimentacao.getIdMovimentacao() + ";";
                    str += movimentacao.getIdVeiculo() + ";";
                    str += movimentacao.getIdTipoDespesa() + ";";
                    str += movimentacao.getDescricao() + ";";
                    str += sdf.format(movimentacao.getDataMovimentacao()) + ";";
                    str += movimentacao.getValor() + "\n";
                    bw.write(str);
                }
            }
            bw.close();
        } catch (Exception erro) {
            throw new Exception("Erro ao remover a movimentação: " + erro.getMessage());
        }
    }
    
    @Override
    public Movimentacao buscarPorId(int idMovimentacao) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivoNoDisco));
            String linha = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            while ((linha = br.readLine()) != null) {
                String campos[] = linha.split(";");
                int idMovimentacaoAux = Integer.parseInt(campos[0]);
                
                if (idMovimentacaoAux == idMovimentacao) {
                int idVeiculo = Integer.parseInt(campos[1]);
                int idTipoDespesa = Integer.parseInt(campos[2]);
                String descricao = campos[3];
                Date data = sdf.parse(campos[4]);
                double valor = Double.parseDouble(campos[5]);
                
                    Movimentacao objMovimentacao = null;
                    objMovimentacao = new Movimentacao(idMovimentacaoAux, idVeiculo, idTipoDespesa,
                        descricao, data, valor);
                    br.close();
                    return objMovimentacao;
                }
            }
            br.close();
            return null;
        } catch (Exception erro) {
            throw new Exception("Erro ao buscar o ID da Movimentação: " + erro.getMessage());
        }
    }

    public int gerarId() throws Exception {
        try {
            int linhas = 0;
            BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivoNoDisco));
            while (br.readLine() != null) {
                linhas++;
            }
            br.close();
            return linhas + 1;
            
        } catch (Exception erro) {
            return 1;
        }
    }
}
