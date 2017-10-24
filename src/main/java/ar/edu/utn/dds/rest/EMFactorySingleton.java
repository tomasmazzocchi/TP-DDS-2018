package ar.edu.utn.dds.rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EMFactorySingleton {

	private static EntityManagerFactory instance;
	public static EntityManagerFactory instance(){
		if(instance == null){
			createEMFactory();
		}
		return instance;
	}
	
	private static void createEMFactory() {
		instance = Persistence.createEntityManagerFactory("db");
	    
	}
	
}
