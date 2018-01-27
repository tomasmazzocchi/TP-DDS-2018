package ar.edu.utn.dds.GUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import DTO.CondicionDTO;
import DTO.IndicadorDTO;
import DTO.MetodologiaDTO;
import DTO.UsuarioDTO;
import DTO.Condiciones.LongevidadDTO;
import DTO.Condiciones.MargenCrecienteDTO;
import DTO.Condiciones.MaximizarIndicadorDTO;
import DTO.Condiciones.MinimizarIndicadorDTO;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.RepoEmpresas;
import ar.edu.utn.dds.rest.EMFactorySingleton;


public class BDloader {
	private static EntityManager em = EMFactorySingleton.entityManager();
	private static UsuarioDTO usuario1;
	private static UsuarioDTO usuario2;
	private static UsuarioDTO usuario3;
	private static List<Cuenta> listaCuentasFacebook = new ArrayList<>();
	private static List<Cuenta> listaCuentasGoogle = new ArrayList<>();
	private static List<Cuenta> listaCuentasTwitter = new ArrayList<>();
	private static List<Indicador> listaIndicadoresFacebook = new ArrayList<>();
	private static List<Indicador> listaIndicadoresGoogle = new ArrayList<>();
	private static List<Indicador> listaIndicadoresTwitter = new ArrayList<>();
	private static Empresa facebook;
	private static Empresa google;
	private static Empresa twitter;
	private static MetodologiaDTO metodologiaBuffetTwitter;
	private static LongevidadDTO unaLongevidad;
	private static IndicadorDTO indicadorROEFacebook;
	private static IndicadorDTO indicadorROAFacebook;
	private static IndicadorDTO indicadorROEGoogle;
	private static IndicadorDTO indicadorROAGoogle;
	private static IndicadorDTO indicadorROETwitter;
	private static IndicadorDTO indicadorROATwitter;
	private static IndicadorDTO indicadorDeudaFacebook;
	private static IndicadorDTO indicadorDeudaGoogle;
	private static IndicadorDTO indicadorDeudaTwitter;
	private static MaximizarIndicadorDTO maxIndicador;
	private static MinimizarIndicadorDTO minIndicador;
	private static MargenCrecienteDTO margenCreciente;
	private static Cuenta cuentaEBIDTAFacebook;
	private static Cuenta cuentaMargenFacebook;
	private static Cuenta cuentaEBIDTAGoogle;
	private static Cuenta cuentaMARGENGooge;
	private static Cuenta cuentaEBIDTATwitter;
	private static Cuenta cuentaMARGENTwitter;

