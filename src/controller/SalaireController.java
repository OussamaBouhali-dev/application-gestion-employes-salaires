package controller;

import dao.EmployeDAO;
import dao.SalaireDAO;
import model.Employe;
import model.Salaire;
import util.PDFExporter;
import util.SalaireHelper;
import view.SalaireView;

import java.awt.*;
import java.util.List;

public class SalaireController {
    private SalaireView view;
    private SalaireDAO salaireDAO;
    private EmployeDAO employeDAO;

    public SalaireController(SalaireView view) {
        this.view = view;
        this.salaireDAO = new SalaireDAO();
        this.employeDAO = new EmployeDAO();
        initialize();
    }

    private void initialize() {
        loadEmployes();
        setupListeners();
    }

    private void loadEmployes() {
        List<Employe> employes = employeDAO.getAllEmployes();
        view.setEmployes(employes);
        if (!employes.isEmpty()) {
            loadSalaires(employes.get(0));
        }
    }

    private void setupListeners() {
        view.getAjouterSalaireBtn().addActionListener(e -> ajouterSalaire());
        view.getVoirHistoriqueBtn().addActionListener(e -> {
            Employe employe = view.getSelectedEmploye();
            if (employe != null) {
                loadSalaires(employe);
            }
        });
        view.getGenererFichePaieBtn().addActionListener(e -> genererFichePaie());
    }

    private void loadSalaires(Employe employe) {
        List<Salaire> salaires = salaireDAO.getSalairesByEmploye(employe.getIdEmploye());
        view.setSalaireTableData(salaires);
    }

    private void ajouterSalaire() {
        Employe employe = view.getSelectedEmploye();
        if (employe == null) {
            view.showMessage("Veuillez sélectionner un employé");
            return;
        }

        Object[] inputs = view.showAjouterSalaireDialog();
        if (inputs != null) {
            try {
                int mois = (int) inputs[0];
                int annee = (int) inputs[1];
                double primes = (double) inputs[2];
                double retenues = (double) inputs[3];

                Salaire salaire = new Salaire(
                        0, // ID sera généré par la base de données
                        employe,
                        mois,
                        annee,
                        employe.getSalaireDeBase(),
                        primes,
                        retenues
                );

                salaireDAO.insertSalaire(salaire);
                view.showMessage("Salaire ajouté avec succès!");
                loadSalaires(employe);
            } catch (Exception e) {
                view.showMessage("Erreur: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void genererFichePaie() {
        try {
            // 1. Vérifier la sélection
            int selectedId = view.getSelectedSalaireId();
            if (selectedId == -1) {
                view.showMessage("Veuillez sélectionner un salaire dans le tableau");
                return;
            }

            // 2. Récupérer le salaire
            Salaire salaire = salaireDAO.getSalaireById(selectedId);
            if (salaire == null) {
                view.showMessage("Salaire introuvable en base de données");
                return;
            }

            // 3. Préparer le contenu
            String pdfContent = generatePdfContent(salaire);
            String fileName = "Fiche_Paie_" + salaire.getEmploye().getNom() +
                    "_" + salaire.getMois() + "_" + salaire.getAnnee() + ".pdf";

            // 4. Chemin garanti (Bureau ou Téléchargements)
            String safePath = System.getProperty("user.home") + "/Desktop/" + fileName;

            // 5. Génération avec vérification
            try {
                PDFExporter.exporterFichePaie(safePath, pdfContent);
                view.showMessage("Fiche générée avec succès!\n" + safePath);

                // Optionnel : ouvrir le fichier automatiquement
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new java.io.File(safePath));
                }
            } catch (Exception e) {
                view.showMessage("Échec de génération: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            view.showMessage("Erreur inattendue: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String generatePdfContent(Salaire salaire) {
        StringBuilder sb = new StringBuilder();
        sb.append("FICHE DE PAIE\n\n");
        sb.append("Employé: ").append(salaire.getEmploye().getNom())
                .append(" ").append(salaire.getEmploye().getPrenom()).append("\n");
        sb.append("Poste: ").append(salaire.getEmploye().getPoste()).append("\n");
        sb.append("Service: ").append(salaire.getEmploye().getIdService()).append("\n\n");

        sb.append("Période: ").append(salaire.getMois()).append("/")
                .append(salaire.getAnnee()).append("\n\n");

        sb.append("Salaire de base: ").append(String.format("%.2f MAD", salaire.getMontant())).append("\n");
        sb.append("Primes: ").append(String.format("%.2f MAD", salaire.getPrimes())).append("\n");
        sb.append("Retenues: ").append(String.format("%.2f MAD", salaire.getRetenues())).append("\n\n");

        double netAPayer = salaire.getMontant() + salaire.getPrimes() - salaire.getRetenues();
        sb.append("NET À PAYER: ").append(String.format("%.2f MAD", netAPayer)).append("\n\n");

        sb.append("Date d'émission: ").append(java.time.LocalDate.now()).append("\n");

        return sb.toString();
    }
}