package ar.edu.utn.dds.rest;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import ar.edu.utn.dds.grupo5.Metodologia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import static ar.edu.utn.dds.rest.JsonUtil.*;

public class MetodologiaController {
	

	
	public MetodologiaController(final Metodologia unaMetodologia) {
		ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
	// Devuelve lista de condiciones de una metodologia.
		Map<String, Object> map = new HashMap<>();
		map.put("metodologia", unaMetodologia.getCondiciones());
		ModelAndView nuevoModelo = new ModelAndView(map, "metodologia");
		
	get("/metodologia",(req, res) -> nuevoModelo , engine);
	get("/metodologia2", (req, res) -> unaMetodologia.getCondiciones() , json());
	// more routes
	
	//Fix
	
	/*
	    after((req, res) -> {

		res.type("application/json");

		});
*/
	

	}

}

