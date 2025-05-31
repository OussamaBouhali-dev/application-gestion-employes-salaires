package dao;

import model.Employe;
import model.Salaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaireDAO {

    public void insertSalaire(Salaire salaire) {
        String sql = "INSERT INTO salaire (employe, mois, annee, montant, primes, retenues) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, salaire.getEmploye().getIdEmploye());
            ps.setInt(2, salaire.getMois());
            ps.setInt(3, salaire.getAnnee());
            ps.setDouble(4, salaire.getMontant());
            ps.setDouble(5, salaire.getPrimes());
            ps.setDouble(6, salaire.getRetenues());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Salaire> getSalairesByEmploye(int idEmploye) {
        List<Salaire> salaires = new ArrayList<>();
        String sql = "SELECT * FROM salaire WHERE employe = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEmploye);
            ResultSet rs = ps.executeQuery();

            EmployeDAO employeDAO = new EmployeDAO();
            Employe employe = employeDAO.getEmployeById(idEmploye);

            while (rs.next()) {
                Salaire s = new Salaire(
                        rs.getInt("idSalaire"),
                        employe,
                        rs.getInt("mois"),
                        rs.getInt("annee"),
                        rs.getDouble("montant"),
                        rs.getDouble("primes"),
                        rs.getDouble("retenues")
                );
                salaires.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salaires;
    }

    public Salaire getSalaireById(int selectedId) {
        String sql = "SELECT * FROM salaire WHERE idSalaire = ?";
        Salaire salaire = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, selectedId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Récupérer l'employé associé
                int idEmploye = rs.getInt("employe");
                EmployeDAO employeDAO = new EmployeDAO();
                Employe employe = employeDAO.getEmployeById(idEmploye);

                salaire = new Salaire(
                        rs.getInt("idSalaire"),
                        employe,
                        rs.getInt("mois"),
                        rs.getInt("annee"),
                        rs.getDouble("montant"),
                        rs.getDouble("primes"),
                        rs.getDouble("retenues")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salaire;
    }

}
