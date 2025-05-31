package view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {
    private JButton employesButton;
    private JButton salairesButton;
    private JButton servicesButton;
    private JButton pointageButton;
    private JButton fichesPaieButton;
    private JButton deconnexionButton;

    public DashboardView() {
        setTitle("Tableau de bord");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        employesButton = new JButton("Employés");
        salairesButton = new JButton("Salaires");
        servicesButton = new JButton("Services");
        pointageButton = new JButton("Pointage");
        fichesPaieButton = new JButton("Fiches de paie");
        deconnexionButton = new JButton("Déconnexion");

        panel.add(employesButton);
        panel.add(salairesButton);
        panel.add(servicesButton);
        panel.add(pointageButton);
        panel.add(fichesPaieButton);
        panel.add(deconnexionButton);

        add(panel, BorderLayout.CENTER);
    }

    // Getters pour les boutons (pour y attacher des listeners depuis le contrôleur)
    public JButton getEmployesButton() { return employesButton; }
    public JButton getSalairesButton() { return salairesButton; }
    public JButton getServicesButton() { return servicesButton; }
    public JButton getPointageButton() { return pointageButton; }
    public JButton getFichesPaieButton() { return fichesPaieButton; }
    public JButton getDeconnexionButton() { return deconnexionButton; }

    public AbstractButton getBtnEmployes() {
        return employesButton;
    }
}
