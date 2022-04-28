package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.beans.Ville;

public class VilleFranceImplementation implements VilleFranceInterface {
	private DaoFactory connexionBdd;
	
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
						Ville ville = new Ville(resultat.getString("Code_commune_INSEE"),
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
			e.printStackTrace();
            throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
			}
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
						Ville ville = new Ville(resultat.getString("Code_commune_INSEE"),
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
			e.printStackTrace();
            throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
			}
		}
		return listVille;
	}

	@Override
	public void ajouterVille(String Code_commune_INSEE, String Nom_commune, String Code_postal,
			String Libelle_acheminement, String Ligne_5, String Latitude, String Longitude) throws DaoException {
		Connection connexion = null;

		try {
			connexion = connexionBdd.getConnection();
			try(PreparedStatement preparedStatement = connexion.prepareStatement(
					"INSERT INTO ville_france(Code_commune_INSEE,Nom_commune,Code_postal,Libelle_acheminement,Ligne_5,Latitude,Longitude) VALUES(?, ?, ?, ?, ?, ?, ?);")){
				preparedStatement.setString(1, Code_commune_INSEE);
				preparedStatement.setString(2, Nom_commune);
				preparedStatement.setString(3, Code_postal);
				preparedStatement.setString(4, Libelle_acheminement);
				preparedStatement.setString(5, Ligne_5);
				preparedStatement.setString(6, Latitude);
				preparedStatement.setString(7, Longitude);

				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
            throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
			}
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
				if(!key.equals("Code_commune_INSEE")) {
					parametres += ", "+ key + "='" + newVille.get(key) + "'";
				}
			}
			parametres = parametres.replaceFirst(",", "");
			
			try(PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE ville_france SET"
					+ parametres
					+ " WHERE Code_commune_INSEE='"
					+ newVille.get("Code_commune_INSEE")
					+ "';")){
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
            throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
			}
		}
		
	}
	
	@Override
	public void supprimerVille(String Code_commune_INSEE) throws DaoException {
		Connection connexion = null;
		
		try {
			connexion = connexionBdd.getConnection();
			
			try(PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM ville_france WHERE Code_commune_INSEE=?;")){
				preparedStatement.setString(1, Code_commune_INSEE);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
            throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
			}
		}
	}
}
