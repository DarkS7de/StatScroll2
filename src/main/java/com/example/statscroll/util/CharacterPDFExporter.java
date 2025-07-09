package com.example.statscroll.util;

import com.example.statscroll.model.Characters;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class CharacterPDFExporter {

    public void exportToPDF(Characters character, String filePath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font bodyFont = new Font(Font.HELVETICA, 12);

            document.add(new Paragraph("Scheda Personaggio", titleFont));
            document.add(new Paragraph(" ", bodyFont)); // spazio

            document.add(new Paragraph("Nome: " + character.getName(), bodyFont));
            document.add(new Paragraph("Classe: " + character.getChar_class(), bodyFont));
            document.add(new Paragraph("Livello: " + character.getLevel(), bodyFont));
            document.add(new Paragraph("Razza: " + character.getRace(), bodyFont));
            document.add(new Paragraph("Iniziativa: " + character.getInitiative(), bodyFont));
            document.add(new Paragraph("Punti ferita massimi: " + character.getMaxhp(), bodyFont));
            document.add(new Paragraph("Statistiche:", bodyFont));
            document.add(new Paragraph("  STR: " + character.getStr() +
                    " DEX: " + character.getDex() +
                    " CON: " + character.getCon() +
                    " INT: " + character.getIntel() +
                    " WIS: " + character.getWis() +
                    " CHA: " + character.getCha(), bodyFont));

            document.close();
            System.out.println("PDF generato correttamente: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

