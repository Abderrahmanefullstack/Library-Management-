package DAO;

import Entites.Livre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpDAOLivre implements IDAOLivre {

    @Override
    public void Ajouter(Livre liv) {
        String query = "INSERT INTO livre (isbn, titre, auteur, date_parution, domaine, nb_exemplaires) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection cnx = ConnexionBD.SeConnecter();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setInt(1, liv.getIsbn());
            stmt.setString(2, liv.getTitre());
            stmt.setString(3, liv.getAuteur());
            stmt.setString(4, liv.getDate_parution());
            stmt.setString(5, liv.getDomaine());
            stmt.setInt(6, liv.getNb_exemplaires());
            stmt.executeUpdate();
            System.out.println("Insertion réussie");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Supprimer(int id) {
        String query = "DELETE FROM livre WHERE isbn = ?";
        try (Connection cnx = ConnexionBD.SeConnecter();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Suppression réussie");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Modifier(Livre newl) {
        String query = "UPDATE livre SET titre = ?, auteur = ?, date_parution = ?, domaine = ?, nb_exemplaires = ? WHERE isbn = ?";
        try (Connection cnx = ConnexionBD.SeConnecter();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, newl.getTitre());
            stmt.setString(2, newl.getAuteur());
            stmt.setString(3, newl.getDate_parution());
            stmt.setString(4, newl.getDomaine());
            stmt.setInt(5, newl.getNb_exemplaires());
            stmt.setInt(6, newl.getIsbn());
            stmt.executeUpdate();
            System.out.println("Modification réussie");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Livre> AfficherTout() {
        List<Livre> liste = new ArrayList<>();
        String query = "SELECT * FROM livre";
        try (Connection cnx = ConnexionBD.SeConnecter();
             Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(query)) {
            while (rs.next()) {
                int isbn = rs.getInt(1);
                String titre = rs.getString(2);
                String auteur = rs.getString(3);
                String date_parution = rs.getString(4);
                String domaine = rs.getString(5);
                int nb_exemplaires = rs.getInt(6);
                liste.add(new Livre(isbn, titre, auteur, date_parution, domaine, nb_exemplaires));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    @Override
    public Livre AfficherParId(int id) {
        Livre liv = null;
        String query = "SELECT * FROM livre WHERE isbn = ?";
        try (Connection cnx = ConnexionBD.SeConnecter();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int isbn = rs.getInt(1);
                String titre = rs.getString(2);
                String auteur = rs.getString(3);
                String date_parution = rs.getString(4);
                String domaine = rs.getString(5);
                int nb_exemplaires = rs.getInt(6);
                liv = new Livre(isbn, titre, auteur, date_parution, domaine, nb_exemplaires);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liv;
    }
}
