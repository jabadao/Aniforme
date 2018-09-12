package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAO;

public class RaceDAOJdbcImpl implements DAO<Race> {
	
	
	private static final String INSERT = "INSERT into Races(race, espece) VALUES (?, ?)";
	
	private static final String SELECT_ALL = "SELECT * FROM Races";
	
	private static final String UPDATE = "UPDATE Races SET Race=?, Espece=? WHERE Race=? AND Espece=?";
	
	private static final String DELETE = "DELETE FROM Races Where Race=? AND Espece=?";
	
	private static final String SELECT_BY_ID = SELECT_ALL + "WHERE Race=? AND Espece=?";

	
	
	@Override
	public void insert(Race raceObject) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
			objectToStatement(pStmt, raceObject);
			
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException("Race", e);
		}
		
	}

	
	@Override
	public Race selectById(Race raceObject) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setString(1, raceObject.getRace());
			pStmt.setString(2, raceObject.getEspece());
			
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				raceObject = map(rs);
			}
		} catch (SQLException e) {
			throw new DALException("Races", e);
		}
		return raceObject;
	}

	
	
	@Override
	public List<Race> selectAll() throws DALException {
		List<Race> races = new ArrayList<>();
		try (Connection cnx = DBConnection.getConnexion()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				races.add(map(rs));
			}
		} catch (SQLException e) {
			throw new DALException("Races", e);
		}
		return races;
	}
	
	

	@Override
	public void update(Race raceObject) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()) {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			objectToStatement(pStmt, raceObject);
			
			pStmt.setString(3, raceObject.getRace());
			pStmt.setString(4, raceObject.getEspece());
			
			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Races", e);
		}
		
	}
	

	@Override
	public void delete(Race raceObject) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()) {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			
			pStmt.setString(1, raceObject.getRace());
			pStmt.setString(2, raceObject.getEspece());
			
			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Races", e);
		}
		
	}

	
	
	
	
	public static Race map(ResultSet rs) throws SQLException {
		Race raceObject = null;
		
		String race = rs.getString("Race");
		String espece = rs.getString("Espece");
		
		raceObject = new Race(race, espece);
		
		return raceObject;
	}
	
	
	private void objectToStatement(PreparedStatement pStmt, Race raceObject) throws SQLException {
		pStmt.setString(1, raceObject.getRace());
		pStmt.setString(2, raceObject.getEspece());
	}
	
	
	
	
	
	

}
