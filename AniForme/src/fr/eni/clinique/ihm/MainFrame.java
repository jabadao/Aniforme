package fr.eni.clinique.ihm;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.gestionPersonnel.CreationPersonnelPanel;
import fr.eni.clinique.ihm.login.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.net.URL;

public class MainFrame extends JFrame{
    private LoginController loginController;

    private JPanel mainPanel;
    private JLabel labelConnectedPersonnelName;
    private JMenuBar menuBar;
    private JMenu fichiers;
    private JMenuItem fichier_quit;
    private JMenu gestionPersonnel;

    private JDialog creationView;
    private JButton addTest;

    URL iconURL;
    ImageIcon icon;

    public MainFrame() {
        this.loginController = LoginController.getInstance();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quitter();
            }
        });
        setSize(800, 400);
        setLocation(600, 250);

        this.iconURL = getClass().getResource("ressources/ico_veto.png");
        this.icon = new ImageIcon(iconURL);

        setContentPane(this.getMainPanel());
        setIconImage(icon.getImage());
        setResizable(true);
        setTitle("Clinique Vétérinaire");
        setJMenuBar(this.getMenu());
    }

    public JPanel getMainPanel() {
        if(this.mainPanel == null) {
            this.mainPanel = new JPanel(new GridBagLayout());
            addComponentTo(this.getAddTest(), this.mainPanel, 0, 0, 1, 1, 1);
            addComponentTo(this.getLabelConnectedPersonnelName(), this.mainPanel, 0, 2, 1, 1, 1);
        }
        return mainPanel;
    }

    public void reloadCurrentPersonnel() {
        Personnel currentPersonnel = this.loginController.getCurrentPersonnel();
        this.getLabelConnectedPersonnelName().setText("Utilisateur: " + currentPersonnel.getNom());
    }

    public JLabel getLabelConnectedPersonnelName() {
        if(this.labelConnectedPersonnelName == null) {
            this.labelConnectedPersonnelName = new JLabel("Aucun utilisateur connecté");
            this.labelConnectedPersonnelName.setForeground(Color.GRAY);
        }
        return labelConnectedPersonnelName;
    }

    public JMenuBar getMenu() {
        if( this.menuBar == null){
            this.menuBar = new JMenuBar();
            this.menuBar.add(this.getFichiers());
            this.menuBar.add(this.getGestionPersonnel());
        }
        return this.menuBar;
    }

    public JMenu getFichiers() {
        if(this.fichiers == null){
            this.fichiers = new JMenu("Fichiers");
            this.fichiers.add(this.getFichier_quit());
        }
        return fichiers;
    }

    public JMenu getGestionPersonnel() {
        if(this.gestionPersonnel == null) {
            this.gestionPersonnel = new JMenu("Gestion du personnel");
        }
        return gestionPersonnel;
    }

    public JDialog getCreationView() {
        if(this.creationView == null){
            this.creationView = new JDialog();
            this.creationView.setContentPane(new CreationPersonnelPanel());
            this.creationView.setVisible(true);
            this.creationView.setSize(800, 400);
            this.creationView.setLocation(600, 250);

            this.creationView.setIconImage(icon.getImage());
            this.creationView.setResizable(false);
            this.creationView.setTitle("Création personnel");
            this.creationView.setJMenuBar(this.getMenu());
        }
        return creationView;
    }

    public JMenuItem getFichier_quit() {
        if(this.fichier_quit == null) {
            this.fichier_quit = new JMenuItem("Quitter");
            this.fichier_quit.addActionListener(e -> quitter());
        }
        return fichier_quit;
    }

    public void quitter(){
        int reply = JOptionPane.showConfirmDialog(this.mainPanel, "Êtes-vous certain de vouloir quitter ?", "Déconnexion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(reply == JOptionPane.YES_OPTION){
            AppliTestIHM.loginFrame.reset();
            this.getCreationView().setVisible(false);
            JOptionPane.showMessageDialog(this.mainPanel, "Vous êtes déconnecté", "Déconnecté", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public JButton getAddTest() {
        if(this.addTest == null){
            this.addTest = new JButton("+");
            this.addTest.addActionListener(e -> {
                this.getCreationView().setVisible(true);
            });
        }
        return addTest;
    }

    private void addComponentTo(JComponent component, JPanel panel,
                                int x, int y, int width, int height,
                                double weightX) {
        addComponentTo(component, panel, x, y, width, height, weightX, true);
    }

    private void addComponentTo(JComponent component, JPanel panel,
                                int x, int y, int width, int height,
                                double weightX, boolean fillHorizontal) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = weightX;
        if(fillHorizontal) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
        }
        gbc.insets = new Insets(7, 10, 5, 10);
        panel.add(component, gbc);
    }
}
