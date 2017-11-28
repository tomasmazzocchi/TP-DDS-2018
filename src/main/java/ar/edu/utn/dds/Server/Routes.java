package ar.edu.utn.dds.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.spark.utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import ar.edu.utn.dds.Controllers.*;

public class Routes {

	private static List<Sesion> sesiones = new ArrayList<>();

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		Spark.staticFiles.location("/public");
		// controllers
		LoginController loginCtlr = new LoginController();
		
		// rutas

		Spark.get("/", LoginController::login, engine);
		Spark.post("/login/", LoginController::loginUsuario);
		Spark.get("/menuPrincipal/", LoginController::homeView,engine);		
		Spark.get("/loginerror/:mensaje", LoginController::loginError, engine);
		
		Spark.get("/visualizarCuentas/", CuentasController::viewCuentas,engine);
	}

	public static Usuario getUsuarioDeSesion(String idSesion) {
		return sesiones.stream().filter(sesion -> sesion.getId().equals(idSesion)).collect(Collectors.toList()).get(0)
				.getUsuario();
	}

	public static void iniciarSesion(String idSesion, Usuario usuario) {
		if (sesiones.stream().anyMatch(sesion -> sesion.getId().equals(idSesion)))
			sesiones.stream().filter(sesion -> sesion.getId().equals(idSesion)).collect(Collectors.toList()).get(0)
					.setUsuario(usuario);
		else
			sesiones.add(new Sesion(idSesion, usuario));
	}

}