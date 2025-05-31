package controller;

import view.*;

import javax.swing.*;

public class DashboardController {
    private DashboardView dashboardView;

    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        initController();
    }

    private void initController() {
        // Configuration des listeners pour tous les boutons
        dashboardView.getEmployesButton().addActionListener(e -> openEmployesModule());
        dashboardView.getSalairesButton().addActionListener(e -> openSalairesModule());
        dashboardView.getServicesButton().addActionListener(e -> openServicesModule());
        dashboardView.getPointageButton().addActionListener(e -> openPointageModule());
        dashboardView.getFichesPaieButton().addActionListener(e -> openFichesPaieModule());
        dashboardView.getDeconnexionButton().addActionListener(e -> logout());
    }

    private void openEmployesModule() {
        EmployeView employeView = new EmployeView();
        new EmployeController(employeView);
        employeView.setVisible(true);
    }

    private void openSalairesModule() {
        SalaireView salaireView = new SalaireView();
        new SalaireController(salaireView); // Passer seulement la vue salaire
        salaireView.setVisible(true);
    }

    private void openServicesModule() {
        ServiceView serviceView = new ServiceView();
        new ServiceController(serviceView);
        serviceView.setVisible(true);
    }

    private void openPointageModule() {
        PointageView pointageView = new PointageView();
        new PointageController(pointageView);
        pointageView.setVisible(true);
    }

    private void openFichesPaieModule() {
        // Rediriger vers le module salaires d'abord
        JOptionPane.showMessageDialog(dashboardView,
                "Veuillez d'abord sélectionner un salaire dans le module Salaires");
        openSalairesModule();
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(
                dashboardView,
                "Voulez-vous vraiment vous déconnecter ?",
                "Déconnexion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            dashboardView.dispose();
            // Retour à l'écran de login
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);
        }
    }


    public void setViews(EmployeView empView, ServiceView serviceView, PointageView pointageView, SalaireView salaireView, FichePaieView fichePaieView) {
    }
}