package ar.edu.utn.dds.rest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Metodologia;
import ar.edu.utn.dds.grupo5.Usuario;

public class EMFactorySingleton {

	private static EntityManagerFactory instance;
	private static ThreadLocal<EntityManager> threadLocal;

	static {
		try {
			instance = Persistence.createEntityManagerFactory("db");
			threadLocal = new ThreadLocal<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static EntityManager entityManager() {
		return getEntityManager();
	}

	public static EntityManager getEntityManager() {
		EntityManager manager = threadLocal.get();
		if (manager == null || !manager.isOpen()) {
			manager = instance.createEntityManager();
			threadLocal.set(manager);
		}
		return manager;
	}

	public static void beginTransaction() {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		if (!tx.isActive()) {
			tx.begin();
		}
	}

	public static void commit() {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		if (tx.isActive()) {
			tx.commit();
		}

	}

	public static void rollback() {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		if (tx.isActive()) {
			tx.rollback();
		}
	}

	public static void clear() {
		entityManager().clear();
	}

	public static void persistir(Object object) {
		beginTransaction();
		entityManager().persist(object);
		commit();
		clear();
	}

	public static void remover(Object object) {
		beginTransaction();
		entityManager().remove(object);
		commit();
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocal.get();
		threadLocal.set(null);
		em.close();
	}

	public static Query createQuery(String query) {
		return getEntityManager().createQuery(query);
	}

	public static boolean existeUsuario(String username) {
		return !entityManager()
				.createQuery("SELECT DISTINCT OBJECT(k) " + "FROM usuario k WHERE k.nombreUsuario = :nombre")
				.setParameter("nombre", username).getResultList().isEmpty();

	}

	public static Usuario obtenerUsuario(String username) {
		CriteriaBuilder cb = entityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> rootEntry = criteria.from(Usuario.class);
		CriteriaQuery<Usuario> all = criteria.select(rootEntry)
				.where(cb.equal(rootEntry.get("nombreUsuario"), username));

		List<Usuario> usuario = entityManager().createQuery(all).getResultList();
		return usuario.get(0);
	}

	public static List<Cuenta> obtenerCuentas() {
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		CriteriaQuery<Cuenta> criteria = criteriaBuilder.createQuery(Cuenta.class);
		Root<Cuenta> rootEntry = criteria.from(Cuenta.class);
		CriteriaQuery<Cuenta> all = criteria.select(rootEntry);
		List<Cuenta> listaCuenta = entityManager().createQuery(all).getResultList();
		return listaCuenta;
	}

	public static List<Condicion> obtenerCondiciones() {
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		CriteriaQuery<Condicion> criteria = criteriaBuilder.createQuery(Condicion.class);
		Root<Condicion> rootEntry = criteria.from(Condicion.class);
		CriteriaQuery<Condicion> all = criteria.select(rootEntry);
		List<Condicion> listaCondicion = entityManager().createQuery(all).getResultList();
		return listaCondicion;
	}

	public static List<Metodologia> obtenerMetodologiasDeUnUsuario(String username) {
		int pkUsuario = obtenerUsuario(username).getId();
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		CriteriaQuery<Metodologia> criteria = criteriaBuilder.createQuery(Metodologia.class);
		Root<Metodologia> rootEntry = criteria.from(Metodologia.class);
		CriteriaQuery<Metodologia> all = criteria.select(rootEntry)
				.where(criteriaBuilder.equal(rootEntry.get("usuarioAsociado"), pkUsuario));

		List<Metodologia> listaMetodologiaDTO = entityManager().createQuery(all).getResultList();
		return listaMetodologiaDTO;
	}

	public static List<Indicador> obtenerIndicadoresDeUnUsuario(String username) {
		int pkUsuario = obtenerUsuario(username).getId();
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		CriteriaQuery<Indicador> criteria = criteriaBuilder.createQuery(Indicador.class);
		Root<Indicador> rootEntry = criteria.from(Indicador.class);
		CriteriaQuery<Indicador> all = criteria.select(rootEntry)
				.where(criteriaBuilder.equal(rootEntry.get("usuarioAsociado"), pkUsuario));

		List<Indicador> listaIndicadores = entityManager().createQuery(all).getResultList();
		return listaIndicadores;
	}

	public static List<Empresa> obtenerEmpresas() {
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = criteriaBuilder.createQuery(Empresa.class);
		Root<Empresa> rootEntry = criteria.from(Empresa.class);
		CriteriaQuery<Empresa> all = criteria.select(rootEntry);

		List<Empresa> listaEmpresas = entityManager().createQuery(all).getResultList();
		return listaEmpresas;
	}
}
