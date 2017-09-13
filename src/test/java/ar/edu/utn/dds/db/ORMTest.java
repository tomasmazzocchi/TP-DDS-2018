package ar.edu.utn.dds.db;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;


public class ORMTest {
	
	private Empresa empresa1;
	private Cuenta cuentaEBIDTA;
	private Cuenta cuentaFDS;
	private Indicador indicadorMayorQue;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;

	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager = null;

	@BeforeClass
	public static void setUpClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("db");

	}
		
	private  <T> void borrarObjetosDeClase(Class<T> class1) {
	        this.entityManager = entityManagerFactory.createEntityManager();
                EntityTransaction transaction = this.entityManager.getTransaction();
                transaction.begin();
                CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();              
                CriteriaDelete<T> query = builder.createCriteriaDelete(class1);
                query.from(class1);
                this.entityManager.createQuery(query).executeUpdate();
                transaction.commit();
        }


	@Before
	public void setUp() {
	        borrarObjetosDeClase(Empresa.class);
			cuentaEBIDTA = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
			cuentaFDS = new Cuenta("FDS", 500, LocalDate.now(), LocalDate.now());
			indicadorMayorQue = new Indicador("MayorQue", "200"); 
			listaCuentas = new ArrayList<>();
			listaIndicadores = new ArrayList<>();
			listaCuentas.add(cuentaEBIDTA);
			listaCuentas.add(cuentaFDS);
			listaIndicadores.add(indicadorMayorQue);
			empresa1 = new Empresa("Facebook", listaCuentas, listaIndicadores,LocalDate.now());
	
	}

	@Test
	public void test() {
		this.entityManager = entityManagerFactory.createEntityManager();
		
		// Guardo la Empresa
		EntityTransaction tx = this.entityManager.getTransaction();
		tx.begin();
			this.entityManager.persist(empresa1);
		tx.commit();

		this.entityManager.close();
		this.entityManager = entityManagerFactory.createEntityManager();

		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteriaQuery = qb.createQuery(Empresa.class);
		Root<Empresa> unaEmpresa = criteriaQuery.from(Empresa.class);
		Predicate condition = qb.equal(unaEmpresa.get("nombre"), "Facebook");
		criteriaQuery.where(condition);
		TypedQuery<Empresa> q = entityManager.createQuery(criteriaQuery);
		Empresa empresaGuardada = q.getSingleResult();

		assertNotNull(empresaGuardada);
		assertEquals(2, empresaGuardada.getListaCuentas().size());
	}

}