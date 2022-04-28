package com.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.beans.Ville;

public interface VilleFranceInterface {
	List<Ville> afficherVille(String codePostal) throws DaoException;
	List<Ville> afficherVilles() throws DaoException;
	void ajouterVille(String codeCommuneInsee,
			String nomCommune, 
			String codePostal, 
			String libelleAcheminement, 
			String ligne5, 
			String latitude, 
			String longitude) throws DaoException;
	void modifierVille(Map<String, String> newVille) throws SQLException, DaoException;
	void supprimerVille(String codeCommuneInsee) throws DaoException;
}
