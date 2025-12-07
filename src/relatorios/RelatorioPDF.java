import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelos.classes.Movimentacao;

public class RelatorioPDF {

    private Font tituloFont;
    private Font corpoFont;

    public RelatorioPDF() {
        this.tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        this.corpoFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
    }

    public void gerarDespesasDoMes(int mes, int ano, ArrayList<Movimentacao> lista) {
        try {
            Document documentoPDF = new Document();
            PdfWriter.getInstance(documentoPDF, new FileOutputStream("Despesas_Frota_" + mes + "_" + ano + ".pdf"));
            documentoPDF.open();

            documentoPDF.add(new Paragraph("Relatório: Somatório Geral das Despesas da Frota", tituloFont));
            documentoPDF.add(new Paragraph("Mês/Ano: " + mes + "/" + ano));
            documentoPDF.add(Chunk.NEWLINE);

            // Criando a tabela com 5 colunas
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.addCell("ID Veículo");
            table.addCell("Tipo de Despesa");
            table.addCell("Descrição");
            table.addCell("Data");
            table.addCell("Valor");

            double total = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Movimentacao movimentacao : lista) {
                table.addCell(String.valueOf(movimentacao.getIdVeiculo()));
                table.addCell(String.valueOf(movimentacao.getIdTipoDespesa())); // Se tiver nome do tipo, pode substituir
                table.addCell(movimentacao.getDescricao());
                table.addCell(sdf.format(movimentacao.getDataMovimentacao()));
                table.addCell(String.format("R$ %.2f", movimentacao.getValor()));
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


