package controller;

import dao.EmployeDAO;
import model.Employe;
import view.EmployeView;

import javax.swing.*;
import java.time.LocalDate;

public class EmployeController {
    private final EmployeView view;
    private final EmployeDAO employeDAO;

    public EmployeController(EmployeView view) {
        this.view = view;
        this.employeDAO = new EmployeDAO();
        initialize();
    }

    private void initialize() {
        refreshEmployeeList();
        setupListeners();
    }

    private void setupListeners() {
        view.getBtnAjouter().addActionListener(e -> ajouterEmploye());
    }

    private void refreshEmployeeList() {
        view.refreshTable(employeDAO.getAllEmployes());
    }

    private void ajouterEmploye() {
        try {
            String nom = view.getNomField().getText().trim();
            String prenom = view.getPrenomField().getText().trim();
            String poste = view.getPosteField().getText().trim();
            LocalDate dateEmbauche = view.getSelectedDate();
            double salaire = view.getSalaireDeBase();
            int idService = view.getSelectedServiceId();

            if (nom.isEmpty() || prenom.isEmpty() || poste.isEmpty() || dateEmbauche == null || idService <= 0) {
                view.showMessage("Tous les champs sont obligatoires");
                return;
            }

            Employe nouvelEmploye = new Employe(
                    0, nom, prenom, poste, idService, dateEmbauche, salaire);

            if (employeDAO.insertEmploye(nouvelEmploye)) {
                refreshEmployeeList();
                view.clearFields();
                view.showMessage("Employé ajouté avec succès");
            } else {
                view.showMessage("Erreur lors de l'ajout");
            }
        } catch (Exception e) {
            view.showMessage("Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
