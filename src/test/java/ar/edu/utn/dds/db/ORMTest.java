package ar.edu.utn.dds.db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.rest.EMFactorySingleton;

public class ORMTest {

	static EntityManager em = EMFactorySingleton.entityManager();
	private Usuario usuario1;
	private Empresa empresa1;
	private Cuenta cuentaEBIDTA;
	private Cuenta cuentaFDS;
	private Indicador indicadorMayorQue;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;

	@Before
	public void setUp() {
		usuario1 = new Usuario("clio", "1234");
		cuentaEBIDTA = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentaFDS = new Cuenta("FDS", 500, LocalDate.now(), LocalDate.now());
		indicadorMayorQue = new Indicador("MayorQue", "200");
		indicadorMayorQue.setUsuario(usuario1);
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		listaCuentas.add(cuentaEBIDTA);
		listaCuentas.add(cuentaFDS);
		listaIndicadores.add(indicadorMayorQue);
		empresa1 = new Empresa("Renault", listaCuentas, null, LocalDate.now().minusYears(10));
	}

	@Test
	public void test() {
		// Guardo la Empresa
		em.getTransaction().begin();
		em.persist(usuario1);
		
		em.persist(cuentaEBIDTA);
		em.persist(cuentaFDS);
		
		em.persist(indicadorMayorQue);

		em.persist(empresa1);

		em.getTransaction().commit();

		em.clear();

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = criteriaBuilder.createQuery(Empresa.class);
		Root<Empresa> rootEntry = criteria.from(Empresa.class);
		CriteriaQuery<Empresa> all = criteria.select(rootEntry);

		List<Empresa> listaEmpresas = em.createQuery(all).getResultList();
		Empresa empresaGuardada = listaEmpresas.stream().filter(x -> x.getNombre().equals(empresa1.getNombre())).collect(Collectors.toList())
				.get(0);

		Assert.assertNotNull(empresaGuardada);
		Assert.assertEquals(empresa1.getNombre(), empresaGuardada.getNombre());

	}

}