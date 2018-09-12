/**
 * 
 */
package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DAO;
import fr.eni.clinique.dal.DALException;

/**
 * @author Eni Ecole
 * 
 */
public class PersonnelDAOJdbcImpl implements DAO<Personnel> {
	
	private static final String INSERT = "INSERT INTO Personnels(reference, marque, "
										+ "designation, prixUnitaire, qteStock, grammage, couleur, type)"
										+ " VALUES(?,?,?,?,?,?,?,?)";

	private static final String SELECT_ALL = "SELECT CodePers, Nom, MotPasse, Role, Archive FROM Personnels";

	private static final String SELECT_BY_ID = SELECT_ALL + " WHERE CodePers=?";

	private static final String SELECT_BY_NAME = SELECT_ALL + " WHERE Nom=?";
	
	private final static String UPDATE = "UPDATE Personnels SET CodePers=?, Nom=?, "
										+ "MotPasse=?, Role=?, Archive=?"
										+ " WHERE CodePers=?";
	
	private final static String DELETE = "DELETE FROM Personnels WHERE CodePers=?";
	
	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.PersonnelDAO#insert(fr.eni.papeterie.bo.Personnel)
	 */
	@Override
	public void insert(Personnel personnel) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			//Préparation de la requête
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			objectToStatement(pStmt, personnel);
			
			//Execution
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				personnel.setCodePers(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Personnels", e);
		}
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.PersonnelDAO#selectById(int)
	 */
	@Override
	public Personnel selectById(Personnel personnel) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			//Préparation de la requête
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, personnel.getCodePers());
			
			//Execution
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
                personnel = map(rs);
			}
		} catch (SQLException e) {
			throw new DALException("Personnels", e);
		}
		return personnel;
	}

    public Personnel selectByName(Personnel _personnel) throws DALException {
		Personnel personnel = null;
        try (Connection cnx = DBConnection.getConnexion()){
            //Préparation de la requête
            PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_NAME);
            pStmt.setString(1, _personnel.getNom());

            //Execution
            ResultSet rs = pStmt.executeQuery();
            if(rs.next()) {
                personnel = map(rs);
            }
System.out.println(personnel);
            if(personnel == null) {
            	throw new SQLException("Aucun personnel trouvé avec le nom " + _personnel.getNom());
			}
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new DALException("Personnels", e);
        }
        return personnel;
    }

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.PersonnelDAO#selectAll()
	 */
	@Override
	public List<Personnel> selectAll() throws DALException {
		List<Personnel> articles = new ArrayList<>();
		try (Connection cnx = DBConnection.getConnexion()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				articles.add(map(rs));
			}
		} catch (SQLException e) {
			throw new DALException("Personnels", e);
		}
		return articles;
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.PersonnelDAO#update(fr.eni.papeterie.bo.Personnel)
	 */
	@Override
	public void update(Personnel personnel) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			//Préparation de la requête
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			objectToStatement(pStmt, personnel);
			pStmt.setInt(6, personnel.getCodePers());
			
			//Execution
			pStmt.executeUpdate();			
			
		} catch (SQLException e) {
			throw new DALException("Personnels", e);
		}
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.PersonnelDAO#delete(int)
	 */
	@Override
	public void delete(Personnel personnel) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, personnel.getCodePers());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Personnels", e);
		}
	}

	public static Personnel map(ResultSet rs) throws SQLException {
		Personnel personnel = null;
		
		int codePers = rs.getInt("CodePers");
		String nom = rs.getString("Nom");
		String motPasse = rs.getString("MotPasse");
		String role = rs.getString("Role");
		Boolean archive = rs.getBoolean("Archive");

		personnel = new Personnel(codePers, nom, motPasse, role, archive);

		return personnel;
	}
	
	private void objectToStatement(PreparedStatement pStmt, Personnel personnel) throws SQLException {
        pStmt.setInt(1, personnel.getCodePers());
        pStmt.setString(2, personnel.getNom());
        pStmt.setString(3, personnel.getMotPasse());
        pStmt.setString(4, personnel.getRole());
        pStmt.setBoolean(5, personnel.isArchive());
    }
}
