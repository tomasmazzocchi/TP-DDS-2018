package ar.edu.utn.dds.GUI;

import java.time.LocalDate;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.rest.EMFactorySingleton;

public class MainApp3 {
	private static Cuenta cuentaEBIDTAFacebook;
	private static Cuenta cuentaMargenFacebook;
	private static Cuenta cuentaEBIDTAGoogle;
	private static Cuenta cuentaMARGENGooge;
	private static Cuenta cuentaEBIDTATwitter;
	private static EntityManagerFactory emf = EMFactorySingleton.instance();
	private static EntityManager em = null;


	
	public static void main(String[] args) {
			//Definiciones
		cuentaEBIDTAFacebook = new Cuenta("EBIDTA", 100, LocalDate.now(), LocalDate.now());
		cuentaMargenFacebook = new Cuenta("Margen", 200, LocalDate.now(), LocalDate.now());
		cuentaEBIDTAGoogle = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentaMARGENGooge = new Cuenta("Margen", 300, LocalDate.now(), LocalDate.now());
		cuentaEBIDTATwitter = new Cuenta("EBIDTA", 300, LocalDate.now(), LocalDate.now());
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
			em.persist(cuentaMargenFacebook);
					
		em.getTransaction().commit();
			

		
	}
}

