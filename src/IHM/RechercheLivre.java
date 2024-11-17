package IHM;

import DAO.ConnexionBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RechercheLivre extends JFrame {
    private JLabel JLabel_isbn;
    private JLabel JLabel_domaine;
    private JLabel JLabel_nomAuteur;
    private JTextField txt_isbn;
    private JTextField txt_domaine;
    private JTextField txt_NomAuteur;
    private JButton btn_Rechercher_Id;
    private JButton btn_Rechercher_Domaine;
    private JButton btn_Rechercher_Auteur;
    private JButton btn_afficherToutLivres;

    public RechercheLivre() {
        // Initialisation des composants
        JLabel_isbn = new JLabel("ISBN:");
        JLabel_domaine = new JLabel("Domaine:");
        JLabel_nomAuteur = new JLabel("Auteur:");

        txt_isbn = new JTextField(15);
        txt_domaine = new JTextField(15);
        txt_NomAuteur = new JTextField(15);

        btn_Rechercher_Id = new JButton("Rechercher par ISBN");
        btn_Rechercher_Domaine = new JButton("Rechercher par Domaine");
        btn_Rechercher_Auteur = new JButton("Rechercher par Auteur");
        btn_afficherToutLivres = new JButton("Afficher tous les livres");

        // Ajouter les composants à la fenêtre
        JPanel panel = new JPanel();
        panel.add(JLabel_isbn);
        panel.add(txt_isbn);
        panel.add(JLabel_domaine);
        panel.add(txt_domaine);
        panel.add(JLabel_nomAuteur);
        panel.add(txt_NomAuteur);
        panel.add(btn_Rechercher_Id);
        panel.add(btn_Rechercher_Domaine);
        panel.add(btn_Rechercher_Auteur);
        panel.add(btn_afficherToutLivres);

        // Ajout du panel au JFrame
        setContentPane(panel);

        // Logique du bouton "Rechercher par ISBN"
        btn_Rechercher_Id.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = txt_isbn.getText();
                if (isbn.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un ISBN.");
                } else {
                    rechercherLivreParISBN(isbn);
                }
            }
        });

        // Logique du bouton "Rechercher par Domaine"
        btn_Rechercher_Domaine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String domaine = txt_domaine.getText();
                if (domaine.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un domaine.");
                } else {
                    rechercherLivreParDomaine(domaine);
                }
            }
        });

        // Logique du bouton "Rechercher par Auteur"
        btn_Rechercher_Auteur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String auteur = txt_NomAuteur.getText();
                if (auteur.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un auteur.");
                } else {
                    rechercherLivreParAuteur(auteur);
                }
            }
        });

        // Logique du bouton "Afficher tous les livres"
        btn_afficherToutLivres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherTousLesLivres();
            }
        });

        // Configurer la fenêtre
        setTitle("Recherche de Livre");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ferme la fenêtre sans quitter l'application
        setLocationRelativeTo(null); // Centre la fenêtre
    }

    // Méthode pour rechercher un livre par ISBN
    private void rechercherLivreParISBN(String isbn) {
        String query = "SELECT * FROM livres WHERE isbn = ?";
        try (Connection cnx = ConnexionBD.SeConnecter();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            afficherResultats(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour rechercher un livre par Domaine
    private void rechercherLivreParDomaine(String domaine) {
        String query = "SELECT * FROM livres WHERE domaine LIKE ?";
        try (Connection cnx = ConnexionBD.SeConnecter();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, "%" + domaine + "%");
            ResultSet rs = stmt.executeQuery();
            afficherResultats(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour rechercher un livre par Auteur
    private void rechercherLivreParAuteur(String auteur) {
        String query = "SELECT * FROM livres WHERE auteur LIKE ?";
        try (Connection cnx = ConnexionBD.SeConnecter();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, "%" + auteur + "%");
            ResultSet rs = stmt.executeQuery();
            afficherResultats(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher tous les livres
    private void afficherTousLesLivres() {
        String query = "SELECT * FROM livres";
        try (Connection cnx = ConnexionBD.SeConnecter();
             Statement stmt = cnx.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            afficherResultats(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher les résultats de la recherche
    private void afficherResultats(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String isbn = rs.getString("isbn");
            String domaine = rs.getString("domaine");
            String auteur = rs.getString("auteur");
            System.out.println("ISBN: " + isbn + ", Domaine: " + domaine + ", Auteur: " + auteur);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RechercheLivre rechercheLivreForm = new RechercheLivre();
                rechercheLivreForm.setVisible(true);
            }
        });
    }
}
