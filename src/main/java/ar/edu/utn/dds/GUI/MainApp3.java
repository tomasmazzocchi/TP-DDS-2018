package ar.edu.utn.dds.GUI;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ar.edu.utn.dds.grupo5.Indicador;

public class MainApp3 {

	public static void main(String[] args) {
		Indicador indicadorROE = new Indicador("ROE", "20");
		  EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		  EntityManager em = emf.createEntityManager();
		  try {
		      // TODO: Use the EntityManager to access the database
			  em.persist(indicadorROE);
		  }
		  finally {
		      em.close();
		  }
		  emf.close();

	}

}
