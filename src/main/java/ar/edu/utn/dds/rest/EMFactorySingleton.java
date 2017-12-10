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

import DTO.CuentaDTO;
import DTO.EmpresaDTO;
import DTO.IndicadorDTO;
import DTO.UsuarioDTO;

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
		return !entityManager().createQuery("SELECT DISTINCT OBJECT(k) " + "FROM usuario k WHERE k.nombreUsuario = :nombre").setParameter("nombre", username)
				.getResultList().isEmpty();

	}

	public static UsuarioDTO obtenerUsuario(String username) {
		CriteriaBuilder cb = entityManager().getCriteriaBuilder();
		CriteriaQuery<UsuarioDTO> criteria = cb.createQuery(UsuarioDTO.class);
		Root<UsuarioDTO> rootEntry = criteria.from(UsuarioDTO.class);
		CriteriaQuery<UsuarioDTO> all = criteria.select(rootEntry).where(cb.equal(rootEntry.get("nombreUsuario"), username));
		
		List<UsuarioDTO> usuario = entityManager().createQuery(all).getResultList();
		return usuario.get(0);
		//return (UsuarioDTO) entityManager().createQuery("SELECT DISTINCT OBJECT(k) " + "FROM usuario k WHERE k.nombreUsuario = :nombre").setParameter("nombre", username)
			//	.getSingleResult();
	}
	
	public static List<CuentaDTO> obtenerCuentasDeUnUsuario(String username) {
		int pkUsuario = obtenerUsuario(username).getId();
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		CriteriaQuery<CuentaDTO> criteria = criteriaBuilder.createQuery(CuentaDTO.class);
		Root<CuentaDTO> rootEntry = criteria.from(CuentaDTO.class);
		CriteriaQuery<CuentaDTO> all = criteria.select(rootEntry).where(
				criteriaBuilder.equal(rootEntry.get("usuarioAsociado"), pkUsuario));
		
		List<CuentaDTO> listaCuenta = entityManager().createQuery(all).getResultList();
		return listaCuenta;		
	}
	
	public static List<IndicadorDTO> obtenerIndicadoresDeUnUsuario(String username) {
		int pkUsuario = obtenerUsuario(username).getId();
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		CriteriaQuery<IndicadorDTO> criteria = criteriaBuilder.createQuery(IndicadorDTO.class);
		Root<IndicadorDTO> rootEntry = criteria.from(IndicadorDTO.class);
		CriteriaQuery<IndicadorDTO> all = criteria.select(rootEntry).where(
				criteriaBuilder.equal(rootEntry.get("usuarioAsociado"), pkUsuario));
		
		List<IndicadorDTO> listaIndicadores = entityManager().createQuery(all).getResultList();
		return listaIndicadores;		
	}
	public static List<EmpresaDTO> obtenerEmpresasDeUnUsuario(String username) {
		int pkUsuario = obtenerUsuario(username).getId();
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		CriteriaQuery<EmpresaDTO> criteria = criteriaBuilder.createQuery(EmpresaDTO.class);
		Root<EmpresaDTO> rootEntry = criteria.from(EmpresaDTO.class);
		CriteriaQuery<EmpresaDTO> all = criteria.select(rootEntry).where(
				criteriaBuilder.equal(rootEntry.get("usuarioAsociado"), pkUsuario));
		
		List<EmpresaDTO> listaEmpresas = entityManager().createQuery(all).getResultList();
		return listaEmpresas;			
	}
}
