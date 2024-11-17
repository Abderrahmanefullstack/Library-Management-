package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {
    static Connection cnx = null;
    static String url = "jdbc:mysql://localhost:3306/bdd_biblio_jdbc_swing";
    static String login = "root";
    static String password = "";

    public static Connection SeConnecter() {
        // Charger le driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Etablir la connexion avec la BDD
            cnx = DriverManager.getConnection(url, login, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cnx;
    }

    public static void SeDeconnecter() {
        try {
            if (cnx != null && !cnx.isClosed()) {
                cnx.close();
                System.out.println("Déconnexion réussie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
