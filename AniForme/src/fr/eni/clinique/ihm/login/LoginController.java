package fr.eni.clinique.ihm.login;

import javax.swing.JDialog;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Observable;
import fr.eni.clinique.bo.Observable.Observer;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.AppliTestIHM;

public class LoginController {

	private static LoginController _instance;
	private Personnel CurrentPersonnel;

	private Observable<Personnel> currentPersonnel = new Observable<>();
	private PersonnelManager manager; 
	private JDialog jdog;
	
	private LoginController(){
		this.manager = new PersonnelManager();
		this.setCurrentPersonnel(new Personnel());
	}

	public static LoginController getInstance() {
		if(_instance == null){
			_instance = new LoginController();
		}
		return _instance;
	}

	public void registerToCurrentPersonnel(Observer obs) {
		this.currentPersonnel.registerObserver(obs);
		
	}


	public JDialog getJdog() {
		if (this.jdog == null) {
			this.jdog = new JDialog();
		}
		
		return this.jdog;
	}

	public Personnel getCurrentPersonnel() {
		return CurrentPersonnel;
	}

	public void setCurrentPersonnel(Personnel currentPersonnel) {

	    CurrentPersonnel = currentPersonnel;
        if(currentPersonnel != null && currentPersonnel.getCodePers() > 0) {
            AppliTestIHM.mainFrame.reloadCurrentPersonnel();
        }
	}

	public void connexion(String nom, String motPasse) {

	    String erreurs = "";
		try {
			Personnel resultat = this.manager.getPersonnel(nom);

			if (resultat == null || resultat.getCodePers() <= 0) {
                throw new BLLException("Erreur: Le rêsultat n'existe pas");
            }

			if (resultat.getNom().equals(nom)) {
				System.out.println("Même nom");
			} else {
                erreurs += "Erreur de récupèration\n";
			}
			
			if (resultat.getMotPasse().equals(motPasse)) {
				System.out.println("Même mot de passe");
			} else {
                erreurs += "Mauvaise concordance\n";
			}
            System.out.println(resultat);

			if(!erreurs.equals("")){
			    AppliTestIHM.showError("Erreur", erreurs);
            }
			if(resultat.getCodePers() > 0){
                AppliTestIHM.loginFrame.setVisible(false);
                AppliTestIHM.mainFrame.setVisible(true);
                this.setCurrentPersonnel(resultat);
            }
		} catch (BLLException e) {
		    e.printStackTrace();
			AppliTestIHM.showError("Erreur BLL", e.getMessage());
		}
		
	}
	
	

}
