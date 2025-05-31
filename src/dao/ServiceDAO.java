package dao;

import model.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    // Validation centralisée
    private void validateServiceNom(String nom) {
        if (nom == null || nom.trim().isBlank()) {
            throw new IllegalArgumentException("Le nom du service est obligatoire");
        }
        if (nom.trim().length() > 100) {
            throw new IllegalArgumentException("Le nom ne doit pas dépasser 100 caractères");
        }
    }

    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT idService, nom, description FROM service";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Service service = new Service(
                        rs.getInt("idService"),
                        rs.getString("nom"),
                        rs.getString("description")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving services: " + e.getMessage());
            e.printStackTrace();
        }
        return services;
    }

    public boolean addService(Service service) throws IllegalArgumentException {
        String nomTrimmed = service.getNom() != null ? service.getNom().trim() : null;

        // Debug: affiche ce qu'on reçoit comme nom
        System.out.println("Nom reçu dans addService: '" + nomTrimmed + "'");

        validateServiceNom(nomTrimmed);

        String sql = "INSERT INTO service (nom, description) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomTrimmed);
            ps.setString(2, service.getDescription() != null ? service.getDescription().trim() : null);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur base de données: " + e.getMessage(), e);
        }
    }

    public boolean updateService(Service service) {
        String nomTrimmed = service.getNom() != null ? service.getNom().trim() : null;

        try {
            validateServiceNom(nomTrimmed);
        } catch (IllegalArgumentException e) {
            System.err.println("Validation failed during update: " + e.getMessage());
            return false;
        }

        String sql = "UPDATE service SET nom = ?, description = ? WHERE idService = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nomTrimmed);
            pstmt.setString(2, service.getDescription() != null ? service.getDescription().trim() : null);
            pstmt.setInt(3, service.getIdService());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteService(int idService) {
        String sql = "DELETE FROM service WHERE idService = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idService);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Service getServiceById(int idService) {
        String sql = "SELECT nom, description FROM service WHERE idService = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idService);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Service(
                        idService,
                        rs.getString("nom"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
