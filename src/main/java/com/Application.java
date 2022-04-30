package com;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dao.VilleFranceImplementation;

@SpringBootApplication
public class Application {
	
	private static Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.log(Level.INFO, "Application Start !");
	}
}
