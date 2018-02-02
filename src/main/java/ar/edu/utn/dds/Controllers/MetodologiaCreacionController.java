package ar.edu.utn.dds.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Metodologia;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiaCreacionController {

	public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/crearMetodologia.hbs");
	}

	public ModelAndView crearMetodologia(Request req, Response res) {
		try {
			Usuario usuario = Routes.getUsuarioDeSesion(req.session().id());
			List<Condicion> condiciones = EMFactorySingleton.obtenerCondiciones();

			Map<String, Object> map = new HashMap<>();
			map.put("titulo", "Dónde invierto - Creación de metodologias");
			map.put("condiciones", condiciones);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
			return new ModelAndView(map, "views/crearMetodologia.hbs");
		} catch (Exception e) {
			res.redirect("/");
			return null;
		}
	}

	public ModelAndView guardarMetodologia(Request request, Response response) {
		Usuario usuario = Routes.getUsuarioDeSesion(request.session().id());
		List<Condicion> condiciones = EMFactorySingleton.obtenerCondiciones();
		String mensaje = "";
		String color = "";
		List<Condicion> condicionesSeleccionadas = condiciones.stream()
				.filter(x -> request.queryParams("selected").contains(x.getNombre())).collect(Collectors.toList());

		Metodologia metodologia = new Metodologia(request.queryParams("nombre"), condicionesSeleccionadas);
		metodologia.setUs(usuario);
		System.out.println(request.queryParams("selected"));
		for (int i = 0; i < condicionesSeleccionadas.size(); i++) {
			System.out.println(condicionesSeleccionadas.get(i).getNombre());
			System.out.println(condicionesSeleccionadas.get(i));
		}
		if (metodologia.getNombre().isEmpty() || metodologia.getCondiciones().isEmpty()
				|| metodologia.getNombre() == null || metodologia.getCondiciones() == null) {
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
