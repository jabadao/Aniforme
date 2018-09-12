package fr.eni.clinique.ihm.gestionPersonnel;

import java.util.List;

import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Observable;
import fr.eni.clinique.bo.Observable.Observer;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bll.BLLException;

public class PersonnelController {

	private List<Personnel> listePersonnels;
	private int indexPersonnel;

	private Observable<Personnel> currentPersonnel = new Observable<>();

	private PersonnelManager manager;
	private static PersonnelController instance = null;

	private PersonnelController() throws BLLException { 
		manager = new PersonnelManager();

		listePersonnels = manager.getPersonnels();
		indexPersonnel = 0;
	}


	public static PersonnelController getInstance() throws BLLException {
		if (instance == null) {
			instance = new PersonnelController();
		}

		return instance;
	}
	

	public void first() throws PersonnelNotFoundException {
		setPersonnel(0);		
	}

	public void setPersonnel(int index) throws PersonnelNotFoundException {
		if(index >=0 && index < listePersonnels.size()) {
			indexPersonnel = index;
			currentPersonnel.set(listePersonnels.get(indexPersonnel));
		} else {
			throw new PersonnelNotFoundException();
		}
	}

	public void registerToCurrentPersonnel(Observer obs) {
		currentPersonnel.registerObserver(obs);
	}


}
