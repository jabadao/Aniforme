/**
 * 
 */
package fr.eni.clinique.dal.jdbc;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eni Ecole
 * 
 */
public class ClientDAOJdbcImpl implements DAO<Client> {
	
	private static final String INSERT = "INSERT INTO Clients(CodeClient, NomClient, PrenomClient, Adresse1, Adresse2,"
										+ "CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive)"
										+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String SELECT_ALL = "SELECT CodeClient, NomClient, PrenomClient, Adresse1, Adresse2, CodePostal," +
			" Ville, NumTel, Assurance, Email, Remarque, Archive FROM Clients";

	private static final String SELECT_BY_ID = SELECT_ALL + " WHERE CodeClient=?";

	private static final String SELECT_BY_NAME = SELECT_ALL + " WHERE NomClient=? AND PrenomClient=?";
	
	private final static String UPDATE = "UPDATE Clients SET CodeClient=?, NomClient=?, PrenomClient=?, Adresse1=?, Adresse2=?,"
										+ " CodePostal=?, Ville=?, NumTel=?, Assurance=?, Email=?, Remarque=?, Archive=?"
										+ " WHERE CodePers=?";
	
	private final static String DELETE = "DELETE FROM Clients WHERE CodeClient=?";
	
	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.ClientDAO#insert(fr.eni.papeterie.bo.Client)
	 */
	@Override
	public void insert(Client client) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			//Pr�paration de la requ�te
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			objectToStatement(pStmt, client);
			
			//Execution
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				client.setCodeClient(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Clients", e);
		}
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.ClientDAO#selectById(int)
	 */
	@Override
	public Client selectById(Client client) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			//Pr�paration de la requ�te
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, client.getCodeClient());
			
			//Execution
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
                client = map(rs);
			}
		} catch (SQLException e) {
			throw new DALException("Clients", e);
		}
		return client;
	}

    public Client selectByName(String name) throws DALException {
        Client client = null;
        try (Connection cnx = DBConnection.getConnexion()){
            //Pr�paration de la requ�te
            PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_NAME);
            pStmt.setString(1, name);

            //Execution
            ResultSet rs = pStmt.executeQuery();
            if(rs.next()) {
                client = map(rs);
            }
        } catch (SQLException e) {
            throw new DALException("Clients", e);
        }
        return client;
    }

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.ClientDAO#selectAll()
	 */
	@Override
	public List<Client> selectAll() throws DALException {
		List<Client> clients = new ArrayList<>();
		try (Connection cnx = DBConnection.getConnexion()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				clients.add(map(rs));
			}
		} catch (SQLException e) {
			throw new DALException("Clients", e);
		}
		return clients;
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.ClientDAO#update(fr.eni.papeterie.bo.Client)
	 */
	@Override
	public void update(Client client) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			//Pr�paration de la requ�te
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			objectToStatement(pStmt, client);
			pStmt.setInt(9, client.getCodeClient());
			
			//Execution
			pStmt.executeUpdate();			
			
		} catch (SQLException e) {
			throw new DALException("Clients", e);
		}
	}

	/* (non-Javadoc)
	 * @see fr.eni.papeterie.dal.jdbc.ClientDAO#delete(int)
	 */
	@Override
	public void delete(Client client) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()){
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, client.getCodeClient());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Clients", e);
		}
	}

	public static Client map(ResultSet rs) throws SQLException {
		Client client = null;
		
		int codeClient = rs.getInt("CodeClient");
		String nomClient = rs.getString("NomClient");
		String prenomClient = rs.getString("PrenomClient");
		String adresse1 = rs.getString("Adresse1");
        String adresse2 = rs.getString("Adresse2");
        String codePostal = rs.getString("CodePostal");
        String ville = rs.getString("Ville");
        String numTel = rs.getString("NumTel");
        String assurance = rs.getString("Assurance");
        String email = rs.getString("Email");
        String remarque = rs.getString("Remarque");
        Boolean archive = rs.getBoolean("Archive");

		client = new Client(codeClient, nomClient, prenomClient, adresse1, adresse2, codePostal, ville, numTel,
                assurance, email, remarque, archive);

		return client;
	}
	
	private void objectToStatement(PreparedStatement pStmt, Client client) throws SQLException {
        pStmt.setInt(1, client.getCodeClient());
        pStmt.setString(2, client.getNomClient());
        pStmt.setString(3, client.getPrenomClient());
        pStmt.setString(4, client.getAdresse1());
        pStmt.setString(5, client.getAdresse2());
        pStmt.setString(6, client.getCodePostal());
        pStmt.setString(7, client.getVille());
        pStmt.setString(8, client.getNumTel());
        pStmt.setString(9, client.getAssurance());
        pStmt.setString(10, client.getEmail());
        pStmt.setString(11, client.getRemarque());
        pStmt.setBoolean(12, client.isArchive());
    }
}
