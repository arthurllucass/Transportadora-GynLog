package modelos.classes;

import java.util.Date;

public class Movimentacao {
    private int idMovimentacao = 0;
    private int idVeiculo = 0;
    private int idTipoDespesa = 0;
    private String descricao = "";
    private Date dataMovimentacao;
    private double valor = 0;

    public Movimentacao() {
    }
    
    public Movimentacao(int idMovimentacao, int idVeiculo, int idTipoDespesa, String descricao, Date dataMovimentacao, double valor) {
        this.idMovimentacao = idMovimentacao;
        this.idVeiculo = idVeiculo;
        this.idTipoDespesa = idTipoDespesa;
        this.descricao = descricao;
        this.dataMovimentacao = dataMovimentacao;
        this.valor = valor;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdTipoDespesa() {
        return idTipoDespesa;
    }

    public void setIdTipoDespesa(int idTipoDespesa) {
        this.idTipoDespesa = idTipoDespesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
