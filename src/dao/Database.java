package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/gestion_demployes"; // adapte le nom de ta base ici
        String user = "root";  // ton user BDD
        String password = "root";  // ton mot de passe BDD

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;  // ou tu peux throw une RuntimeException si tu préfères
        }
    }
}
