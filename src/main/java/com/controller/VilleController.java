package com.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.VilleFranceImplementation;

@RestController
public class VilleController {

	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public String get(@RequestParam(required  = false, value="codePostal") String codePostal) {
		System.out.println("get");
		// TODO : mon code vers la BDD (visualiser la ville à partir du codePostal entré en paramètres)
		VilleFranceImplementation villeFranceImpl = new VilleFranceImplementation();
		String ret = "";
		if(codePostal == null) {
			ret = villeFranceImpl.afficherVilles();
		}
		else {
			ret = villeFranceImpl.afficherVille(codePostal);
		}
		
		return ret;
	}
	
	// TODO : 
	// fonction pour enregistrer un element dans la BDD
	@RequestMapping(value="/ville", method=RequestMethod.POST)
	@ResponseBody
	public void post(@RequestBody String request) {
		System.out.println("post");
		System.out.println(request);
		System.out.println("-------------------------------------------------");
		String[] result = request.split("[&=]");
		for(String item : result) {
			System.out.println(item);
		}
		VilleFranceImplementation villeFranceImpl = new VilleFranceImplementation();
		villeFranceImpl.ajouterVille(result[1], result[3], result[5], result[7], result[9], result[11], result[13]);
	}

}