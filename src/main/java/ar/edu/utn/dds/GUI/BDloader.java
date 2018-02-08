package ar.edu.utn.dds.GUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Metodologia;
import ar.edu.utn.dds.grupo5.RepoEmpresas;
import ar.edu.utn.dds.grupo5.Usuario;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionCuenta;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionIndicador;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionNumero;
import ar.edu.utn.dds.rest.EMFactorySingleton;

public class BDloader {
	private static EntityManager em = EMFactorySingleton.entityManager();
	private static Usuario usuario1;
	private static Usuario usuario2;
	private static Usuario usuario3;
	private static List<Cuenta> listaCuentasFacebook = new ArrayList<>();
	private static List<Cuenta> listaCuentasGoogle = new ArrayList<>();
	private static List<Cuenta> listaCuentasTwitter = new ArrayList<>();
	private static List<Cuenta> listaCuentasRenault = new ArrayList<>();
	private static List<Indicador> listaIndicadoresFacebook = new ArrayList<>();
	private static List<Indicador> listaIndicadoresGoogle = new ArrayList<>();
	private static List<Indicador> listaIndicadoresTwitter = new ArrayList<>();
	private static List<Indicador> listaIndicadoresRenault = new ArrayList<>();
	private static Empresa facebook;
	private static Empresa google;
	private static Empresa twitter;
	private static Empresa renault;
	private static Metodologia metodologiaBuffetTwitter;
	private static Indicador indicadorROEFacebook;
	private static Indicador indicadorROAFacebook;
	private static Indicador indicadorROEGoogle;
	private static Indicador indicadorROAGoogle;
	private static Indicador indicadorROETwitter;
	private static Indicador indicadorROATwitter;
	private static Indicador indicadorROARenault;
	private static Indicador indicadorDeudaRenault;
	private static Indicador indicadorDeudaFacebook;
	private static Indicador indicadorDeudaGoogle;
	private static Indicador indicadorDeudaTwitter;
	private static CondicionNumero longevidad;
	private static CondicionIndicador maximizarROE;
	private static CondicionIndicador minimizarROA;
	private static CondicionCuenta margenCreciente;
	private static Cuenta cuentaEBIDTAFacebook;
	private static Cuenta cuentaMargenFacebook;
	private static Cuenta cuentaEBIDTAGoogle;
	private static Cuenta cuentaMARGENGooge;
	private static Cuenta cuentaEBIDTATwitter;
	private static Cuenta cuentaMARGENTwitter;
	private static Cuenta cuentaEBIDTARenault;
	private static Cuenta cuentaMARGENRenault;

