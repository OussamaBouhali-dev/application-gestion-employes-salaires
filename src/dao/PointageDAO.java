package dao;

import model.Pointage;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PointageDAO {

    public void pointerArrivee(Pointage pointage) {
        String sql = "INSERT INTO pointage (employe, date, heureArrivee) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pointage.getIdEmploye());
            ps.setDate(2, Date.valueOf(pointage.getDate()));
            ps.setTime(3, Time.valueOf(pointage.getHeureArrivee()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pointerDepart(int idEmploye, LocalDate date, LocalTime heureDepart) {
        String sql = "UPDATE pointage SET heureDepart = ? WHERE employe = ? AND date = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTime(1, Time.valueOf(heureDepart));
            ps.setInt(2, idEmploye);
            ps.setDate(3, Date.valueOf(date));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pointage> getAllPointagesByEmploye(int idEmploye) {
        List<Pointage> pointages = new ArrayList<>();
        String sql = "SELECT * FROM pointage WHERE employe = ? ORDER BY date DESC";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEmploye);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pointage p = new Pointage(
                        rs.getInt("idPointage"),
                        rs.getInt("employe"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("heureArrivee") != null ? rs.getTime("heureArrivee").toLocalTime() : null,
                        rs.getTime("heureDepart") != null ? rs.getTime("heureDepart").toLocalTime() : null
                );
                pointages.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pointages;
    }
}