package relatorios;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelos.classes.Movimentacao;
import modelos.classes.TipoDeVeiculos;
import persistencia.TipoDeDespesasDAO;
import persistencia.TipoDeVeiculoDAO;

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

    public void gerarDespesasDoMes(int mes, int ano, ArrayList<Movimentacao> lista) {
        try {
            Document documentoPDF = new Document();
            String caminhoArquivo = caminhoPasta + "/Despesas_Frota_" + mes + "_" + ano + ".pdf";
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(caminhoArquivo));
            documentoPDF.open();

            Paragraph titulo = new Paragraph("Relatório: Somatório Geral das Despesas da Frota", tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documentoPDF.add(titulo);
            documentoPDF.add(new Paragraph("Mês/Ano: " + mes + "/" + ano));
            documentoPDF.add(Chunk.NEWLINE);
            documentoPDF.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.addCell(criarCelulaCentralizada("ID Veículo"));
            table.addCell(criarCelulaCentralizada("Placa"));
            table.addCell(criarCelulaCentralizada("Modelo"));
            table.addCell(criarCelulaCentralizada("Tipo de Despesa"));
            table.addCell(criarCelulaCentralizada("Descrição"));
            table.addCell(criarCelulaCentralizada("Data"));
            table.addCell(criarCelulaCentralizada("Valor"));

            double total = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            TipoDeDespesasDAO daoTipo = new TipoDeDespesasDAO();
            TipoDeVeiculoDAO daoVeiculo = new TipoDeVeiculoDAO();

            for (Movimentacao movimentacao : lista) {
                String placa = "";
                String modelo = "";
                try {
                    TipoDeVeiculos veiculo = daoVeiculo.buscarPorId(movimentacao.getIdVeiculo());
                    placa = veiculo.getPlaca();
                    modelo = veiculo.getModelo();
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}

