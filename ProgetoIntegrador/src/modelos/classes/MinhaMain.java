/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.classes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class MinhaMain {

    public static void main(String[] args) {

        try {
            PDDocument doc = new PDDocument();
            doc.addPage(new PDPage());
            doc.save("teste.pdf");
            doc.close();
            System.out.println("PDF funcionando!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
