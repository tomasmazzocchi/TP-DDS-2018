package ar.edu.utn.dds.Controllers;

import java.util.HashMap;
import java.util.Map;

import ar.edu.utn.dds.Server.*;
import ar.edu.utn.dds.grupo5.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	private static String mensaje = "";

	public static ModelAndView login(Request req, Response res) {
		Map<String, Object> map = new HashMap<>();
		map.put("titulo", "Ingreso a la aplicaci�n");
		map.put("mensaje", mensaje);
		mensaje = "";
		Routes.cerrarSesion(req.session().id());
		return new ModelAndView(map, "views/login.hbs");
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
			mensaje = "Usuario/contrase�a inv�lidos";
			res.redirect("/");
		}

		res.redirect("/menuPrincipal/");
		return null;
	}

	public static ModelAndView homeView(Request req, Response res) {

		Map<String, String> model = new HashMap<>();

		try {
			Usuario usuario = Routes.getUsuarioDeSesion(req.session().id());

			model.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			model.put("titulo", "D�nde Invierto - Men� Principal");
			model.put("exit", "exit_to_app");
			model.put("salirTitulo", "Salir");

			return new ModelAndView(model, "views/menuPrincipal.hbs");
		} catch (Exception e) {
			res.redirect("/");
			return null;
		}

	}

	public static ModelAndView loginError(Request req, Response res) {

		String mensajeEnCodigo = req.params("mensaje");

		String mensaje = mensajeEnCodigo.replace("%20", " ");

		Map<String, String> model = new HashMap<>();

		model.put("excepcion", mensaje);

		return new ModelAndView(model, "views/loginerror.hbs");
	}

}
