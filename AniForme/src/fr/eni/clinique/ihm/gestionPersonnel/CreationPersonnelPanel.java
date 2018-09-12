package fr.eni.clinique.ihm.gestionPersonnel;

import javax.swing.*;
import java.awt.*;

public class CreationPersonnelPanel extends JPanel {

    private JLabel labelNom;
    private JLabel labelMotDePasse;
    private JLabel labelRole;
    private JLabel labelArchive;

    private JTextField textNom;
    private JTextField textMotDePasse;
    private JTextField textRole;
    private JCheckBox checkBoxArchive;

    private JButton buttonSave;

    public CreationPersonnelPanel() {
        setLayout(new GridBagLayout());
        addComponentTo(this.getLabelNom(), this, 0, 0, 1, 1, 0.2);
        addComponentTo(this.getTextNom(), this, 1, 0, 1, 1, 0.8);
        addComponentTo(this.getLabelMotDePasse(), this, 0, 1, 1, 1, 0.2);
        addComponentTo(this.getTextMotDePasse(), this, 1, 1, 1, 1, 0.8);
        addComponentTo(this.getLabelRole(), this, 0, 2, 1, 1, 0.2);
        addComponentTo(this.getTextRole(), this, 1, 2, 1, 1, 0.8);
        addComponentTo(this.getLabelArchive(), this, 0, 3, 1, 1, 0.2);
        addComponentTo(this.getCheckBoxArchive(), this, 1, 3, 1, 1, 0.8);
        addComponentTo(this.getButtonSave(), this, 0, 6, 1, 1, 1);
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

    public JLabel getLabelNom() {
        if(this.labelNom == null){
            this.labelNom = new JLabel("Nom : ");
        }
        return labelNom;
    }

    public JLabel getLabelMotDePasse() {
        if(this.labelMotDePasse == null){
            this.labelMotDePasse = new JLabel("Mot de passe: ");
        }
        return labelMotDePasse;
    }

    public JLabel getLabelRole() {
        if(this.labelRole == null){
            this.labelRole = new JLabel("Rôle : ");
        }
        return labelRole;
    }

    public JLabel getLabelArchive() {
        if(this.labelArchive == null){
            this.labelArchive = new JLabel("Archive : ");
        }
        return labelArchive;
    }

    public JTextField getTextNom() {
        if(this.textNom == null){
            this.textNom = new JTextField();
        }
        return textNom;
    }

    public JTextField getTextMotDePasse() {
        if(this.textMotDePasse == null){
            this.textMotDePasse = new JTextField();
        }
        return textMotDePasse;
    }

    public JTextField getTextRole() {
        if(this.textRole == null){
            this.textRole = new JTextField();
        }
        return textRole;
    }

    public JCheckBox getCheckBoxArchive() {
        if(this.checkBoxArchive == null){
            this.checkBoxArchive = new JCheckBox();
        }
        return checkBoxArchive;
    }

    public JButton getButtonSave() {
        if(this.buttonSave == null){
            this.buttonSave = new JButton("Enregistrer");
        }
        return buttonSave;
    }
}
