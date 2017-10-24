package ar.edu.utn.dds.rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EMFactorySingleton {

	private static EntityManager instance;
	public static EntityManager instance(){
		if(instance == null){
			createEMFactory();
		}
		return instance;
	}
	
	private static void createEMFactory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
	    instance = emf.createEntityManager();
	}
	
}
