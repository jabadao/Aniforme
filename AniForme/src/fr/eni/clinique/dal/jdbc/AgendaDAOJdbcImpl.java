/**
 * 
 */
package fr.eni.clinique.dal.jdbc;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eni Ecole
 * 
 */
public class AgendaDAOJdbcImpl implements DAO<Agenda> {
	
	private static final String INSERT = "INSERT INTO Agendas(CodeVeto, DateRdv, CodeAnimal)"
										+ " VALUES(?,?,?)";

	private static final String SELECT_ALL = "SELECT CodePers, Nom, MotPasse, Role, Archive, CodeVeto, DateRdv, CodeAnimal, NomAnimal, Sexe, Couleur, A2.Race, A2.Espece, CodeClient, Tatouage, Antecedents, A2.Archive "
            + "FROM Personnels P INNER JOIN Agendas A ON P.CodePers = A.CodeVeto INNER JOIN Animaux A2 ON A.CodeAnimal = A2.CodeAnimal INNER JOIN Races R ON A2.Race = R.Race AND A2.Espece = R.Espece";

	private static final String SELECT_BY_ID = SELECT_ALL + " WHERE CodeVeto=? AND DateRdv=? AND CodeAnimal=?";
	
	private final static String UPDATE = "UPDATE Agendas(CodeVeto, DateRdv, CodeAnimal)"
										+ " WHERE CodePers=?";
	
	private final static String DELETE = "DELETE FROM Agendas WHERE WHERE CodeVeto=? AND DateRdv=? AND CodeAnimal=?";
	
	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.AgendaDAO#insert(fr.eni.papeterie.bo.Agenda)
	 */
	@Override
	public void insert(Agenda agenda) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			//Préparation de la requête
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
			objectToStatement(pStmt, agenda);
			
			//Execution
			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Agendas", e);
		}
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.AgendaDAO#selectById(int)
	 */
	@Override
	public Agenda selectById(Agenda agenda) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			//Préparation de la requête
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, agenda.getPersonnel().getCodePers());
			pStmt.setDate(2, new Date(agenda.getDateRdv().getTime()));
			pStmt.setInt(3, agenda.getAnimal().getCodeAnimal());
			
			//Execution
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
                agenda = map(rs);
			}
		} catch (SQLException e) {
			throw new DALException("Agendas", e);
		}
		return agenda;
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.AgendaDAO#selectAll()
	 */
	@Override
	public List<Agenda> selectAll() throws DALException {
		List<Agenda> articles = new ArrayList<>();
		try (Connection cnx = DBConnection.getConnexion()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				articles.add(map(rs));
			}
		} catch (SQLException e) {
			throw new DALException("Agendas", e);
		}
		return articles;
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.AgendaDAO#update(fr.eni.papeterie.bo.Agenda)
	 */
	@Override
	public void update(Agenda agenda) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			//Préparation de la requête
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			objectToStatement(pStmt, agenda);
            pStmt.setInt(4, agenda.getPersonnel().getCodePers());
            pStmt.setDate(5, new Date(agenda.getDateRdv().getTime()));
			pStmt.setInt(6, agenda.getAnimal().getCodeAnimal());


			
			//Execution
			pStmt.executeUpdate();			
			
		} catch (SQLException e) {
			throw new DALException("Agendas", e);
		}
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.AgendaDAO#delete(int)
	 */
	@Override
	public void delete(Agenda agenda) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
            pStmt.setInt(1, agenda.getPersonnel().getCodePers());
            pStmt.setDate(2, new Date(agenda.getDateRdv().getTime()));
            pStmt.setInt(3, agenda.getAnimal().getCodeAnimal());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Agendas", e);
		}
	}

	public static Agenda map(ResultSet rs) throws SQLException {
		Agenda agenda = null;
		
		java.util.Date dateRdV = new java.util.Date(rs.getDate("CodeAgenda").getTime());
		Personnel personnel = PersonnelDAOJdbcImpl.map(rs);
		Animal animal = AnimalDAOJdbcImpl.map(rs);

		agenda = new Agenda(animal, personnel, dateRdV);

		return agenda;
	}
	
	private void objectToStatement(PreparedStatement pStmt, Agenda agenda) throws SQLException {
        pStmt.setInt(1, agenda.getPersonnel().getCodePers());
        pStmt.setDate(2, new Date(agenda.getDateRdv().getTime()));
        pStmt.setInt(3, agenda.getAnimal().getCodeAnimal());
    }
}
