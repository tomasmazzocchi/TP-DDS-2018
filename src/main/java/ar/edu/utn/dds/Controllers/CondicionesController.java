package ar.edu.utn.dds.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CondicionesController {
	
	public ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/creacionCondicion.hbs");
	}
	
	public ModelAndView creacionCondicion(Request request, Response response) {
		try {
			Usuario usuario = Routes.getUsuarioDeSesion(request.session().id());
			Map<String, Object> map = new HashMap<>();
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("titulo", "Dónde Invierto - Creación de condición");
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
			return new ModelAndView(map, "views/creacionCondicion.hbs");
		} catch (Exception e) {
			response.redirect("/");
			return null;
		}
	}
}
