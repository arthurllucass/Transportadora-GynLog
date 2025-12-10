package persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

public class BancoDeDados {

    private static final String PASTA = "banco";

    // Garante que a pasta banco e o arquivo existam
    public static File getArquivo(String nomeArquivo) {
        try {
            // Pega o diretório de execução (funciona no NetBeans e no JAR)
            File base = new File("").getAbsoluteFile();

            // Cria a pasta banco
            File pastaBanco = new File(base, PASTA);
            if (!pastaBanco.exists()) {
                pastaBanco.mkdirs();
            }

            // Cria o arquivo
            File arquivo = new File(pastaBanco, nomeArquivo);
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            return arquivo;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivo do banco de dados!", e);
        }
    }

    public  String getCaminhoArquivo(String nomeArquivo) {
        try {
            File base = new File("").getAbsoluteFile();

            File pastaBanco = new File(base, "banco");
            if (!pastaBanco.exists()) {
                pastaBanco.mkdirs();
            }
            File arquivo = new File(pastaBanco, nomeArquivo);
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            return arquivo.getAbsolutePath(); 

        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar/ler arquivo do banco!", e);
        }
    }
    
    public String getCaminhoRelatorios() {
        File base = new File("").getAbsoluteFile();
        File pastaRelatorios = new File(base, "Relatorios");
        if (!pastaRelatorios.exists()) {
            pastaRelatorios.mkdirs();
        }
        return pastaRelatorios.getAbsolutePath();
    }

}
