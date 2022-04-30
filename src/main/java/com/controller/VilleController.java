package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Ville;
import com.dao.DaoException;
import com.dao.DaoFactory;
import com.dao.VilleFranceImplementation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class VilleController {

	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public List<Ville> get(@RequestParam(required  = false, value="nom") String nom) throws DaoException {
		DaoFactory connexionBdd = new DaoFactory();
		VilleFranceImplementation villeFranceImpl = new VilleFranceImplementation(connexionBdd);
		List<Ville> ret = new ArrayList<>();
		
		if(nom == null) {
			ret = villeFranceImpl.afficherVilles();
		}
		if(nom != null) {
			ret = villeFranceImpl.afficherVille(nom);
		}
		
		return ret;
	}
	
	// fonction pour enregistrer un element dans la BDD
	@RequestMapping(value="/ville", method=RequestMethod.POST)
	@ResponseBody
	public void post(@RequestBody String request) throws DaoException {
		String[] result = request.split("[{}\":,]");
	
		DaoFactory connexionBdd = new DaoFactory();
		VilleFranceImplementation villeFranceImpl = new VilleFranceImplementation(connexionBdd);
		villeFranceImpl.ajouterVille(result[5], result[11], result[17], result[23], result[29], result[35], result[41]);
	}
	
	@RequestMapping(value = "/ville", method = RequestMethod.PUT)
	@ResponseBody
	public void put(@RequestBody String request) throws JsonMappingException, JsonProcessingException, SQLException, DaoException {
		@SuppressWarnings("unchecked")
		Map<String, String> result = new ObjectMapper().readValue(request, HashMap.class);
		
		DaoFactory connexionBdd = new DaoFactory();
		VilleFranceImplementation villeFranceImpl = new VilleFranceImplementation(connexionBdd);
		villeFranceImpl.modifierVille(result);
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestBody String request) throws DaoException {
		String[] result = request.split("[\"{}:]");
		
		DaoFactory connexionBdd = new DaoFactory();
		VilleFranceImplementation villeFranceImpl = new VilleFranceImplementation(connexionBdd);
		villeFranceImpl.supprimerVille(result[5]);
	}
}