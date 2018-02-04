package ar.edu.utn.dds.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.ExpressionParser;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.RepoEmpresas;
import ar.edu.utn.dds.grupo5.RepoIndicadores;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController {

	public ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/evaluacionIndicador.hbs");
	}

	public ModelAndView guardarIndicador(Request request, Response response) {
		String mensaje; 
		String color;
		Usuario usuario = Routes.getUsuarioDeSesion(request.session().id());
		Indicador indicador = new Indicador(request.queryParams("nombre"), request.queryParams("formula"));
		indicador.setUsuario(usuario);
		List<Indicador> indicadoresExistentes = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());
		List<Cuenta> cuentasExistentes = EMFactorySingleton.obtenerCuentas();
		
		if (indicador.getNombre().isEmpty() || indicador.getFormula().isEmpty() || indicador.getNombre() == null
				|| indicador.getFormula() == null) {
			mensaje = "Rellene todos los campos";
			color = "red";
		} else {
			try {
				ExpressionParser parser = new ExpressionParser();
				parser.validarFormula(indicador.getFormula());
				EMFactorySingleton.persistir(indicador);
				indicadoresExistentes.add(indicador);
				mensaje = "Indicador creado correctamente";
				color = "green";
			} catch(Exception e){
				mensaje = e.getMessage();
				color = "red";
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("titulo", "Dónde invierto - Creación de indicador");
		map.put("mensaje", mensaje);
		map.put("indicadoresExistentes",indicadoresExistentes);
		map.put("cuentasExistentes",cuentasExistentes);
		map.put("color", color);
		map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
		map.put("exit", "exit_to_app");
		map.put("salirTitulo", "Salir");
		map.put("menu", "menu");
		return new ModelAndView(map, "views/creacionIndicador.hbs");
	}

	public ModelAndView creacionIndicador(Request req, Response res) {
		try {
			Usuario usuario = Routes.getUsuarioDeSesion(req.session().id());
			List<Empresa> empresas = EMFactorySingleton.obtenerEmpresas();
			List<Indicador> indicadoresExistentes = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());
			List<Cuenta> cuentasExistentes = EMFactorySingleton.obtenerCuentas();
			Map<String, Object> map = new HashMap<>();
			map.put("titulo", "Dónde invierto - Creación de indicador");
			map.put("empresas", empresas);
			map.put("cuentasExistentes",cuentasExistentes);
			map.put("indicadoresExistentes",indicadoresExistentes);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
			return new ModelAndView(map, "views/creacionIndicador.hbs");
		} catch (Exception e) {
			res.redirect("/");
			return null;
		}
	}

	public ModelAndView viewIndicadores(Request request, Response response) {
		Usuario usuario;
		try {
			usuario = Routes.getUsuarioDeSesion(request.session().id());

		} catch (Exception e) {
			response.redirect("/");
			return null;
		}
		List<Empresa> empresas = EMFactorySingleton.obtenerEmpresas();
		RepoEmpresas.agregarEmpresas(empresas);
		List<Indicador> listaIndicadores = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());
		Map<String, Object> map = new HashMap<>();
		map.put("indicadores", listaIndicadores);
		map.put("empresas", empresas);
		map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
		map.put("titulo", "Dónde Invierto - Evaluación de Indicadores");
		map.put("exit", "exit_to_app");
		map.put("salirTitulo", "Salir");
		map.put("menu", "menu");
		map.put("placeholderIndicador", "Seleccione un Indicador");
		map.put("placeholderEmpresa", "Seleccione una Empresa");
		return new ModelAndView(map, "views/evaluacionIndicador.hbs");
	}

	public ModelAndView calcularIndicador(Request request, Response response) {
		Usuario usuario;
		List<Indicador> listaIndicadores = new ArrayList<Indicador>();
		List<Empresa> empresas = new ArrayList<Empresa>();
		String mensaje = "";
		String color = "";
		Indicador indicadorSeleccionado =  null;
		Empresa empresaSeleccionada = null;
		Map<String, Object> map = new HashMap<>();
		
		int calculo = 0;
		try {
			usuario = Routes.getUsuarioDeSesion(request.session().id());
		} catch (Exception e) {
			response.redirect("/");
			return null;
		}	
		
		try {
			empresas = EMFactorySingleton.obtenerEmpresas();
			listaIndicadores = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());
			RepoIndicadores.setearIndicadores(listaIndicadores);
			indicadorSeleccionado = listaIndicadores.stream()
					.filter(x -> x.getNombre().equals(request.queryParams("selectedInd"))).findFirst().get();
			empresaSeleccionada = empresas.stream()
					.filter(x -> x.getNombre().equals(request.queryParams("selectedEmp"))).findFirst().get();
			Indicador ind = new Indicador(indicadorSeleccionado.getNombre(), indicadorSeleccionado.getFormula());
			calculo = ind.calcularIndicador(empresaSeleccionada);		
			map.put("formula", indicadorSeleccionado.getFormula());
			map.put("placeholderIndicador", indicadorSeleccionado.getNombre());
			map.put("placeholderEmpresa", empresaSeleccionada.getNombre());			
		} catch(Exception e){
			mensaje = e.getMessage();
			if(empresaSeleccionada == null){
				mensaje = "Seleccione una empresa";
			}
			if(indicadorSeleccionado == null){
				mensaje = "Seleccione un indicador";
			}
			map.put("placeholderIndicador", "Seleccione un Indicador");
			map.put("placeholderEmpresa", "Seleccione una Empresa");
			color = "red";
		}
		
		map.put("indicadores", listaIndicadores);
		map.put("empresas", empresas);
		map.put("indicadorSeleccionado", indicadorSeleccionado);
		map.put("empresaSeleccionada", empresaSeleccionada);		
		map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
		map.put("titulo", "Dónde Invierto - Evaluación de Indicadores");
		map.put("exit", "exit_to_app");
		map.put("mensaje", mensaje);
		map.put("color", color);
		map.put("salirTitulo", "Salir");
		map.put("menu", "menu");
		map.put("resultado", calculo);
		return new ModelAndView(map, "views/evaluacionIndicador.hbs");
	}
}
