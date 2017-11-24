package ar.edu.utn.dds.GUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Metodologia;
import ar.edu.utn.dds.grupo5.RepoEmpresas;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.grupo5.Condiciones.Longevidad;
import ar.edu.utn.dds.grupo5.Condiciones.MargenCreciente;
import ar.edu.utn.dds.grupo5.Condiciones.MaximizarIndicador;
import ar.edu.utn.dds.grupo5.Condiciones.MinimizarIndicador;
import ar.edu.utn.dds.hibernate.EMFactorySingleton;

public class MainApp3 {
	private static EntityManagerFactory emf = EMFactorySingleton.instance();
	private static EntityManager em = null;
	private static Usuario usuario1;
	private static Usuario usuario2;
	private static List<Cuenta> listaCuentasFacebook = new ArrayList<>();
	private static List<Cuenta> listaCuentasGoogle = new ArrayList<>();
	private static List<Cuenta> listaCuentasTwitter = new ArrayList<>();
	private static List<Indicador> listaIndicadoresFacebook = new ArrayList<>();
	private static List<Indicador> listaIndicadoresGoogle = new ArrayList<>();
	private static List<Indicador> listaIndicadoresTwitter = new ArrayList<>();
	private static Empresa facebook;
	private static Empresa google;
	private static Empresa twitter;
	private static RepoEmpresas repoEmpresas;
	private static Metodologia metodologiaBuffetTwitter;
	private static Longevidad unaLongevidad;
	private static Indicador indicadorROEFacebook;
	private static Indicador indicadorROAFacebook;
	private static Indicador indicadorROEGoogle;
	private static Indicador indicadorROAGoogle;
	private static Indicador indicadorROETwitter;
	private static Indicador indicadorROATwitter;
	private static Indicador indicadorDeudaFacebook;
	private static Indicador indicadorDeudaGoogle;
	private static Indicador indicadorDeudaTwitter;
	private static MaximizarIndicador maxIndicador;
	private static MinimizarIndicador minIndicador;
	private static MargenCreciente margenCreciente;
	private static Cuenta cuentaEBIDTAFacebook;
	private static Cuenta cuentaMargenFacebook;
	private static Cuenta cuentaEBIDTAGoogle;
	private static Cuenta cuentaMARGENGooge;
	private static Cuenta cuentaEBIDTATwitter;
	private static Cuenta cuentaMARGENTwitter;

