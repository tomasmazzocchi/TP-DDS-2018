package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.utn.dds.grupo5.Condiciones.Longevidad;
import ar.edu.utn.dds.rest.EMFactorySingleton;

public class UsuarioTest {
	private static Usuario usuario;
	private static Metodologia metodologiaBuffet;
	private Longevidad unaLongevidad;	

	@BeforeClass
	public static void setUpClass() {
	}

	@Before
	public void init() {
		usuario = new Usuario("Tomi", "1234");
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
