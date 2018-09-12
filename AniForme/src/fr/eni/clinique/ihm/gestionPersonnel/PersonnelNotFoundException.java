package fr.eni.clinique.ihm.gestionPersonnel;

public class PersonnelNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonnelNotFoundException() {
		super("Personnel introuvable");
	}

}
