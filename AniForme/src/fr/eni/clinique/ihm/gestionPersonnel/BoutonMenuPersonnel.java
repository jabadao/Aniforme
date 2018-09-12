package fr.eni.clinique.ihm.gestionPersonnel;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BoutonMenuPersonnel extends JPanel {

	private JButton bttNew;
	private JButton bttDelete;
	private JButton bttReset;

	public BoutonMenuPersonnel() {
		add(getBttNew());
		add(getBttDelete());
		add(getBttReset());
	}

	private JButton getBttNew() {
		if(bttNew == null) {
			bttNew = new JButton("Ajouter");
		}

		return bttNew;

	}

	private JButton getBttDelete() {
		if(bttDelete == null) {
			bttDelete = new JButton("Supprimer");
		}

		return bttDelete;

	}

	private JButton getBttReset() {
		if (bttReset == null) {
			bttReset = new JButton("Réinitaliser");
		}
		
		return bttReset;

	}



}
