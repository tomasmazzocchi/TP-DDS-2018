package ar.edu.utn.dds.grupo5;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.utn.dds.rest.EMFactorySingleton;

public class UsuarioTest {
	private static Usuario usuario;

	@BeforeClass
	public static void setUpClass() {
	}

	@Before
	public void init() {
		usuario = new Usuario("UsuarioTest", "1234");
	}

	@Test
	public void siPersistoUnUsuarioLuegoLoObtengo() {
		EMFactorySingleton.beginTransaction();
		if(!EMFactorySingleton.existeUsuario(usuario.getNombreUsuario())){
			EMFactorySingleton.persistir(usuario);
		}
		Usuario user = EMFactorySingleton.obtenerUsuario(usuario.getNombreUsuario());

		Assert.assertTrue(user.getNombreUsuario().equals(usuario.getNombreUsuario()));
		EMFactorySingleton.closeEntityManager();
	}
	

}
