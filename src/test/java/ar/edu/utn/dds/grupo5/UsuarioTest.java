package ar.edu.utn.dds.grupo5;

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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsuarioTest {
	private Usuario usuario;	
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
	public void init() {
		usuario = new Usuario("Tomi","1234");
	}

	@Test
	public void siPersistoUnUsuarioLuegoLoObtengo() {
		borrarObjetosDeClase(Usuario.class);
		this.entityManager = entityManagerFactory.createEntityManager();		
		EntityTransaction tx = this.entityManager.getTransaction();
		tx.begin();
			this.entityManager.persist(usuario);
		tx.commit();
		Usuario user = (Usuario) this.entityManager.createQuery("SELECT DISTINCT OBJECT(k) " +
                "FROM usuario k WHERE k.nombreUsuario = 'Tomi'").getSingleResult();
					
		Assert.assertTrue(user.getNombreUsuario() == "Tomi");
		this.entityManager.close();
		
	}
	
}
