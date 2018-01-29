package ar.edu.utn.dds.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.dds.spark.utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import ar.edu.utn.dds.Controllers.*;
import ar.edu.utn.dds.grupo5.Usuario;

public class Routes {

	private static List<Sesion> sesiones = new ArrayList<>();

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();
		Spark.staticFiles.location("/public");		

		// rutas

		Spark.get("/", LoginController::login, engine);
		Spark.post("/login/", LoginController::loginUsuario);
		Spark.get("/menuPrincipal/", LoginController::homeView,engine);				
		Spark.get("/visualizarCuentas/", CuentasController::viewCuentas,engine);
		Spark.get("/evaluacionIndicador/", IndicadorController::viewIndicadores,engine);
		Spark.post("/evaluacionIndicador/", IndicadorController::calcularIndicador,engine);
		Spark.get("/creacionIndicador/", IndicadorController::creacionIndicador, engine);
		Spark.post("/creacionIndicador/", IndicadorController::guardarIndicador);
		Spark.get("/crearMetodologia/", MetodologiaCreacionController::crearMetodologia, engine);
		Spark.post("/crearMetodologia/", MetodologiaCreacionController::guardarMetodologia);
		Spark.get("/evaluacionMetodologia/", MetodologiaEvaluacionController::evaluacionMetodologia, engine);
		Spark.post("/evaluacionMetodologia/", MetodologiaEvaluacionController::visualizarResultados, engine);
		
	}
	
	public static Usuario getUsuarioDeSesion(String idSesion) {	  
		return sesiones.stream().filter(sesion -> sesion.getId().equals(idSesion)).collect(Collectors.toList()).get(0).getUsuario();
	}

	public static void iniciarSesion(String idSesion, Usuario usuario) {
		if (sesiones.stream().anyMatch(sesion -> sesion.getId().equals(idSesion)))
			sesiones.stream().filter(sesion -> sesion.getId().equals(idSesion)).collect(Collectors.toList()).get(0)
					.setUsuario(usuario);
		else
			sesiones.add(new Sesion(idSesion, usuario));
	}
	public static void cerrarSesion(String idSesion) {
		if (sesiones.stream().anyMatch(sesion -> sesion.getId().equals(idSesion)))
			sesiones.stream().filter(sesion -> sesion.getId().equals(idSesion)).collect(Collectors.toList()).get(0)
					.setUsuario(null);
	}
}