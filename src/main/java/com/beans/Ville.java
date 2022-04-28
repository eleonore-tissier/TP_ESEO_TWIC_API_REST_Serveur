package com.beans;

public class Ville {
	private String Code_commune_INSEE;
	private String Nom_commune;
	private String Code_postal;
	private String Libelle_acheminement;
	private String Ligne_5;
	private String Latitude;
	private String Longitude;
	
	public Ville() {
		super();
	}
	
	public Ville(String Code_commune_INSEE, String Nom_commune, String Code_postal, String Libelle_acheminement,
			String Ligne_5, String Latitude, String Longitude) {
		super();
		this.Code_commune_INSEE = Code_commune_INSEE;
		this.Nom_commune = Nom_commune;
		this.Code_postal = Code_postal;
		this.Libelle_acheminement = Libelle_acheminement;
		this.Ligne_5 = Ligne_5;
		this.Latitude = Latitude;
		this.Longitude = Longitude;
	}

	public String getCode_commune_INSEE() {
		return this.Code_commune_INSEE;
	}

	public void setCode_commune_INSEE(String code_commune_INSEE) {
		this.Code_commune_INSEE = code_commune_INSEE;
	}

	public String getNom_commune() {
		return this.Nom_commune;
	}

	public void setNom_commune(String nom_commune) {
		this.Nom_commune = nom_commune;
	}

	public String getCode_postal() {
		return this.Code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.Code_postal = code_postal;
	}

	public String getLibelle_acheminement() {
		return this.Libelle_acheminement;
	}

	public void setLibelle_acheminement(String libelle_acheminement) {
		this.Libelle_acheminement = libelle_acheminement;
	}

	public String getLigne_5() {
		return this.Ligne_5;
	}

	public void setLigne_5(String ligne_5) {
		this.Ligne_5 = ligne_5;
	}

	public String getLatitude() {
		return this.Latitude;
	}

	public void setLatitude(String latitude) {
		this.Latitude = latitude;
	}

	public String getLongitude() {
		return this.Longitude;
	}

	public void setLongitude(String longitude) {
		this.Longitude = longitude;
	}
}
