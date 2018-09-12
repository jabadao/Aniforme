package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAO;

public class AnimalDAOJdbcImpl implements DAO<Animal> {
	
	private static final String INSERT = "INSERT INTO Animaux(CodeAnimal, NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive) VALUES (?,?,?,?,?,?,?,?,?,?)";

	private static final String SELECT_ALL = "SELECT CodeAnimal, NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive, c.NomClient, c.PrenomClient, c.Adresse1, c.Adresse2, c.CodePostal, c.Ville, c.NumTel, c.Assurance, c.Email, c.Remarque, c.Archive FROM Animaux ani INNER JOIN Clients c ON ani.CodeClient = c.CodeClient INNER JOIN Race r ON ani.race = r.race AND ani.espece = r.espece";
	
	private static final String SELECT_BY_ID = SELECT_ALL + "WHERE CodeAnimal=?";
	
	private static final String SELECT_BY_NAME = SELECT_ALL + "WHERE NomAnimal=?";
	
	private static final String UPDATE = "UPDATE Animaux SET CodeAnimal=?, NomAnimal=?, Sexe=?, Couleur=?, Race=?, Espece=?, CodeClient=?, Tatouage=?, Antecedents=?, Archive=?";

	private static final String DELETE = "DELETE FROM Animaux WHERE CodeAnimal=?";
	
	
	@Override
	public void insert(Animal animal) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			objectToStatement(pStmt, animal);
			
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				animal.setCodeAnimal(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Animaux", e);
		}
		
	}

	
	@Override
	public Animal selectById(Animal animal) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, animal.getCodeAnimal());

			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				animal = map(rs);
			}
		} catch (SQLException e) {
			throw new DALException("Animaux", e);
		}
		return animal;
	}
	
	
	public Animal selectByName(String name) throws DALException {
		Animal animal = null;
		
		try (Connection cnx = DBConnection.getConnexion()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_NAME);
			pStmt.setString(1, name);
			
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				animal = map(rs);
			}
		} catch (SQLException e) {
			throw new DALException("Animaux", e);
		}
		
		return animal;
	}

	
	@Override
	public List<Animal> selectAll() throws DALException {
		List<Animal> animaux = new ArrayList<>();
		try (Connection cnx = DBConnection.getConnexion()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				animaux.add(map(rs));
			}
		} catch (SQLException e) {
			throw new DALException("Animaux", e);
		}
		return animaux;
	}

	
	@Override
	public void update(Animal animal) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			objectToStatement(pStmt, animal);
			pStmt.setInt(11, animal.getCodeAnimal());
			
			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Animaux", e);
		}
	}
	

	@Override
	public void delete(Animal animal) throws DALException {
		try (Connection cnx = DBConnection.getConnexion()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, animal.getCodeAnimal());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Animaux", e);
		}
		
	}
	
	
	public static Animal map (ResultSet rs) throws SQLException {
		Animal animal = null;
		
		int codeAnimal = rs.getInt("CodeAnimal");
		String nomAnimal = rs.getString("NomAnimal");
		String sexe = rs.getString("Sexe");
		String couleur = rs.getString("Couleur");
		Race race_espece = RaceDAOJdbcImpl.map(rs);
		Client codeClient = ClientDAOJdbcImpl.map(rs);
		String tatouage = rs.getString("Tatouage");
		String antecedent = rs.getString("Antecedent");		
		boolean archive = rs.getBoolean("Archive");
						
		animal = new Animal(codeAnimal, nomAnimal, sexe, couleur, race_espece, codeClient, tatouage, antecedent, archive);
		
		return animal;
	}
	
	
	private void objectToStatement(PreparedStatement pStmt, Animal animal) throws SQLException {
		pStmt.setInt(1, animal.getCodeAnimal());
		pStmt.setString(2, animal.getNomAnimal());
		pStmt.setString(3, animal.getSexe());
		pStmt.setString(4, animal.getCouleur());
		pStmt.setString(5, animal.getRace_espece().getRace());
		pStmt.setString(6, animal.getRace_espece().getEspece());
		pStmt.setInt(7, animal.getClient().getCodeClient());
		pStmt.setString(8, animal.getTatouage());
		pStmt.setString(9, animal.getAntecedent());
		pStmt.setBoolean(10, animal.isArchive());
	}

	
	
	

	






}
