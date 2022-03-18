package com.dao;

public interface VilleFranceInterface {
	String afficherVille(String codePostal);
	String afficherVilles();
	void ajouterVille(String Code_commune_INSEE,
			String Nom_commune, 
			String Code_postal, 
			String Libelle_acheminement, 
			String Ligne_5, 
			String Latitude, 
			String Longitude);
}
