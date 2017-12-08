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

public class CuentasController {
	public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/visualizarCuentas.hbs");
	}
	
	public static ModelAndView viewCuentas(Request request, Response response) {
		try	{
			Usuario usuario = Routes.getUsuarioDeSesion(request.session().id());
			List<Cuenta> listaCuentas = EMFactorySingleton.obtenerCuentasDeUnUsuario(usuario.getNombreUsuario());
			Map<String, Object> map = new HashMap<>();		
			map.put("cuentas", listaCuentas);
			map.put("usuario", "Usuario: " + usuario.getNombreUsuario());
			map.put("titulo", "Dónde Invierto - Visualización de Cuentas");	
			map.put("exit", "exit_to_app");
			map.put("salirTitulo", "Salir");
			map.put("menu", "menu");
			return new ModelAndView(map, "views/visualizarCuentas.hbs");			
		} catch(Exception e){
			response.redirect("/");
			return null;
		}
	}
}