	public static void main(String[] args) {
			//Definiciones
		
		usuario1 = new UsuarioDTO("pablo","1234");
		usuario2 = new UsuarioDTO("juli","1234");
		usuario3 = new UsuarioDTO("tomi","1234");
		
		cuentaEBIDTAFacebook = new Cuenta("EBIDTA", 100, LocalDate.now(), LocalDate.now());
		cuentaMargenFacebook = new Cuenta("Margen", 200, LocalDate.now(), LocalDate.now());
		cuentaEBIDTAGoogle = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentaMARGENGooge = new Cuenta("Margen", 300, LocalDate.now(), LocalDate.now());
		cuentaEBIDTATwitter = new Cuenta("EBIDTA", 300,LocalDate.now(), LocalDate.now());
		cuentaMARGENTwitter = new Cuenta("Margen", 400,LocalDate.now(), LocalDate.now());
		
		listaCuentasFacebook.add(cuentaEBIDTAFacebook);
		listaCuentasFacebook.add(cuentaMargenFacebook);
		listaCuentasGoogle.add(cuentaEBIDTAGoogle);
		listaCuentasGoogle.add(cuentaMARGENGooge);
		listaCuentasTwitter.add(cuentaEBIDTATwitter);
		listaCuentasTwitter.add(cuentaMARGENTwitter);

		indicadorROEFacebook = new IndicadorDTO("ROE", "cu.EBIDTA",facebook,usuario1);
		indicadorROAFacebook = new IndicadorDTO("ROA", "cu.Margen",facebook,usuario1);
		indicadorDeudaFacebook = new IndicadorDTO("DEUDA","300",facebook,usuario1);
		indicadorROEGoogle = new IndicadorDTO("ROE", "cu.EBIDTA",google,usuario2);
		indicadorROAGoogle = new IndicadorDTO("ROA", "cu.Margen",google,usuario2);
		indicadorDeudaGoogle = new IndicadorDTO("DEUDA","400",google,usuario2);
		indicadorROETwitter = new IndicadorDTO("ROE", "cu.EBIDTA",twitter,usuario2);
		indicadorROATwitter= new IndicadorDTO("ROA", "cu.Margen",twitter,usuario2);
		indicadorDeudaTwitter = new IndicadorDTO("DEUDA","1000",twitter,usuario2);
		
		listaIndicadoresFacebook.add(AdapterIndicador.adaptar(indicadorROEFacebook));
		listaIndicadoresFacebook.add(AdapterIndicador.adaptar(indicadorROAFacebook));
		listaIndicadoresFacebook.add(AdapterIndicador.adaptar(indicadorDeudaFacebook));
		listaIndicadoresTwitter.add(AdapterIndicador.adaptar(indicadorROETwitter));
		listaIndicadoresTwitter.add(AdapterIndicador.adaptar(indicadorROATwitter));
		listaIndicadoresTwitter.add(AdapterIndicador.adaptar(indicadorDeudaTwitter));
		listaIndicadoresGoogle.add(AdapterIndicador.adaptar(indicadorROEGoogle));
		listaIndicadoresGoogle.add(AdapterIndicador.adaptar(indicadorROAGoogle));
		listaIndicadoresGoogle.add(AdapterIndicador.adaptar(indicadorDeudaGoogle));
		
		facebook = new Empresa("Facebook",listaCuentasFacebook,listaIndicadoresFacebook,LocalDate.now());
		google = new Empresa("Google",listaCuentasGoogle,listaIndicadoresGoogle,LocalDate.now().minusYears(50));
		twitter = new Empresa("Twitter",listaCuentasTwitter,listaIndicadoresTwitter,LocalDate.now().minusYears(20));
				
		
		//Condiciones
		unaLongevidad = new LongevidadDTO(10);
		maxIndicador = new MaximizarIndicadorDTO(indicadorROETwitter);
		margenCreciente = new MargenCrecienteDTO();
		minIndicador = new MinimizarIndicadorDTO(indicadorROATwitter);
		
		List<CondicionDTO> condiciones = new ArrayList<>();
		condiciones.add(unaLongevidad);
		condiciones.add(maxIndicador);
		condiciones.add(minIndicador);
		condiciones.add(margenCreciente);

		metodologiaBuffetTwitter = new MetodologiaDTO("Metodologia Buffet Twitter",condiciones,usuario1);
		

		em.getTransaction().begin();
		
		//usuarios
		em.persist(usuario1);
		em.persist(usuario2);
		em.persist(usuario3);
		
		//Metodologias
		em.persist(metodologiaBuffetTwitter);
		
		// empresas
		em.persist(facebook);
		em.persist(google);
		em.persist(twitter);
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas.add(facebook);
		empresas.add(google);
		empresas.add(twitter);
		RepoEmpresas.agregarEmpresas(empresas);		

		// cuentas
		em.persist(cuentaEBIDTAFacebook);
		em.persist(cuentaMargenFacebook);
		em.persist(cuentaEBIDTAGoogle);
		em.persist(cuentaMARGENGooge);
		em.persist(cuentaEBIDTATwitter);
		em.persist(cuentaMARGENTwitter);
		
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
		
		//Condiciones
		em.persist(unaLongevidad);
		em.persist(maxIndicador); 
		em.persist(margenCreciente); 
		em.persist(minIndicador);
		
		
		em.getTransaction().commit();
		em.clear();
			
	}
}

