package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VilleFranceImplementation implements VilleFranceInterface {
//	private DaoFactory connectionBdd;
//	
//	public CodePostalImplementation(DaoFactory connectionBdd) {
//		this.connectionBdd = connectionBdd;
//	}
	
	@Override
	public String afficherVille(String codePostal){
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        String nom = "";

        try {
            connexion = DaoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT Nom_commune FROM ville_france WHERE Code_postal = " + codePostal + ";");
            
            while (resultat.next()) {
                nom += "<p>" + resultat.getString("Nom_commune") + "</p>";
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
            }
        }
        return nom;
	}
	
	@Override
	public String afficherVilles() {
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        String villes = "";

        try {
            connexion = DaoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT Nom_commune FROM ville_france;");
            
            while (resultat.next()) {
                villes += "<p>" + resultat.getString("Nom_commune") + "</p>";
                
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
            }
        }
        return villes;
	}
	
	@Override
	public void ajouterVille(String Code_commune_INSEE,
			String Nom_commune, 
			String Code_postal, 
			String Libelle_acheminement, 
			String Ligne_5, 
			String Latitude, 
			String Longitude) {
		System.out.println("Je suis làà !!");
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = DaoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO ville_france(Code_commune_INSEE,Nom_commune,Code_postal,Libelle_acheminement,Ligne_5,Latitude,Longitude) VALUES(?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, Code_commune_INSEE);
            preparedStatement.setString(2, Nom_commune);
            preparedStatement.setString(3, Code_postal);
            preparedStatement.setString(4, Libelle_acheminement);
            preparedStatement.setString(5, Ligne_5);
            preparedStatement.setString(6, Latitude);
            preparedStatement.setString(7, Longitude);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
            }
        }
	}
}
