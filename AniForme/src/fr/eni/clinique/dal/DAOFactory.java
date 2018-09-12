package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.jdbc.AgendaDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.AnimalDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.ClientDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.PersonnelDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.RaceDAOJdbcImpl;

public class DAOFactory {

	public static DAO<Personnel> getPersonnelDAO() { return new PersonnelDAOJdbcImpl ();}

	public static DAO<Client> getClientDAO() { return new ClientDAOJdbcImpl (); }

	public static DAO<Agenda> getAgendaDAO() { return  new AgendaDAOJdbcImpl(); }
	
	public static DAO<Race> getRaceDAO() { return new RaceDAOJdbcImpl(); }

	public static DAO<Animal> getAnimalDAO() { return new AnimalDAOJdbcImpl(); }
}
