package ar.edu.utn.dds.rest;

import static spark.Spark.*;
import ar.edu.utn.dds.grupo5.Metodologia;
import static ar.edu.utn.dds.rest.JsonUtil.*;

public class MetodologiaController {
	
	public MetodologiaController(final Metodologia unaMetodologia) {

	// Devuelve lista de condiciones de una metodologia.
		
	get("/metodologia", (req, res) -> unaMetodologia.getCondiciones() , json());
	// more routes
	
	//Fix
	
	after((req, res) -> {

		res.type("application/json");

		});

	

	}

}

