package ar.edu.utn.dds.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DTO.MetodologiaDTO;
import DTO.UsuarioDTO;
import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiaCreacionController {

	private static String mensaje = "";
	private static String color = "";
	private static List<Condicion> condiciones = new ArrayList<Condicion>();

	public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/crearMetodologia.hbs");
	}

	public static ModelAndView crearMetodologia(Request req, Response res) {
		try {
			UsuarioDTO usuario = Routes.getUsuarioDeSesion(req.session().id());
			condiciones = EMFactorySingleton.obtenerCondiciones();

			Map<String, Object> map = new HashMap<>();
			map.put("titulo", "Dónde invierto - Creación de metodologias");
			map.put("mensaje", mensaje);
			map.put("condiciones", condiciones);
			map.put("color", color);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
			mensaje = "";
			return new ModelAndView(map, "views/crearMetodologia.hbs");
		} catch (Exception e) {
			res.redirect("/");
			return null;
		}
	}

	public static Void guardarMetodologia(Request request, Response response) {
		UsuarioDTO usuario = Routes.getUsuarioDeSesion(request.session().id());
		List<Condicion> condicionesSeleccionadas = condiciones.stream()
				.filter(x -> request.queryParams("selected").contains(x.getNombre())).collect(Collectors.toList());
		
		MetodologiaDTO metodologia = new MetodologiaDTO(request.queryParams("nombre"),condicionesSeleccionadas,usuario);
		if (metodologia.getNombre().isEmpty() || metodologia.getCondiciones().isEmpty() || metodologia.getNombre() == null
				|| metodologia.getCondiciones() == null) {
			mensaje = "Rellene todos los campos";
			color = "red";
		} else {
			EMFactorySingleton.persistir(metodologia);
			mensaje = "Metodologia creada correctamente";
			color = "green";
		}
		response.redirect("/crearMetodologia/");
		return null;
	}

}
