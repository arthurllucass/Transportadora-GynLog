package relatorios;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import controle.*;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.classes.*;
import persistencia.*;

public class RelatorioPDF {

    private Font tituloFont;
    private Font corpoFont;
    private String caminhoPasta;

    public RelatorioPDF() {
        this.tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        this.corpoFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        String home = System.getProperty("user.home");
        this.caminhoPasta = home + "/RelatoriosTransportadoraGynLog";
        File pasta = new File(this.caminhoPasta);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    private PdfPCell criarCelulaCentralizada(String texto) {
        PdfPCell celula = new PdfPCell(new Paragraph(texto, corpoFont));
        celula.setHorizontalAlignment(Element.ALIGN_CENTER);
        return celula;
    }
    
    private PdfPCell criarHeader(String texto) {
        Font fonteNegrito = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        PdfPCell header = new PdfPCell(new Paragraph(texto, fonteNegrito));
        header.setBackgroundColor(Color.LIGHT_GRAY);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setPadding(5);
        return header;
    }
  
    public void gerarRelatorio(ArrayList<Movimentacao> lista, LocalDate dataInicial, LocalDate dataFinal, TipoDeVeiculos veiculo, TipoDeDespesa despesa) throws Exception{
        try {
            
            MovimentacaoController moviController = new MovimentacaoController();            
            lista = moviController.filtrarPorPeriodo(lista,dataInicial, dataFinal);
            
            String filtros = "";
                        
            if(veiculo != null){lista = moviController.filtrarPorVeiculo(lista, veiculo); filtros += "_" + veiculo.getPlaca();}
            if(despesa != null){lista = moviController.filtrarPorDespesa(lista, despesa); filtros += "_" + despesa.getDescricao();}
            
            Document documentoPDF = new Document();
            String caminhoArquivo = caminhoPasta + "/Despesas_Frota_" + dataInicial.toString() + "_" + dataFinal.toString() + filtros + ".pdf";
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(caminhoArquivo));
            documentoPDF.open();            
            String caminhoImagem = "src/imagens/logo200x200_letrasazul.png";

            try {
                Image logo = Image.getInstance(caminhoImagem);
                logo.scaleToFit(120, 120);
                logo.setAlignment(Image.ALIGN_CENTER);

                documentoPDF.add(logo);
                documentoPDF.add(Chunk.NEWLINE);

            } catch (Exception e) {
                System.out.println("Erro ao carregar imagem: " + e.getMessage());
            }

            Paragraph titulo = new Paragraph("Relatório: Somatório Geral das Despesas da Frota", tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documentoPDF.add(titulo);
            documentoPDF.add(new Paragraph(""));
            documentoPDF.add(new Paragraph("Data Inicial: " + dataInicial.toString()));
            documentoPDF.add(new Paragraph("Data Final: " + dataFinal.toString()));
            documentoPDF.add(new Paragraph("/n"));

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.addCell(criarHeader("ID Veículo"));
            table.addCell(criarHeader("Placa"));
            table.addCell(criarHeader("Modelo"));
            table.addCell(criarHeader("Tipo de Despesa"));
            table.addCell(criarHeader("Descrição"));
            table.addCell(criarHeader("Data"));
            table.addCell(criarHeader("Valor"));

            double total = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            TipoDeDespesasDAO daoTipo = new TipoDeDespesasDAO();
            TipoDeVeiculoDAO daoVeiculo = new TipoDeVeiculoDAO();

            for (Movimentacao movimentacao : lista) {
                String placa = "";
                String modelo = "";
                try {
                    TipoDeVeiculos veiculoLista = daoVeiculo.buscarPorId(movimentacao.getIdVeiculo());
                    placa = veiculoLista.getPlaca();
                    modelo = veiculoLista.getModelo();
                } catch (Exception e) {
                    placa = "Não encontrado";
                    modelo = "Não encontrado";
                }

                // buscar tipo de despesa
                String nomeTipo = "";
                try {
                    nomeTipo = daoTipo.buscarPorId(movimentacao.getIdTipoDespesa()).getDescricao();
                } catch (Exception e) {
                    nomeTipo = "Tipo não encontrado";
                }
                
                table.addCell(criarCelulaCentralizada("" + movimentacao.getIdVeiculo()));
                table.addCell(criarCelulaCentralizada(placa));
                table.addCell(criarCelulaCentralizada(modelo));
                table.addCell(criarCelulaCentralizada(nomeTipo));
                table.addCell(criarCelulaCentralizada(movimentacao.getDescricao()));

                String dataFormatada = "";
                if (movimentacao.getDataMovimentacao() != null) {
                    dataFormatada = sdf.format(movimentacao.getDataMovimentacao());
                } else {
                    dataFormatada = "Data não informada";
                }
                table.addCell(criarCelulaCentralizada(dataFormatada));

                table.addCell(criarCelulaCentralizada("R$ " + movimentacao.getValor()));

                total += movimentacao.getValor();
                
            }

            documentoPDF.add(table);
            documentoPDF.add(Chunk.NEWLINE);
            documentoPDF.add(new Paragraph("Total das Despesas: R$ " + String.format("%.2f", total), tituloFont));
            documentoPDF.close();

            System.out.println("PDF de Despesas do mês gerado com sucesso!");
            JOptionPane.showMessageDialog(null, "PDF gerado com sucesso!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }    
    
    public void gerarRelatorioVeiculosInativos(ArrayList<TipoDeVeiculos> lista) {
        try {
            Document documentoPDF = new Document();
            String caminhoArquivo = caminhoPasta + "/Relatorio_Veiculos_Inativos.pdf";

            PdfWriter.getInstance(documentoPDF, new FileOutputStream(caminhoArquivo));
            documentoPDF.open();
            String caminhoImagem = "src/imagens/logo200x200_letrasazul.png";

            try {
                Image logo = Image.getInstance(caminhoImagem);
                logo.scaleToFit(120, 120);
                logo.setAlignment(Image.ALIGN_CENTER);

                documentoPDF.add(logo);
                documentoPDF.add(Chunk.NEWLINE);

            } catch (Exception e) {
                System.out.println("Erro ao carregar imagem: " + e.getMessage());
            }

            Paragraph titulo = new Paragraph("Relatório de Veículos Inativos", tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documentoPDF.add(titulo);
            documentoPDF.add(Chunk.NEWLINE);

            PdfPTable tabela = new PdfPTable(5);
            tabela.setWidthPercentage(100);
            tabela.addCell(criarHeader("ID"));
            tabela.addCell(criarHeader("Placa"));
            tabela.addCell(criarHeader("Marca"));
            tabela.addCell(criarHeader("Modelo"));
            tabela.addCell(criarHeader("Ano"));

            for (TipoDeVeiculos veiculos : lista) {
                if (veiculos.getStatus() == StatusVeiculo.INATIVO) {
                    tabela.addCell(criarCelulaCentralizada("" + veiculos.getIdVeiculo()));
                    tabela.addCell(criarCelulaCentralizada(veiculos.getPlaca()));
                    tabela.addCell(criarCelulaCentralizada(veiculos.getMarca()));
                    tabela.addCell(criarCelulaCentralizada(veiculos.getModelo()));
                    tabela.addCell(criarCelulaCentralizada("" + veiculos.getAnoDeFabricacao()));
                }
            }

            documentoPDF.add(tabela);
            documentoPDF.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    




