package ar.edu.utn.dds.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.RepoIndicadores;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController {
	private static EntityManager em = EMFactorySingleton.entityManager();
	private static RepoIndicadores repoIndicadores;
	public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/evaluacionIndicador.hbs");
	}
	
	public static Void guardarIndicador(Request request, Response response) {

		if (request.queryParams("id_indicador") == null) {
			// obtengo usuario de session
			Usuario usuario = Routes.getUsuarioDeSesion(request.session().id());
			// creo indicador
			Indicador indicador = new Indicador();
			indicador.setNombre(request.queryParams("nombre"));
			indicador.setFormula(request.queryParams("formula"));
			indicador.setUsuario(usuario);
			em.getTransaction().begin();
				repoIndicadores.save(em, indicador);
			em.getTransaction().commit();
		} else {
			em.getTransaction().begin();
				Indicador indicador = repoIndicadores.obtenerIndicador(em, Integer.parseInt(request.queryParams("id_indicador")));
				indicador.setNombre(request.queryParams("nombre"));
				indicador.setFormula(request.queryParams("formula"));
			em.getTransaction().commit();
		}

		return null;
	}
	
	public static ModelAndView creacionIndicador(Request req, Response res) {
		Map<String, Object> map = new HashMap<>();
		map.put("titulo", "Dónde Invierto");	
		Routes.cerrarSesion(req.session().id());
		return new ModelAndView(map, "views/creacionIndicador.hbs");
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
