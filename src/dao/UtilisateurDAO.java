package dao;

import model.Utilisateur;

import java.sql.*;

public class UtilisateurDAO {

    // Récupérer un utilisateur par son nom d'utilisateur
    public Utilisateur getByUsername(String username) {
        String sql = "SELECT * FROM utilisateur WHERE nomUtilisateur = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("idUtilisateur"),
                        rs.getString("nomUtilisateur"),
                        rs.getString("motDePasse"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Ajouter un nouvel utilisateur
    public boolean insertUtilisateur(Utilisateur user) {
        String sql = "INSERT INTO utilisateur (nomUtilisateur, motDePasse, role) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getNomUtilisateur());
            ps.setString(2, user.getMotDePasse());
            ps.setString(3, user.getRole());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Vérifier si le mot de passe correspond (utile pour login)
    public boolean verifierMotDePasse(String username, String motDePasse) {
        Utilisateur utilisateur = getByUsername(username);
        if (utilisateur != null) {
            return utilisateur.getMotDePasse().equals(motDePasse);
        }
        return false;
    }
}
