package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFExporter {
    public static void exporterFichePaie(String filePath, String content) throws Exception {
        Document document = new Document();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            PdfWriter.getInstance(document, fos);
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("FICHE DE PAIE\n\n", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Font contentFont = new Font(Font.FontFamily.HELVETICA, 12);
            for (String line : content.split("\n")) {
                document.add(new Paragraph(line, contentFont));
            }
        } catch (Exception e) {
            throw new Exception("Échec de la génération PDF: " + e.getMessage());
        } finally {
            if (document != null && document.isOpen()) {
                document.close();
            }
            if (fos != null) {
                try { fos.close(); } catch (IOException e) { /* Ignorer */ }
            }
        }
    }
}
