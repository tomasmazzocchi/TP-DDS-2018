package ar.edu.utn.dds.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.EmpresaDTO;
import DTO.IndicadorDTO;
import DTO.UsuarioDTO;
import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController {
	private static String mensaje = "";
	private static String color = "";
	private static List<EmpresaDTO> empresas = new ArrayList<EmpresaDTO>();
	private static List<IndicadorDTO> listaIndicadores = new ArrayList<IndicadorDTO>();
	private static int calculo;

	public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/evaluacionIndicador.hbs");
	}

	public static Void guardarIndicador(Request request, Response response) {
		UsuarioDTO usuario = Routes.getUsuarioDeSesion(request.session().id());
		EmpresaDTO empresaSeleccionada = empresas.stream()
				.filter(x -> x.getNombre().equals(request.queryParams("selected"))).findFirst().get();
		IndicadorDTO indicador = new IndicadorDTO(request.queryParams("nombre"), request.queryParams("formula"),
				empresaSeleccionada, usuario);
		if (indicador.getNombre().isEmpty() || indicador.getFormula().isEmpty() || indicador.getNombre() == null
				|| indicador.getFormula() == null) {
			mensaje = "Rellene todos los campos";
			color = "red";
		} else {
			EMFactorySingleton.persistir(indicador);
			mensaje = "Indicador creado correctamente";
			color = "green";
		}
		response.redirect("/creacionIndicador/");
		return null;
	}

	public static ModelAndView creacionIndicador(Request req, Response res) {
		try {
			UsuarioDTO usuario = Routes.getUsuarioDeSesion(req.session().id());
			empresas = EMFactorySingleton.obtenerEmpresasDeUnUsuario(usuario.getNombreUsuario());

			Map<String, Object> map = new HashMap<>();
			map.put("titulo", "Dónde invierto - Creación de indicador");
			map.put("mensaje", mensaje);
			map.put("empresas", empresas);
			map.put("color", color);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
			mensaje = "";
			return new ModelAndView(map, "views/creacionIndicador.hbs");
		} catch (Exception e) {
			res.redirect("/");
			return null;
		}
	}

	public static ModelAndView viewIndicadores(Request request, Response response) {
		UsuarioDTO usuario;
		try {
			usuario = Routes.getUsuarioDeSesion(request.session().id());

		} catch (Exception e) {
			response.redirect("/");
			return null;
		}
		empresas = EMFactorySingleton.obtenerEmpresasDeUnUsuario(usuario.getNombreUsuario());
		listaIndicadores = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());
		Map<String, Object> map = new HashMap<>();
		map.put("indicadores", listaIndicadores);
		map.put("empresas", empresas);
		map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
		map.put("titulo", "Dónde Invierto - Evaluación de Indicadores");
		map.put("exit", "exit_to_app");
		map.put("salirTitulo", "Salir");
		map.put("menu", "menu");
		map.put("resultado", calculo);
		map.put("placeholderIndicador", "Seleccione un Indicador");
		map.put("placeholderEmpresa", "Seleccione una Empresa");
		return new ModelAndView(map, "views/evaluacionIndicador.hbs");
	}

	public static ModelAndView calcularIndicador(Request request, Response response) {
		UsuarioDTO usuario;
		try {
			usuario = Routes.getUsuarioDeSesion(request.session().id());
		} catch (Exception e) {
			response.redirect("/");
			return null;
		}

		EmpresaDTO empresaSeleccionada = empresas.stream()
				.filter(x -> x.getNombre().equals(request.queryParams("selectedEmp"))).findFirst().get();
		IndicadorDTO indicadorSeleccionado = listaIndicadores.stream()
				.filter(x -> x.getNombre().equals(request.queryParams("selectedInd"))).findFirst().get();
		Indicador ind = new Indicador(indicadorSeleccionado.getNombre(), indicadorSeleccionado.getFormula());
		calculo = ind.calcularIndicador(empresaSeleccionada.getId_empresa());
		Map<String, Object> map = new HashMap<>();
		map.put("indicadores", listaIndicadores);
		map.put("empresas", empresas);
		map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
		map.put("titulo", "Dónde Invierto - Evaluación de Indicadores");
		map.put("exit", "exit_to_app");
		map.put("salirTitulo", "Salir");
		map.put("menu", "menu");
		map.put("resultado", calculo);
		map.put("formula", indicadorSeleccionado.getFormula());
		map.put("placeholderIndicador", indicadorSeleccionado.getNombre());
		map.put("placeholderEmpresa", empresaSeleccionada.getNombre());
		return new ModelAndView(map, "views/evaluacionIndicador.hbs");
	}
}
