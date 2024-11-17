package IHM;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListeLivre extends JFrame {
    private JTable booklistes;
    private JPanel panel1;
    private JScrollPane JScrollPanel;

    public ListeLivre() {
        panel1 = new JPanel();

        // Définir les colonnes du tableau
        String[] columnNames = {"Titre", "Auteur", "Année", "Genre"};

        // Exemple de données, tu peux remplacer cela par des données venant de la base
        Object[][] data = {
                {"Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "Philosophique"},
                {"1984", "George Orwell", 1949, "Science-fiction"},
                {"Le Meilleur des Mondes", "Aldous Huxley", 1932, "Dystopie"}
        };

        // Créer un modèle de tableau avec les colonnes et les données
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Créer le JTable avec ce modèle
        booklistes = new JTable(model);

        // Ajouter le JTable à un JScrollPane pour une meilleure présentation
        JScrollPane scrollPane = new JScrollPane(booklistes);
        panel1.add(scrollPane);
    }

    // Retourner le panel contenant le tableau
    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        // Créer une fenêtre JFrame
        JFrame frame = new JFrame("Liste des Livres");
        ListeLivre listeLivre = new ListeLivre();

        // Ajouter le panel principal à la fenêtre
        frame.setContentPane(listeLivre.getPanel1());

        // Configurer la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Taille de la fenêtre
        frame.setVisible(true); // Afficher la fenêtre
    }
}
