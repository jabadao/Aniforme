package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DAO;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.jdbc.PersonnelDAOJdbcImpl;

import java.util.List;

public class PersonnelManager {
    private DAO<Personnel> personnelDAO;

    public PersonnelManager(){
        this.personnelDAO = DAOFactory.getPersonnelDAO();
    }

    public Personnel getPersonnel(int codePers) throws BLLException {
    	Personnel personnel = new Personnel();
    	personnel.setCodePers(codePers);
    	
        try{
             return this.personnelDAO.selectById(personnel);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Récupèration du Personnel impossible");
        }
    }

    public Personnel getPersonnel(String name) throws BLLException {
    	Personnel personnel = new Personnel();
    	personnel.setNom(name);
        try {
            personnel = ((PersonnelDAOJdbcImpl)this.personnelDAO).selectByName(personnel);
        } catch (DALException e) {
        	e.printStackTrace();
            throw new BLLException("Récupèration du Personnel impossible");
        }
        return personnel;
    }

    public List<Personnel> getPersonnels() throws BLLException {
        try{
            return personnelDAO.selectAll();
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Récupèration de la liste des personnels impossible");
        }
    }

    public void updatePersonnel(Personnel personnel) throws BLLException {
        try {
            this.personnelDAO.update(personnel);
        } catch (DALException e) {
            throw new BLLException("Mise à jour du personnel impossible");
        }
    }

    public void deletePersonnel(Personnel personnel) throws BLLException {
        try {
            this.personnelDAO.delete(personnel);
        } catch (DALException e) {
            throw new BLLException("Suppression impossible");
        }
    }

    public void addPersonnel(Personnel personnel) throws BLLException {
        try {
            this.personnelDAO.insert(personnel);
        } catch (DALException e){
            throw new BLLException("Ajout du personnel impossible");
        }
    }

    public void validationPersonnel(Personnel personnel) throws BLLException {
        BLLException exceptions = new BLLException();
        String needed = "Obligatoire";
        if(personnel == null) {
            throw new BLLException("Personnel null");
        }

        if(personnel.getCodePers() >= 0) {
            exceptions.ajouterException(new ParameterException("CodePers", "Doit être supérieur ou égale � zéro"));
        }

        if(personnel.getNom() == null ||
                personnel.getNom().trim().length() == 0) {
            exceptions.ajouterException(new ParameterException("Nom", needed));
        }

        if(personnel.getMotPasse() == null ||
                personnel.getMotPasse().trim().length() == 0) {
            exceptions.ajouterException(new ParameterException("Mot de passe", needed));
        }

        if(personnel.getRole() == null ||
                personnel.getRole().trim().length() == 0) {
            exceptions.ajouterException(new ParameterException("Role", needed));
        }

        if(exceptions.size() > 0){
            throw exceptions;
        }
    }
}
