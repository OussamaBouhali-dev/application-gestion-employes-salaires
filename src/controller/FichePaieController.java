package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import model.Salaire;
import view.FichePaieView;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class FichePaieController {
    private FichePaieView view;
    private Salaire salaire;

    public FichePaieController(FichePaieView view, Salaire salaire) {
        this.view = view;
        this.salaire = salaire;

        afficherFichePaie();
        view.exporterBtn.addActionListener(e -> exporterPDF());
    }

    private void afficherFichePaie() {
        StringBuilder sb = new StringBuilder();

        sb.append("FICHE DE PAIE\n\n");
        sb.append("Employé : ").append(salaire.getEmploye().getNom()).append(" ")
                .append(salaire.getEmploye().getPrenom()).append("\n");
        sb.append("Poste : ").append(salaire.getEmploye().getPoste()).append("\n");
        sb.append("Service ID : ").append(salaire.getEmploye().getIdService()).append("\n\n");

        sb.append("Période : ").append(salaire.getMois()).append(" / ")
                .append(salaire.getAnnee()).append("\n\n");

        sb.append("Salaire de base : ").append(String.format("%.2f", salaire.getMontant()))
                .append(" MAD\n");
        sb.append("Primes : ").append(String.format("%.2f", salaire.getPrimes()))
                .append(" MAD\n");
        sb.append("Retenues : ").append(String.format("%.2f", salaire.getRetenues()))
                .append(" MAD\n\n");

        double netAPayer = salaire.getMontant() + salaire.getPrimes() - salaire.getRetenues();
        sb.append("Salaire net à payer : ").append(String.format("%.2f", netAPayer))
                .append(" MAD\n\n");

        sb.append("Date d'émission : ").append(LocalDate.now()).append("\n");

        view.ficheArea.setText(sb.toString());
    }

    private void exporterPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("FichePaie.pdf"));

        int option = fileChooser.showSaveDialog(view);
        if (option == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();

            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(path));
                document.open();
                document.add(new Paragraph(view.ficheArea.getText()));
                document.close();

                JOptionPane.showMessageDialog(view, "Fiche de paie exportée avec succès !");
            } catch (DocumentException | IOException ex) {
                JOptionPane.showMessageDialog(view, "Erreur lors de l'exportation PDF : " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
