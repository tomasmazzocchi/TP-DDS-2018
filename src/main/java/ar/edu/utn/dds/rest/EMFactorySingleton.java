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
	
	public static EntityManagerFactory instance(){
		if(instance == null){
			createEMFactory();
		}
		return instance;
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
	private static void createEMFactory() {
		instance = Persistence.createEntityManagerFactory("db");
		threadLocal = new ThreadLocal<>();
	}
	
	public static Query createQuery(String query) {
		return getEntityManager().createQuery(query);
	}
	
	public static boolean existeUsuario(String username) {		
		return !entityManager().createQuery("from usuario where username = :nombre").setParameter("nombre", username)
				.getResultList().isEmpty();

	}

	public static Usuario obtenerUsuario(String username) {
		return (Usuario) entityManager().createQuery("from Usuario where username = :nombre").setParameter("nombre", username)
				.getSingleResult();
	}
}
