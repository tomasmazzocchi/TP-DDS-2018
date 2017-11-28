package ar.edu.utn.dds.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import ar.edu.utn.dds.Server.Routes;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.rest.EMFactorySingleton;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController {
	public static ModelAndView view(Request req, Response res) {
		return new ModelAndView(null, "views/appViews/visualizarCuentas.hbs");
	}
	
	public static ModelAndView viewCuentas(Request request, Response response) {
		Usuario usuario = Routes.getUsuarioDeSesion(request.session().id());
		List<Cuenta> listaCuentas = EMFactorySingleton.obtenerCuentasDeUnUsuario(usuario.getNombreUsuario());
		Map<String, Object> map = new HashMap<>();
		map.put("cuentas", listaCuentas);
		return new ModelAndView(map, "cuentas");
	}
}
