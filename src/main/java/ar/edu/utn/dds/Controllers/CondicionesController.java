package ar.edu.utn.dds.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionCuenta;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionIndicador;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionNumero;
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
			List<Cuenta> cuentas = EMFactorySingleton.obtenerCuentas();
			List<Indicador> indicadores = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());			
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("cuentas", cuentas);
			map.put("indicadores", indicadores);
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
	
	public ModelAndView creacionConCuenta(Request request, Response response) {
		String message = "";
		Usuario usuario = null;
		String color = "green";
		Map<String, Object> map = new HashMap<>();
		try	{
			usuario = Routes.getUsuarioDeSesion(request.session().id());			
		} catch (Exception e){
			response.redirect("/");
			return null;
		}
		try {
			List<Cuenta> cuentas = EMFactorySingleton.obtenerCuentas();
			List<Indicador> indicadores = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());
			Cuenta cuentaElegida = cuentas.stream()
					.filter(x -> x.getNombre().equals(request.queryParams("selectCuentas"))).findFirst().get();
			char restriccion =  request.queryParams("selectRestriccionesConCuenta").charAt(0);			
			int ponderacion = Integer.parseInt(request.queryParams("ponderacionConCuenta"));		
			CondicionCuenta condicionCreada = new CondicionCuenta(request.queryParams("nombreConCuenta"),cuentaElegida,ponderacion, restriccion);
			
			EMFactorySingleton.persistir(condicionCreada);
			message = "Condición " + condicionCreada.getNombre() + " creada correctamente";
			map.put("color", color);
			map.put("cuentas", cuentas);
			map.put("indicadores", indicadores);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("titulo", "Dónde Invierto - Evaluación de metodologias");
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
		} catch (Exception e) {
			message = e.getMessage();
			color = "red";
		}
		map.put("message", message);
		map.put("color", color);
		
		return new ModelAndView(map, "views/creacionCondicion.hbs");		
	}
	
	public ModelAndView creacionConIndicador(Request request, Response response) {
		String message = "";
		Usuario usuario = null;
		String color = "green";
		Map<String, Object> map = new HashMap<>();
		try	{
			usuario = Routes.getUsuarioDeSesion(request.session().id());			
		} catch (Exception e){
			response.redirect("/");
			return null;
		}
		try {
			List<Cuenta> cuentas = EMFactorySingleton.obtenerCuentas();
			List<Indicador> indicadores = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());
			Indicador indicadorElegido = indicadores.stream()
					.filter(x -> x.getNombre().equals(request.queryParams("selectIndicadores"))).findFirst().get();
			char restriccion =  request.queryParams("selectRestriccionesConIndicador").charAt(0);			
			int ponderacion = Integer.parseInt(request.queryParams("ponderacionConIndicador"));
			
			CondicionIndicador condicionCreada = new CondicionIndicador(request.queryParams("nombreConIndicador"),indicadorElegido,ponderacion,restriccion);
			EMFactorySingleton.persistir(condicionCreada);
			
			message = "Condición " + condicionCreada.getNombre() + " creada correctamente";
			map.put("color", color);
			map.put("cuentas", cuentas);
			map.put("indicadores", indicadores);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("titulo", "Dónde Invierto - Evaluación de metodologias");
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
		} catch (Exception e) {
			message = e.getMessage();
			color = "red";
		}
		map.put("message", message);
		map.put("color", color);
		
		return new ModelAndView(map, "views/creacionCondicion.hbs");		
	}
	
	public ModelAndView creacionConNumero(Request request, Response response) {
		String message = "";
		String color = "green";
		Usuario usuario = null;
		Map<String, Object> map = new HashMap<>();
		
		try	{
			usuario = Routes.getUsuarioDeSesion(request.session().id());			
		} catch (Exception e){
			response.redirect("/");
			return null;
		}
		try {
			List<Cuenta> cuentas = EMFactorySingleton.obtenerCuentas();
			List<Indicador> indicadores = EMFactorySingleton.obtenerIndicadoresDeUnUsuario(usuario.getNombreUsuario());
			
			char restriccion =  request.queryParams("selectRestriccionesConNumero").charAt(0);			
			int ponderacion = Integer.parseInt(request.queryParams("ponderacionConNumero"));	
			int numeroElegido = Integer.parseInt(request.queryParams("numeroElegido"));
			
			CondicionNumero condicionCreada = new CondicionNumero(request.queryParams("nombreConNumero"),numeroElegido,ponderacion,restriccion);
			
			EMFactorySingleton.persistir(condicionCreada);
			message = "Condición " + condicionCreada.getNombre() + " creada correctamente";
			map.put("color", color);
			map.put("cuentas", cuentas);
			map.put("indicadores", indicadores);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("titulo", "Dónde Invierto - Evaluación de metodologias");
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
		} catch (Exception e) {
			message = e.getMessage();
			color = "red";
		}
		map.put("message", message);
		map.put("color", color);
		
		return new ModelAndView(map, "views/creacionCondicion.hbs");		
	}
	
}
