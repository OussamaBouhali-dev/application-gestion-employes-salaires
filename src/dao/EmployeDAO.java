package dao;

import model.Employe;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAO {

    // Récupérer tous les employés de la base
    public List<Employe> getAllEmployes() {
        List<Employe> employes = new ArrayList<>();
        String sql = "SELECT idEmploye, nom, prenom, poste, idService, dateEmbauche, salaireDeBase FROM employe";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employe e = new Employe(
                        rs.getInt("idEmploye"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("poste"),
                        rs.getInt("idService"),
                        rs.getDate("dateEmbauche").toLocalDate(),
                        rs.getDouble("salaireDeBase")
                );
                employes.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employes;
    }

    // Insérer un nouvel employé dans la base et récupérer son ID généré
    public boolean insertEmploye(Employe emp) {
        String sql = "INSERT INTO employe (nom, prenom, poste, idService, dateEmbauche, salaireDeBase) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, emp.getNom());
            ps.setString(2, emp.getPrenom());
            ps.setString(3, emp.getPoste());
            ps.setInt(4, emp.getIdService());
            ps.setDate(5, Date.valueOf(emp.getDateEmbauche()));
            ps.setDouble(6, emp.getSalaireDeBase());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        emp.setIdEmploye(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Récupérer un employé par son ID
    public Employe getEmployeById(int idEmploye) {
        Employe employe = null;
        String sql = "SELECT idEmploye, nom, prenom, poste, idService, dateEmbauche, salaireDeBase FROM employe WHERE idEmploye = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEmploye);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    employe = new Employe(
                            rs.getInt("idEmploye"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("poste"),
                            rs.getInt("idService"),
                            rs.getDate("dateEmbauche").toLocalDate(),
                            rs.getDouble("salaireDeBase")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employe;
    }
}
