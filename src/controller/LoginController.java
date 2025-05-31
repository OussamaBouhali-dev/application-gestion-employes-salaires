package controller;

import dao.Database;
import view.LoginView;
import view.DashboardView;
//import database.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;

        this.loginView.getLoginButton().addActionListener(e -> {
            login();
        });
    }

    private void login() {
        String username = loginView.getUsernameField().getText();
        String password = new String(loginView.getPasswordField().getPassword());

        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM utilisateur WHERE nomUtilisateur = ? AND motDePasse = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role"); // tu peux l’utiliser pour rediriger selon le rôle
                JOptionPane.showMessageDialog(loginView, "Connexion réussie en tant que " + role + " !");
                loginView.dispose();

                DashboardView dashboardView = new DashboardView();
                DashboardController dashboardController = new DashboardController(dashboardView);
                dashboardView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(loginView, "Nom d'utilisateur ou mot de passe incorrect.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(loginView, "Erreur de connexion à la base de données : " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
