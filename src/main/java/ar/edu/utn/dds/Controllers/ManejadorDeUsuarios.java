package ar.edu.utn.dds.Controllers;

import DTO.UsuarioDTO;
import ar.edu.utn.dds.rest.EMFactorySingleton;

public class ManejadorDeUsuarios {

	private static ManejadorDeUsuarios singleton = null;

	public UsuarioDTO loginOK(String username, String password) throws Exception {

		if(username.isEmpty() || username==null || password.isEmpty() || password==null){
			throw new Exception("Ingrese usuario y contraseña");
		}
		

		else if (!EMFactorySingleton.existeUsuario(username)) {
			throw new Exception("El usuario y contraseña ingresados son incorrectos");
		}

		else {
			UsuarioDTO usuario =  EMFactorySingleton.obtenerUsuario(username);

			if(!usuario.getPassword().equals(password))
				throw new Exception("El usuario y contraseña ingresados son incorrectos");
			else
				return usuario;
		}
	}

	public static ManejadorDeUsuarios getInstance() {
		if(singleton==null){
			singleton=new ManejadorDeUsuarios();
		}
		return singleton;
	}
	
	public UsuarioDTO getUsuario(String username) {
		return EMFactorySingleton.obtenerUsuario(username);
	}

}
