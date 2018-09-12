package fr.eni.clinique.ihm.login;

import javax.swing.SwingUtilities;

public class AppliTestIHM {

	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				EcranLogin frame = new EcranLogin();
				frame.setVisible(true);
			}
		});
	}
}
