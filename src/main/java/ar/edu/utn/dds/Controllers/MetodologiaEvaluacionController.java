package ar.edu.utn.dds.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DTO.IndicadorDTO;
import DTO.MetodologiaDTO;
import DTO.UsuarioDTO;
import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiaEvaluacionController {

	private static String mensaje = "";
	private static String color = "";
	private static List<MetodologiaDTO> listaMetodologias = new ArrayList<MetodologiaDTO>();

	public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/evaluacionMetodologia.hbs");
	}

	public static ModelAndView evaluacionMetodologia(Request req, Response res) {
		try {
			UsuarioDTO usuario = Routes.getUsuarioDeSesion(req.session().id());
			listaMetodologias = EMFactorySingleton.obtenerMetodologiasDeUnUsuario(usuario.getNombreUsuario());

			Map<String, Object> map = new HashMap<>();
			map.put("titulo", "Dónde invierto - Evaluacion de metodologias");
			map.put("mensaje", mensaje);
			map.put("metodologias", listaMetodologias);
			map.put("color", color);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
			mensaje = "";
			return new ModelAndView(map, "views/evaluacionMetodologia.hbs");
		} catch (Exception e) {
			res.redirect("/");
			return null;
		}
	}

	public static Void visualizarResultados(Request request, Response response) {
		return null;
	}
}
