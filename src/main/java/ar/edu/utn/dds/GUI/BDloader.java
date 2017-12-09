package ar.edu.utn.dds.GUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import DTO.CondicionDTO;
import DTO.CuentaDTO;
import DTO.EmpresaDTO;
import DTO.IndicadorDTO;
import DTO.MetodologiaDTO;
import DTO.UsuarioDTO;
import DTO.Condiciones.LongevidadDTO;
import DTO.Condiciones.MargenCrecienteDTO;
import DTO.Condiciones.MaximizarIndicadorDTO;
import DTO.Condiciones.MinimizarIndicadorDTO;
import ar.edu.utn.dds.grupo5.RepoEmpresas;
import ar.edu.utn.dds.rest.EMFactorySingleton;


public class BDloader {
	private static EntityManager em = EMFactorySingleton.entityManager();
	private static UsuarioDTO usuario1;
	private static UsuarioDTO usuario2;
	private static UsuarioDTO usuario3;
	private static List<CuentaDTO> listaCuentasFacebook = new ArrayList<>();
	private static List<CuentaDTO> listaCuentasGoogle = new ArrayList<>();
	private static List<CuentaDTO> listaCuentasTwitter = new ArrayList<>();
	private static List<IndicadorDTO> listaIndicadoresFacebook = new ArrayList<>();
	private static List<IndicadorDTO> listaIndicadoresGoogle = new ArrayList<>();
	private static List<IndicadorDTO> listaIndicadoresTwitter = new ArrayList<>();
	private static EmpresaDTO facebook;
	private static EmpresaDTO google;
	private static EmpresaDTO twitter;
	private static RepoEmpresas repoEmpresas;
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
	private static CuentaDTO cuentaEBIDTAFacebook;
	private static CuentaDTO cuentaMargenFacebook;
	private static CuentaDTO cuentaEBIDTAGoogle;
	private static CuentaDTO cuentaMARGENGooge;
	private static CuentaDTO cuentaEBIDTATwitter;
	private static CuentaDTO cuentaMARGENTwitter;

	public static void main(String[] args) {
			//Definiciones
		
		usuario1 = new UsuarioDTO("pablo","1234");
		usuario2 = new UsuarioDTO("juli","1234");
		usuario3 = new UsuarioDTO("tomi","1234");
		
		cuentaEBIDTAFacebook = new CuentaDTO( LocalDate.now(), LocalDate.now(),"EBIDTA", 100,usuario1);
		cuentaMargenFacebook = new CuentaDTO( LocalDate.now(), LocalDate.now(),"Margen", 200,usuario2);
		cuentaEBIDTAGoogle = new CuentaDTO( LocalDate.now(), LocalDate.now(),"EBIDTA", 200,usuario1);
		cuentaMARGENGooge = new CuentaDTO( LocalDate.now(), LocalDate.now(),"Margen", 300,usuario1);
		cuentaEBIDTATwitter = new CuentaDTO(LocalDate.now(), LocalDate.now(),"EBIDTA", 300, usuario2);
		cuentaMARGENTwitter = new CuentaDTO(LocalDate.now(), LocalDate.now(),"Margen", 400, usuario2);
		
		listaCuentasFacebook.add(cuentaEBIDTAFacebook);
		listaCuentasFacebook.add(cuentaMargenFacebook);
		listaCuentasGoogle.add(cuentaEBIDTAGoogle);
		listaCuentasGoogle.add(cuentaMARGENGooge);
		listaCuentasTwitter.add(cuentaEBIDTATwitter);
		listaCuentasTwitter.add(cuentaMARGENTwitter);
		
		facebook = new EmpresaDTO(LocalDate.now(),"Facebook",listaCuentasFacebook,listaIndicadoresFacebook,usuario1);
		google = new EmpresaDTO(LocalDate.now().minusYears(50),"Google",listaCuentasGoogle,listaIndicadoresGoogle, usuario2);
		twitter = new EmpresaDTO(LocalDate.now().minusYears(20),"Twitter",listaCuentasTwitter,listaIndicadoresTwitter, usuario2);
		
		indicadorROEFacebook = new IndicadorDTO("ROE", "cu.EBIDTA",facebook.getId_empresa(),usuario1);
		indicadorROAFacebook = new IndicadorDTO("ROA", "cu.Margen",facebook.getId_empresa(),usuario1);
		indicadorDeudaFacebook = new IndicadorDTO("DEUDA","300",facebook.getId_empresa(),usuario1);
		indicadorROEGoogle = new IndicadorDTO("ROE", "cu.EBIDTA",google.getId_empresa(),usuario2);
		indicadorROAGoogle = new IndicadorDTO("ROA", "cu.Margen",google.getId_empresa(),usuario2);
		indicadorDeudaGoogle = new IndicadorDTO("DEUDA","400",google.getId_empresa(),usuario2);
		indicadorROETwitter = new IndicadorDTO("ROE", "cu.EBIDTA",twitter.getId_empresa(),usuario2);
		indicadorROATwitter= new IndicadorDTO("ROA", "cu.Margen",twitter.getId_empresa(),usuario2);
		indicadorDeudaTwitter = new IndicadorDTO("DEUDA","1000",twitter.getId_empresa(),usuario2);
		
		facebook.getListaIndicadores().add(indicadorROEFacebook);
		facebook.getListaIndicadores().add(indicadorROAFacebook);
		facebook.getListaIndicadores().add(indicadorDeudaFacebook);
		google.getListaIndicadores().add(indicadorROEGoogle);
		google.getListaIndicadores().add(indicadorROAGoogle);
		google.getListaIndicadores().add(indicadorDeudaGoogle);
		twitter.getListaIndicadores().add(indicadorROETwitter);
		twitter.getListaIndicadores().add(indicadorROATwitter);
		twitter.getListaIndicadores().add(indicadorDeudaTwitter);
		
		
		//Condiciones
		unaLongevidad = new LongevidadDTO(10);
		maxIndicador = new MaximizarIndicadorDTO(indicadorROETwitter);
		margenCreciente = new MargenCrecienteDTO();
		minIndicador = new MinimizarIndicadorDTO(indicadorROATwitter);
		
		//repoEmpresas = new RepoEmpresas("repoEmpresas");
		//repoEmpresas.agregarEmpresa(facebook);
		//repoEmpresas.agregarEmpresa(google);
		//repoEmpresas.agregarEmpresa(twitter);
		List<CondicionDTO> condiciones = new ArrayList<>();
		condiciones.add(unaLongevidad);
		condiciones.add(maxIndicador);
		condiciones.add(minIndicador);
		condiciones.add(margenCreciente);

		metodologiaBuffetTwitter = new MetodologiaDTO("Metodologia Buffet Twitter",condiciones,usuario1);
		

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
		em.persist(usuario3);
							
		em.getTransaction().commit();
		em.clear();
		
			
	}
}

