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
import ar.edu.utn.dds.rest.EMFactorySingleton;

public class MainApp3 {
	private static EntityManagerFactory emf = EMFactorySingleton.instance();
	private static EntityManager em = null;
	private static Usuario usuario1;
	private static List<Cuenta> listaCuentasFacebook = new ArrayList<>();
	private static List<Cuenta> listaCuentasGoogle = new ArrayList<>();
	private static List<Cuenta> listaCuentasTwitter = new ArrayList<>();
	private static List<Indicador> listaIndicadoresFacebook;
	private static List<Indicador> listaIndicadoresGoogle;
	private static List<Indicador> listaIndicadoresTwitter;
	private static Empresa facebook;
	private static Empresa google;
	private static Empresa twitter;
	private static RepoEmpresas repoEmpresas;
	private static Metodologia metodologiaBuffet;
	private static Longevidad unaLongevidad;
	private static Indicador indicadorROE;
	private static Indicador indicadorROA;
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
		
		listaIndicadoresFacebook = new ArrayList<>();
		listaIndicadoresGoogle = new ArrayList<>();
		listaIndicadoresTwitter = new ArrayList<>();
		
		indicadorROE = new Indicador("ROE", "cu.EBIDTA");
		indicadorROA = new Indicador("ROA", "cu.Margen");
		indicadorDeudaFacebook = new Indicador("DEUDA","300");
		indicadorDeudaGoogle = new Indicador("DEUDA","400");
		indicadorDeudaTwitter = new Indicador("DEUDA","1000");
		
		listaIndicadoresFacebook.add(indicadorROE);
		listaIndicadoresFacebook.add(indicadorROA);
		listaIndicadoresFacebook.add(indicadorDeudaFacebook);
		listaIndicadoresGoogle.add(indicadorROE);
		listaIndicadoresGoogle.add(indicadorROA);
		listaIndicadoresGoogle.add(indicadorDeudaGoogle);
		listaIndicadoresTwitter.add(indicadorROE);
		listaIndicadoresTwitter.add(indicadorROA);
		listaIndicadoresTwitter.add(indicadorDeudaTwitter);
		
		facebook = new Empresa("Facebook",listaCuentasFacebook,listaIndicadoresFacebook, LocalDate.now());
		google = new Empresa("Google",listaCuentasGoogle,listaIndicadoresGoogle, LocalDate.now().minusYears(50));
		twitter = new Empresa("Twitter",listaCuentasTwitter,listaIndicadoresTwitter, LocalDate.now().minusYears(20));
		
		//Condiciones
		unaLongevidad = new Longevidad(10);
		maxIndicador = new MaximizarIndicador(indicadorROE);
		margenCreciente = new MargenCreciente();
		minIndicador = new MinimizarIndicador(indicadorROA);
		
		repoEmpresas = new RepoEmpresas("repoEmpresas");
		repoEmpresas.agregarEmpresa(facebook);
		repoEmpresas.agregarEmpresa(google);
		repoEmpresas.agregarEmpresa(twitter);
		List<Condicion> condiciones = new ArrayList<>();
		condiciones.add(unaLongevidad);
		condiciones.add(maxIndicador);
		condiciones.add(minIndicador);
		condiciones.add(margenCreciente);
		metodologiaBuffet = new Metodologia("Metodologia Buffet",condiciones);
		metodologiaBuffet.setUsuario(usuario1);

		em = emf.createEntityManager();
		em.getTransaction().begin();
		
			em.persist(facebook);
					
		em.getTransaction().commit();
			
	}
}

