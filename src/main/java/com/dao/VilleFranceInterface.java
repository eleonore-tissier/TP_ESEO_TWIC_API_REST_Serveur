package com.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.beans.Ville;

public interface VilleFranceInterface {
	List<Ville> afficherVille(String codePostal) throws DaoException;
	List<Ville> afficherVilles() throws DaoException;
	void ajouterVille(String Code_commune_INSEE,
			String Nom_commune, 
			String Code_postal, 
			String Libelle_acheminement, 
			String Ligne_5, 
			String Latitude, 
			String Longitude) throws DaoException;
	void modifierVille(Map<String, String> newVille) throws SQLException, DaoException;
	void supprimerVille(String Code_commune_INSEE) throws DaoException;
}
