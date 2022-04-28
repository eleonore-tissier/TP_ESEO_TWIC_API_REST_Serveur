package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	
		public DaoFactory() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch (Exception e){
			}
		}
		
		public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ville_france", "root", "network");
		}
		
		public VilleFranceImplementation getVilleFranceDao() {
			return new VilleFranceImplementation(this);
		}
}