	public static void main(String[] args) {
			//Definiciones
		
		usuario1 = new Usuario("pablo","1234");
		usuario2 = new Usuario("pedro","1234");
		
		cuentaEBIDTAFacebook = new Cuenta("EBIDTA", 100, LocalDate.now(), LocalDate.now());
		cuentaMargenFacebook = new Cuenta("Margen", 200, LocalDate.now(), LocalDate.now());
		cuentaEBIDTAGoogle = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentaMARGENGooge = new Cuenta("Margen", 300, LocalDate.now(), LocalDate.now());
		cuentaEBIDTATwitter = new Cuenta("EBIDTA", 300, LocalDate.now(), LocalDate.now());
		cuentaMARGENTwitter = new Cuenta("Margen", 400, LocalDate.now(), LocalDate.now());
		
		listaCuentasFacebook.add(cuentaEBIDTAFacebook);
		listaCuentasFacebook.add(cuentaMargenFacebook);
		listaCuentasGoogle.add(cuentaEBIDTAGoogle);
		listaCuentasGoogle.add(cuentaMARGENGooge);
		listaCuentasTwitter.add(cuentaEBIDTATwitter);
		listaCuentasTwitter.add(cuentaMARGENTwitter);
		
		indicadorROEFacebook = new Indicador("ROE", "cu.EBIDTA");
		indicadorROAFacebook = new Indicador("ROA", "cu.Margen");
		indicadorDeudaFacebook = new Indicador("DEUDA","300");
		indicadorROEGoogle = new Indicador("ROE", "cu.EBIDTA");
		indicadorROAGoogle = new Indicador("ROA", "cu.Margen");
		indicadorDeudaGoogle = new Indicador("DEUDA","400");
		indicadorROETwitter = new Indicador("ROE", "cu.EBIDTA");
		indicadorROATwitter= new Indicador("ROA", "cu.Margen");
		indicadorDeudaTwitter = new Indicador("DEUDA","1000");
		
		listaIndicadoresFacebook.add(indicadorROEFacebook);
		listaIndicadoresFacebook.add(indicadorROAFacebook);
		listaIndicadoresFacebook.add(indicadorDeudaFacebook);
		listaIndicadoresGoogle.add(indicadorROEGoogle);
		listaIndicadoresGoogle.add(indicadorROAGoogle);
		listaIndicadoresGoogle.add(indicadorDeudaGoogle);
		listaIndicadoresTwitter.add(indicadorROETwitter);
		listaIndicadoresTwitter.add(indicadorROATwitter);
		listaIndicadoresTwitter.add(indicadorDeudaTwitter);
		
		facebook = new Empresa("Facebook",listaCuentasFacebook,listaIndicadoresFacebook, LocalDate.now());
		google = new Empresa("Google",listaCuentasGoogle,listaIndicadoresGoogle, LocalDate.now().minusYears(50));
		twitter = new Empresa("Twitter",listaCuentasTwitter,listaIndicadoresTwitter, LocalDate.now().minusYears(20));
		
		//Condiciones
		unaLongevidad = new Longevidad(10);
		maxIndicador = new MaximizarIndicador(indicadorROETwitter);
		margenCreciente = new MargenCreciente();
		minIndicador = new MinimizarIndicador(indicadorROATwitter);
		
		repoEmpresas = new RepoEmpresas("repoEmpresas");
		repoEmpresas.agregarEmpresa(facebook);
		repoEmpresas.agregarEmpresa(google);
		repoEmpresas.agregarEmpresa(twitter);
		List<Condicion> condiciones = new ArrayList<>();
		condiciones.add(unaLongevidad);
		condiciones.add(maxIndicador);
		condiciones.add(minIndicador);
		condiciones.add(margenCreciente);

		metodologiaBuffetTwitter = new Metodologia("Metodologia Buffet Twitter",condiciones);
		
		//agrego usuarios

		metodologiaBuffetTwitter.setUsuario(usuario1);
		indicadorROETwitter.setUsuario(usuario1);
		indicadorROATwitter.setUsuario(usuario1);
		indicadorDeudaTwitter.setUsuario(usuario1);
		indicadorROEFacebook.setUsuario(usuario2);
		indicadorROAFacebook.setUsuario(usuario2);
		indicadorDeudaFacebook.setUsuario(usuario2);
		indicadorROEGoogle.setUsuario(usuario1);
		indicadorROAGoogle.setUsuario(usuario1);
		indicadorDeudaGoogle.setUsuario(usuario1);

		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//Metodologias
		em.persist(metodologiaBuffetTwitter);
		
		//Condiciones
		em.persist(unaLongevidad);
		em.persist(maxIndicador); 
		em.persist(margenCreciente); 
		em.persist(minIndicador);
		
		// indicadores
		em.persist(indicadorROEFacebook);
		em.persist(indicadorROAFacebook);
		em.persist(indicadorDeudaFacebook);
		em.persist(indicadorROEGoogle);
		em.persist(indicadorROAGoogle);
		em.persist(indicadorDeudaGoogle);
		em.persist(indicadorROETwitter);
		em.persist(indicadorROATwitter);
		em.persist(indicadorDeudaTwitter);
		//* cuentas
		em.persist(cuentaEBIDTAFacebook);
		em.persist(cuentaMargenFacebook);
		em.persist(cuentaEBIDTAGoogle);
		em.persist(cuentaMARGENGooge);
		em.persist(cuentaEBIDTATwitter);
		em.persist(cuentaMARGENTwitter);
		// empresas
		em.persist(facebook);
		em.persist(google);
		em.persist(twitter);
		//usuarios
		em.persist(usuario1);
		em.persist(usuario2);
							
		em.getTransaction().commit();
		em.clear();
		
			
	}
}

