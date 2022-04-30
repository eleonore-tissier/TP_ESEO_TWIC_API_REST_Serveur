package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
		private static String user = "root";
		private static String password = "network";
	
		public DaoFactory() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch (Exception e){
			}
		}
		
		public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ville_france", user , password);
		}
		
		public VilleFranceImplementation getVilleFranceDao() {
			return new VilleFranceImplementation(this);
		}
}
