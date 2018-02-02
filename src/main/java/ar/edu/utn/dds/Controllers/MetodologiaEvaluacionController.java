package ar.edu.utn.dds.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Metodologia;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiaEvaluacionController {

	public ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/evaluacionMetodologia.hbs");
	}

	public ModelAndView evaluacionMetodologia(Request req, Response res) {
		try {
			Usuario usuario = Routes.getUsuarioDeSesion(req.session().id());
			List<Metodologia> listaMetodologias = EMFactorySingleton.obtenerMetodologiasDeUnUsuario(usuario.getNombreUsuario());

			Map<String, Object> map = new HashMap<>();
			map.put("titulo", "D�nde invierto - Evaluacion de metodologias");
			map.put("metodologias", listaMetodologias);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
			return new ModelAndView(map, "views/evaluacionMetodologia.hbs");
		} catch (Exception e) {
			res.redirect("/");
			return null;
		}
	}

	public ModelAndView visualizarResultados(Request request, Response response) {
		String message = "";
		Usuario usuario;
		Map<String, List<Empresa>> resultados = new HashMap<String, List<Empresa>>();
		List<Metodologia> listaMetodologias =  new ArrayList<Metodologia>();
		try {
			usuario = Routes.getUsuarioDeSesion(request.session().id());
		} catch (Exception e) {
			response.redirect("/");
			return null;
		}
		try {
			List<Empresa> empresas = EMFactorySingleton.obtenerEmpresas();
			listaMetodologias = EMFactorySingleton.obtenerMetodologiasDeUnUsuario(usuario.getNombreUsuario());
			Metodologia metodologiaSeleccionada = listaMetodologias.stream()
					.filter(x -> x.getNombre().equals(request.queryParams("selected"))).findFirst().get();
			metodologiaSeleccionada.aplicarCondiciones(empresas);
			resultados = metodologiaSeleccionada.getResultados();
		} catch (Exception e) {
			message = "Seleccione una metodologia";
		}

		Map<String, Object> map = new HashMap<>();
		map.put("headerTabla", resultados.keySet());
		map.put("resultados", resultados);
		map.put("message", message);
		map.put("metodologias", listaMetodologias);
		map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
		map.put("titulo", "D�nde Invierto - Evaluaci�n de metodologias");
		map.put("exit", "exit_to_app");
		map.put("salirTitulo", "Salir");
		map.put("menu", "menu");
		return new ModelAndView(map, "views/evaluacionMetodologia.hbs");
	}
}
