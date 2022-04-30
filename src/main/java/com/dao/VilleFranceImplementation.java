package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.*;

import com.beans.Ville;

public class VilleFranceImplementation implements VilleFranceInterface {
	private static Logger logger = Logger.getLogger(VilleFranceImplementation.class.getName());
	
	private DaoFactory connexionBdd;
	private static final String DAOEXCEPTION = "Impossible de communiquer avec la base de données";
	
	private static final String CODEINSEE = "Code_commune_INSEE";
	
	public VilleFranceImplementation(DaoFactory connexionBdd) {
		this.connexionBdd = connexionBdd;
	}
	
	@Override
	public List<Ville> afficherVille(String nom) throws DaoException {
		Connection connexion = null;
		
		List<Ville> listVille = new ArrayList<>();
		
		try {
			connexion = connexionBdd.getConnection();
			try(Statement statement = connexion.createStatement()){
				try(ResultSet resultat = statement.executeQuery("SELECT * FROM ville_france WHERE Nom_commune = " + nom + ";")){
					while (resultat.next()) {
						Ville ville = new Ville(resultat.getString(CODEINSEE),
								resultat.getString("Nom_commune"),
								resultat.getString("Code_postal"),
								resultat.getString("Libelle_acheminement"),
								resultat.getString("Ligne_5"),
								resultat.getString("Latitude"),
								resultat.getString("Longitude"));
						listVille.add(ville);
					}
				}
			}			
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
            throw new DaoException(DAOEXCEPTION);
		}
		return listVille;
	}

	@Override
	public List<Ville> afficherVilles() throws DaoException {
		Connection connexion = null;
		
		List<Ville> listVille = new ArrayList<>();

		try {
			connexion = connexionBdd.getConnection();
			try(Statement statement = connexion.createStatement()){
				try(ResultSet resultat = statement.executeQuery("SELECT * FROM ville_france;")){
					while (resultat.next()) {
						Ville ville = new Ville(resultat.getString(CODEINSEE),
								resultat.getString("Nom_commune"),
								resultat.getString("Code_postal"),
								resultat.getString("Libelle_acheminement"),
								resultat.getString("Ligne_5"),
								resultat.getString("Latitude"),
								resultat.getString("Longitude"));
						listVille.add(ville);
						
					}
				}
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
            throw new DaoException(DAOEXCEPTION);
		}
		return listVille;
	}

	@Override
	public void ajouterVille(String codeCommuneInsee, String nomCommune, String codePostal, String libelleAcheminement, 
			String ligne5, String latitude,	String longitude) throws DaoException {
		Connection connexion = null;

		try {
			connexion = connexionBdd.getConnection();
			try(PreparedStatement preparedStatement = connexion.prepareStatement(
					"INSERT INTO ville_france(Code_commune_INSEE,Nom_commune,Code_postal,Libelle_acheminement,Ligne_5,Latitude,Longitude) VALUES(?, ?, ?, ?, ?, ?, ?);")){
				preparedStatement.setString(1, codeCommuneInsee);
				preparedStatement.setString(2, nomCommune);
				preparedStatement.setString(3, codePostal);
				preparedStatement.setString(4, libelleAcheminement);
				preparedStatement.setString(5, ligne5);
				preparedStatement.setString(6, latitude);
				preparedStatement.setString(7, longitude);

				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
            throw new DaoException(DAOEXCEPTION);
		}
	}

	@Override
	public void modifierVille(Map<String, String> newVille) throws DaoException {
		Connection connexion = null;
		
		try {
			connexion = connexionBdd.getConnection();
			String parametres = "";
			Object[] keys = newVille.keySet().toArray();
			for (Object key : keys) {
				if(!key.equals(CODEINSEE)) {
					parametres += ", "+ key + "='" + newVille.get(key) + "'";
				}
			}
			parametres = parametres.replaceFirst(",", "");
			
			try(PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE ville_france SET"
					+ parametres
					+ " WHERE Code_commune_INSEE='"
					+ newVille.get(CODEINSEE)
					+ "';")){
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
            throw new DaoException(DAOEXCEPTION);
		}
		
	}
	
	@Override
	public void supprimerVille(String codeCommuneInsee) throws DaoException {
		Connection connexion = null;
		
		try {
			connexion = connexionBdd.getConnection();
			
			try(PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM ville_france WHERE Code_commune_INSEE=?;")){
				preparedStatement.setString(1, codeCommuneInsee);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
            throw new DaoException(DAOEXCEPTION);
		}
	}
}
