package ar.edu.utn.dds.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController {
	public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/evaluacionIndicador.hbs");
	}
	
	public static ModelAndView viewIndicadores(Request request, Response response) {
		try {
			Usuario usuario = Routes.getUsuarioDeSesion(request.session().id());
			List<Indicador> listaIndicadores = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());
			Map<String, Object> map = new HashMap<>();
			map.put("indicadores", listaIndicadores);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("titulo", "Dónde Invierto - Evaluación de Indicadores");	
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
			return new ModelAndView(map, "views/evaluacionIndicador.hbs");
		} catch(Exception e){
			response.redirect("/");
			return null;
		}
	}
}
