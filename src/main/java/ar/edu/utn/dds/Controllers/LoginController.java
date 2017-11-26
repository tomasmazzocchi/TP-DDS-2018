package ar.edu.utn.dds.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.utn.dds.Server.*;
import ar.edu.utn.dds.grupo5.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	public static ModelAndView login(Request req, Response res) {
		return new ModelAndView(null, "views/login.hbs");
	}

	public static Void loginUsuario(Request req, Response res) {

		ManejadorDeUsuarios repoUsuarios = ManejadorDeUsuarios.getInstance();
		String body = req.body();
		String[] params = body.split("&");

		String username;
		String password;

		if (params[0].split("=").length < 2)
			username = "";
		else
			username = params[0].split("=")[1];

		if (params[1].split("=").length < 2)
			password = "";
		else
			password = params[1].split("=")[1];

		try {
			Usuario usuario = repoUsuarios.loginOK(username, password);
			
			Routes.iniciarSesion(req.session().id(), usuario);


			res.redirect("/menuPrincipal/");
			
		} catch (Exception e) {

			res.redirect("/loginerror/"+e.getMessage());
		}
		
		res.redirect("/menuPrincipal/");
		return null;
	}

	public static ModelAndView homeView(Request req, Response res) {

		Map<String, Usuario> model = new HashMap<>();

		//Usuario usuario = Routes.getUsuarioDeSesion(req.session().id());
		
		//model.put("usuario", usuario);

		return new ModelAndView(model, "views/menuPrincipal.hbs");
	}

	public static ModelAndView loginError(Request req, Response res) {
		
		String mensajeEnCodigo = req.params("mensaje");
		
		String mensaje = mensajeEnCodigo.replace("%20", " ");
		
		Map<String, String> model = new HashMap<>();
		
		model.put("excepcion",mensaje);

		return new ModelAndView(model, "views/loginerror.hbs");
	}

}
