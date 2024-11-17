package IHM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JPanel panel1;
    private JButton button_GestionLivre;
    private JButton button_ListeLivre;
    private JButton button_RechercheLivre;

    public Main() {
        // Initialiser panel1
        panel1 = new JPanel();
        button_GestionLivre = new JButton("Gérer les livres");
        button_ListeLivre = new JButton("Liste des livres");
        button_RechercheLivre = new JButton("Recherche de livre");

        // Ajouter les boutons au panel
        panel1.add(button_GestionLivre);
        panel1.add(button_ListeLivre);
        panel1.add(button_RechercheLivre);

        // Action pour le bouton "Gérer les livres"
        button_GestionLivre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionLivre gestionLivreForm = new GestionLivre();
                gestionLivreForm.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                frame.dispose();
            }
        });

        // Action pour le bouton "Liste des livres"
        button_ListeLivre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListeLivre listeLivreForm = new ListeLivre();
                listeLivreForm.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                frame.dispose();
            }
        });

        // Action pour le bouton "Recherche de livre"
        button_RechercheLivre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RechercheLivre rechercheLivreForm = new RechercheLivre();
                rechercheLivreForm.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Form");
        Main mainForm = new Main();
        frame.setContentPane(mainForm.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
