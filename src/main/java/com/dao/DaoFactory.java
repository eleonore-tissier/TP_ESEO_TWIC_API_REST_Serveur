package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoFactory {
		private static Logger logger = Logger.getLogger(DaoFactory.class.getName());
	
		public DaoFactory() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch (Exception e){
				logger.log(Level.WARNING, e.getMessage());
			}
		}
		
		public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ville_france", "root" , "network");
		}
		
		public VilleFranceImplementation getVilleFranceDao() {
			return new VilleFranceImplementation(this);
		}
}
