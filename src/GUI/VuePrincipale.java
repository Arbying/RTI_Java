package GUI;

import Controleurs.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VuePrincipale extends JFrame {
    private Controleur controleur;

    public VuePrincipale(Controleur controleur) {
        super("Boutique en ligne");
        this.controleur = controleur;
        setSize(850, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComposants();
        setVisible(true);
    }

    private void initComposants() {
        setResizable(false);
        JMenuBar menuBar = new JMenuBar();

        // Menu Fichier
        JMenu menuFichier = new JMenu("Fichier");

        JMenuItem itemConnection = new JMenuItem("Connection");
        JMenuItem itemDeconnection = new JMenuItem("Déconnection");
        JMenuItem itemQuitter = new JMenuItem("Quitter");

        itemConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.connection();
            }
        });

        itemDeconnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.deconnection();
            }
        });

        itemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.quitter();
            }
        });

        menuFichier.add(itemConnection);
        menuFichier.add(itemDeconnection);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);

        menuBar.add(menuFichier);

        // Ajout du JPanel "InfoConnect"
        JPanel panelInfoConnect = new JPanel();
        panelInfoConnect.setPreferredSize(new Dimension(840, 50));
       // panelInfoConnect.setBackground(Color.YELLOW);
        add(panelInfoConnect, BorderLayout.NORTH);
        panelInfoConnect.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f), Color.BLACK));

        // Ajout du JPanel "Login" centré sur la hauteur mais aligné à gauche
        JPanel panelLogin = createPanelWithLabelAndTextField("Login");

        // Ajout du JPanel "Mot de passe" à côté du champ "Login"
        JPanel panelMotDePasse = createPanelWithLabelAndTextField("Mot de passe");

        // Créer un JPanel pour centrer le panelLogin et panelMotDePasse sur la hauteur
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.add(Box.createHorizontalGlue());  // Espace flexible à gauche
        centerPanel.add(panelLogin);
        centerPanel.add(Box.createRigidArea(new Dimension(10, 0)));  // Espace fixe entre Login et Mot de passe
        centerPanel.add(panelMotDePasse);
        centerPanel.add(Box.createHorizontalGlue());  // Espace flexible à droite

        panelInfoConnect.add(centerPanel);

        // Ajout du JPanel "Connection" à côté du champ "Mot de passe"
        JPanel panelConnection = createConnectionPanel();

        panelInfoConnect.add(panelConnection);

        // Ajout du JPanel "NewCl" à côté du bouton "Connection"
        JPanel panelNewCl = createNewClientPanel();


        // Ajout du JPanel "PanelMarche" en dessous de "PanelInfoConnect"
        JPanel panelMarche = new JPanel();
        panelMarche.setPreferredSize(new Dimension(840, 50));
        panelMarche.setBackground(Color.BLUE);
        getContentPane().add(panelMarche, BorderLayout.CENTER);


        panelInfoConnect.add(panelNewCl);

        // Menu Navigation
        JMenu menuNavigation = new JMenu("Navigation");

        JMenuItem itemSuivant = new JMenuItem("Suivant");
        JMenuItem itemPrecedent = new JMenuItem("Précédent");

        itemSuivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Suivant
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Suivant");
            }
        });

        itemPrecedent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Précédent
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Précédent");
            }
        });

        menuNavigation.add(itemSuivant);
        menuNavigation.add(itemPrecedent);

        menuBar.add(menuNavigation);

        // Menu Panier
        JMenu menuPanier = new JMenu("Panier");

        JMenuItem itemAjouterPanier = new JMenuItem("Ajouter");
        JMenuItem itemSupprimerPanier = new JMenuItem("Supprimer");
        JMenuItem itemViderPanier = new JMenuItem("Vider");

        itemAjouterPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Ajouter Panier
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Ajouter Panier");
            }
        });

        itemSupprimerPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Supprimer Panier
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Supprimer Panier");
            }
        });

        itemViderPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Vider Panier
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Vider Panier");
            }
        });

        menuPanier.add(itemAjouterPanier);
        menuPanier.add(itemSupprimerPanier);
        menuPanier.add(itemViderPanier);

        menuBar.add(menuPanier);

        // Menu Achat
        JMenu menuAchat = new JMenu("Achat");

        JMenuItem itemValiderPanier = new JMenuItem("Valider Panier");

        itemValiderPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Valider Panier
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Valider Panier");
            }
        });

        menuAchat.add(itemValiderPanier);

        menuBar.add(menuAchat);

        // Menu ?
        JMenu menuQuestion = new JMenu("?");

        JMenuItem itemAide = new JMenuItem("Aide");
        JMenuItem itemApropos = new JMenuItem("A propos");

        itemAide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Aide
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Aide");
            }
        });

        itemApropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu A propos
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action A propos");
            }
        });

        menuQuestion.add(itemAide);
        menuQuestion.add(itemApropos);

        menuBar.add(menuQuestion);

        setJMenuBar(menuBar);
    }

    private JPanel createNewClientPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(150, 30));
       // panel.setBackground(Color.ORANGE);

        JLabel labelNewClient = new JLabel("Nouveau");
        JCheckBox checkBoxNewClient = new JCheckBox();

        panel.add(labelNewClient);
        panel.add(checkBoxNewClient);

        return panel;
    }

    private JPanel createPanelWithLabelAndTextField(String label) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 30));
       // panel.setBackground(Color.WHITE);

        JLabel panelLabel = new JLabel(label);
        JTextField textField = new JTextField(10);

        panel.add(panelLabel);
        panel.add(textField);

        return panel;
    }

    private JPanel createConnectionPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 30));
        //panel.setBackground(Color.GREEN);

        JButton buttonConnection = new JButton("Connection");

        buttonConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action when the "Connection" button is clicked
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Connection");
            }
        });

        panel.add(buttonConnection);

        return panel;
    }

    public static void main(String[] args) {
        // Création de la vue de lancement
        VueLancement vueLancement = new VueLancement();

        // Définir une minuterie pour fermer la vue de lancement après 5 secondes
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fermer la vue de lancement après 5 secondes
                vueLancement.dispose();

                // Création du contrôleur et de la vue principale
                Controleur controleur = new Controleur();
                new VuePrincipale(controleur);
            }
        });

        // Démarrer la minuterie
        timer.setRepeats(false); // Ne répète pas la minuterie
        timer.start();
    }
}