	public static void main(String[] args) {
		// Definiciones

		usuario1 = new Usuario("admin", "1234");
		usuario2 = new Usuario("juli", "1234");
		usuario3 = new Usuario("pablo", "1234");

		cuentaEBIDTAFacebook = new Cuenta("EBIDTA", 100, LocalDate.now(), LocalDate.now().plusDays(382));
		cuentaMargenFacebook = new Cuenta("Margen", 200, LocalDate.now().minusWeeks(31), LocalDate.now().plusMonths(6));
		cuentaEBIDTAGoogle = new Cuenta("EBIDTA", 200, LocalDate.now().minusMonths(3), LocalDate.now().plusWeeks(100));
		cuentaMARGENGooge = new Cuenta("Margen", 300, LocalDate.now().minusDays(360), LocalDate.now().plusYears(7));
		cuentaEBIDTATwitter = new Cuenta("EBIDTA", 300, LocalDate.now().minusWeeks(200), LocalDate.now().plusDays(89));
		cuentaMARGENTwitter = new Cuenta("Margen", 400, LocalDate.now().minusYears(4), LocalDate.now().plusMonths(10));
		cuentaEBIDTARenault = new Cuenta("EBIDTA", 400, LocalDate.now().minusDays(125), LocalDate.now().plusWeeks(59));
		cuentaMARGENRenault = new Cuenta("Margen", 500, LocalDate.now().minusMonths(9), LocalDate.now().plusYears(3));

		listaCuentasFacebook.add(cuentaEBIDTAFacebook);
		listaCuentasFacebook.add(cuentaMargenFacebook);
		listaCuentasGoogle.add(cuentaEBIDTAGoogle);
		listaCuentasGoogle.add(cuentaMARGENGooge);
		listaCuentasTwitter.add(cuentaEBIDTATwitter);
		listaCuentasTwitter.add(cuentaMARGENTwitter);
		listaCuentasRenault.add(cuentaMARGENRenault);
		listaCuentasRenault.add(cuentaMARGENRenault);
		
		indicadorROEFacebook = new Indicador("ROE", "cu.EBIDTA");
		indicadorROAFacebook = new Indicador("ROA", "cu.Margen");
		indicadorDeudaFacebook = new Indicador("DEUDA", "300");
		indicadorROEGoogle = new Indicador("ROE", "cu.EBIDTA");
		indicadorROAGoogle = new Indicador("ROA", "cu.Margen");
		indicadorDeudaGoogle = new Indicador("DEUDA", "400");
		indicadorROETwitter = new Indicador("ROE", "cu.EBIDTA");
		indicadorROATwitter = new Indicador("ROA", "cu.Margen");
		indicadorDeudaTwitter = new Indicador("DEUDA", "1000");
		indicadorROARenault = new Indicador("ROA","cu.Margen");
		indicadorDeudaRenault = new Indicador("DEUDA","2500");
		
		indicadorROEFacebook.setUsuario(usuario1);
		indicadorROAFacebook.setUsuario(usuario1);
		indicadorDeudaFacebook.setUsuario(usuario1);
		indicadorROEGoogle.setUsuario(usuario1);
		indicadorROAGoogle.setUsuario(usuario1);
		indicadorDeudaGoogle.setUsuario(usuario1);
		indicadorROETwitter.setUsuario(usuario1);
		indicadorROATwitter.setUsuario(usuario1);
		indicadorDeudaTwitter.setUsuario(usuario1);
		indicadorDeudaRenault.setUsuario(usuario1);
		indicadorROARenault.setUsuario(usuario1);

		listaIndicadoresFacebook.add(indicadorROEFacebook);
		listaIndicadoresFacebook.add(indicadorROAFacebook);
		listaIndicadoresFacebook.add(indicadorDeudaFacebook);
		listaIndicadoresTwitter.add(indicadorROETwitter);
		listaIndicadoresTwitter.add(indicadorROATwitter);
		listaIndicadoresTwitter.add(indicadorDeudaTwitter);
		listaIndicadoresGoogle.add(indicadorROEGoogle);
		listaIndicadoresGoogle.add(indicadorROAGoogle);
		listaIndicadoresGoogle.add(indicadorDeudaGoogle);
		listaIndicadoresRenault.add(indicadorDeudaRenault);
		listaIndicadoresRenault.add(indicadorROARenault);

		facebook = new Empresa("Facebook", listaCuentasFacebook, null, LocalDate.now());
		google = new Empresa("Google", listaCuentasGoogle, null, LocalDate.now().minusYears(50));
		twitter = new Empresa("Twitter", listaCuentasTwitter, null, LocalDate.now().minusYears(20));
		renault = new Empresa("Renault",listaCuentasRenault,null,LocalDate.now().minusYears(25));

		// Condiciones
		longevidad = new CondicionNumero("longevidad",10,10,'<');
		maximizarROE = new CondicionIndicador("Maximizar ROE", indicadorROEFacebook, 8, '>');
		minimizarROA = new CondicionIndicador("Minimizar ROA", indicadorROAFacebook, 2, '<');
		margenCreciente = new CondicionCuenta("Margen Creciente", cuentaMARGENGooge, 6, '>');

		List<Condicion> condiciones = new ArrayList<>();
		condiciones.add(maximizarROE);

		metodologiaBuffetTwitter = new Metodologia("Metodologia Buffet Twitter", condiciones);
		metodologiaBuffetTwitter.setUs(usuario1);

		em.getTransaction().begin();

		em.persist(usuario1);
		em.persist(usuario2);
		em.persist(usuario3);

		em.persist(cuentaEBIDTAFacebook);
		em.persist(cuentaMargenFacebook);
		em.persist(cuentaEBIDTAGoogle);
		em.persist(cuentaMARGENGooge);
		em.persist(cuentaEBIDTATwitter);
		em.persist(cuentaMARGENTwitter);
		em.persist(cuentaEBIDTARenault);
		em.persist(cuentaMARGENRenault);
		
		em.persist(indicadorROEFacebook);
		em.persist(indicadorDeudaTwitter);

		em.persist(facebook);
		em.persist(google);
		em.persist(twitter);
		em.persist(renault);
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas.add(facebook);
		empresas.add(google);
		empresas.add(twitter);
		empresas.add(renault);
		RepoEmpresas.agregarEmpresas(empresas);

		em.persist(longevidad);
		em.persist(maximizarROE);
		em.persist(margenCreciente);
		em.persist(minimizarROA);

		em.getTransaction().commit();
		em.clear();

	}
}
