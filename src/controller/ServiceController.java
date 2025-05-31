package controller;

import dao.ServiceDAO;
import model.Service;
import view.ServiceView;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.List;

public class ServiceController {
    private ServiceView view;
    private ServiceDAO dao;
    private List<Service> services;

    public ServiceController(ServiceView view) {
        this.view = view;
        this.dao = new ServiceDAO();
        this.services = dao.getAllServices();
        initialize();
    }

    private void initialize() {
        view.refreshTable(services);
        setupListeners();
    }

    private void setupListeners() {
        view.getAjouterBtn().addActionListener(e -> ajouterService());
        view.getModifierBtn().addActionListener(e -> modifierService());
        view.getSupprimerBtn().addActionListener(e -> supprimerService());
        view.getServiceTable().getSelectionModel().addListSelectionListener(this::handleTableSelection);
    }

    private void handleTableSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Service selected = view.getSelectedService();
            if (selected != null) {
                view.fillFields(selected);
            }
        }
    }

    private void ajouterService() {
        String nom = view.getNomField().getText().trim();
        String description = view.getDescriptionArea().getText().trim();

        if (nom.isEmpty()) {
            view.showMessage("Le nom du service est obligatoire");
            return;
        }

        try {
            Service newService = new Service(nom, description);
            if (dao.addService(newService)) {
                services = dao.getAllServices();
                view.refreshTable(services);
                view.clearFields();
                view.showMessage("Service ajouté avec succès");
            } else {
                view.showMessage("Erreur lors de l'ajout du service");
            }
        } catch (Exception ex) {
            view.showMessage("Erreur: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void modifierService() {
        Service selected = view.getSelectedService();
        if (selected == null) {
            view.showMessage("Veuillez sélectionner un service à modifier");
            return;
        }

        String nom = view.getNomField().getText().trim();
        String description = view.getDescriptionArea().getText().trim();

        if (nom.isEmpty()) {
            view.showMessage("Le nom du service est obligatoire");
            return;
        }

        selected.setNom(nom);
        selected.setDescription(description);

        if (dao.updateService(selected)) {
            services = dao.getAllServices();
            view.refreshTable(services);
            view.showMessage("Service modifié avec succès");
        } else {
            view.showMessage("Erreur lors de la modification du service");
        }
    }

    private void supprimerService() {
        Service selected = view.getSelectedService();
        if (selected == null) {
            view.showMessage("Veuillez sélectionner un service à supprimer");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Êtes-vous sûr de vouloir supprimer ce service?",
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.deleteService(selected.getIdService())) {
                services = dao.getAllServices();
                view.refreshTable(services);
                view.clearFields();
                view.showMessage("Service supprimé avec succès");
            } else {
                view.showMessage("Erreur lors de la suppression du service");
            }
        }
    }
}
