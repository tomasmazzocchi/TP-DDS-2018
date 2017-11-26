package ar.edu.utn.dds.rest;

import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Assert;

import ar.edu.utn.dds.grupo5.*;

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

	public static Usuario obtenerUsuario(String username) {
		return (Usuario) entityManager().createQuery("SELECT DISTINCT OBJECT(k) " + "FROM usuario k WHERE k.nombreUsuario = :nombre").setParameter("nombre", username)
				.getSingleResult();
	}
}
