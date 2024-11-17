package IHM;

import DAO.ConnexionBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GestionLivre extends JFrame {
    private JTextField txt_Isbn;
    private JTextField txt_Titre;
    private JTextField txt_dateProduction;
    private JTextField txt_nbexmp;
    private JTextField txt_Auteur;
    private JComboBox<String> cbox_domaine;
    private JButton enregistrerButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JButton annulerButton;
    private JButton listeDesExemplairesButton;

    public GestionLivre() {
        // Initialiser la fenêtre
        setTitle("Gestion des Livres");
        setSize(500, 500); // Taille de la fenêtre augmentée
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        // Initialisation des composants
        JLabel lbl_isbn = new JLabel("ISBN:");
        txt_Isbn = new JTextField();
        txt_Isbn.setPreferredSize(new Dimension(200, 30));

        JLabel lbl_titre = new JLabel("Titre:");
        txt_Titre = new JTextField();
        txt_Titre.setPreferredSize(new Dimension(200, 30));

        JLabel lbl_dateProduction = new JLabel("Date de production:");
        txt_dateProduction = new JTextField();
        txt_dateProduction.setPreferredSize(new Dimension(200, 30));

        JLabel lbl_nbExemplaires = new JLabel("Nombre d'exemplaires:");
        txt_nbexmp = new JTextField();
        txt_nbexmp.setPreferredSize(new Dimension(200, 30));

        JLabel lbl_auteur = new JLabel("Nom de l'auteur:");
        txt_Auteur = new JTextField();
        txt_Auteur.setPreferredSize(new Dimension(200, 30));

        JLabel lbl_domaine = new JLabel("Domaine:");
        cbox_domaine = new JComboBox<>(new String[]{"Science", "Literature", "Technology", "Art"});
        cbox_domaine.setPreferredSize(new Dimension(200, 30));

        // Ajouter les composants au panel
        add(lbl_isbn);
        add(txt_Isbn);

        add(lbl_titre);
        add(txt_Titre);

        add(lbl_dateProduction);
        add(txt_dateProduction);

        add(lbl_nbExemplaires);
        add(txt_nbexmp);

        add(lbl_auteur);
        add(txt_Auteur);

        add(lbl_domaine);
        add(cbox_domaine);

        // Boutons
        enregistrerButton = new JButton("Ajouter");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");
        annulerButton = new JButton("Annuler");
        listeDesExemplairesButton = new JButton("Liste des Exemplaires");

        add(enregistrerButton);
        add(modifierButton);
        add(supprimerButton);
        add(annulerButton);
        add(listeDesExemplairesButton);


        // Action pour le bouton "Ajouter"
        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = txt_Isbn.getText();
                String titre = txt_Titre.getText();
                String auteur = txt_Auteur.getText();
                String date_parution = txt_dateProduction.getText();
                String domaine = (String) cbox_domaine.getSelectedItem();
                String nb_exemplaires = txt_nbexmp.getText();


                String query = "INSERT INTO livre (isbn, titre, auteur,date_parution, domaine,nb_exemplaires  ) VALUES (?, ?, ?, ?, ?, ?)";

                try (Connection stm = ConnexionBD.SeConnecter()) {
                    PreparedStatement pstmt = stm.prepareStatement(query);
                        pstmt.setString(1, isbn);
                        pstmt.setString(2, titre);
                        pstmt.setString(3, auteur);
                        pstmt.setString(4, date_parution);
                        pstmt.setString(5, domaine);
                        pstmt.setInt(6, Integer.parseInt(nb_exemplaires));

                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Livre ajouté avec succès");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        // Action pour le bouton "Modifier"
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = txt_Isbn.getText();
                String titre = txt_Titre.getText();
                String dateProduction = txt_dateProduction.getText();
                String nbExemplaires = txt_nbexmp.getText();
                String auteur = txt_Auteur.getText();
                String domaine = (String) cbox_domaine.getSelectedItem();

                // Vérifier que l'ISBN est renseigné
                if (isbn.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "L'ISBN doit être renseigné pour modifier un livre.");
                    return;
                }

                String query = "UPDATE livres SET titre = ?, date_production = ?, nb_exemplaires = ?, auteur = ?, domaine = ? WHERE isbn = ?";

                try (Connection stm = ConnexionBD.SeConnecter()) {
                    PreparedStatement pstmt = stm.prepareStatement(query);
                        pstmt.setString(1, titre);
                        pstmt.setString(2, dateProduction);
                        pstmt.setInt(3, Integer.parseInt(nbExemplaires));
                        pstmt.setString(4, auteur);
                        pstmt.setString(5, domaine);
                        pstmt.setString(6, isbn); // Utilisation de l'ISBN pour identifier le livre à modifier

                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Livre modifié avec succès");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        // Action pour le bouton "Supprimer"
        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbn = txt_Isbn.getText();

                // Vérifier que l'ISBN est renseigné
                if (isbn.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "L'ISBN doit être renseigné pour supprimer un livre.");
                    return;
                }

                String query = "DELETE FROM livres WHERE isbn = ?";

                try (Connection stm = ConnexionBD.SeConnecter()) {
                    PreparedStatement pstmt = stm.prepareStatement(query);
                        pstmt.setString(1, isbn); // Utilisation de l'ISBN pour identifier le livre à supprimer

                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Livre supprimé avec succès");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        // Action pour le bouton "Annuler"
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Réinitialiser les champs de texte
                txt_Isbn.setText("");
                txt_Titre.setText("");
                txt_dateProduction.setText("");
                txt_nbexmp.setText("");
                txt_Auteur.setText("");
                cbox_domaine.setSelectedIndex(0); // Remettre la première option par défaut
            }
        });

        // Action pour le bouton "Liste des Exemplaires"
        listeDesExemplairesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Afficher la liste des exemplaires du livre
                String isbn = txt_Isbn.getText();

                if (isbn.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "L'ISBN doit être renseigné pour afficher les exemplaires.");
                    return;
                }

                // Cette partie peut ouvrir une nouvelle fenêtre avec les exemplaires du livre
                String query = "SELECT * FROM exemplaires WHERE isbn = ?";

                try (Connection stm = ConnexionBD.SeConnecter()) {
                    PreparedStatement pstmt = stm.prepareStatement(query);
                        pstmt.setString(1, isbn);

                        // Exécuter la requête pour récupérer les exemplaires
                        ResultSet rs = pstmt.executeQuery();  // Utilisation explicite de ResultSet
                        while (rs.next()) {
                            // Traiter chaque exemplaire ici (par exemple, afficher dans un tableau)
                            // Exemple d'affichage : récupérer des colonnes du résultat
                            String exemplaireID = rs.getString("exemplaire_id");
                            String statut = rs.getString("statut");  // Par exemple, "disponible", "emprunté"
                            System.out.println("Exemplaire ID: " + exemplaireID + ", Statut: " + statut);
                        }

                        // Si tu veux afficher les exemplaires dans une nouvelle fenêtre ou tableau,
                        // tu peux créer une nouvelle interface graphique ici.

                        JOptionPane.showMessageDialog(null, "Liste des exemplaires à afficher.");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GestionLivre();
    }
}
