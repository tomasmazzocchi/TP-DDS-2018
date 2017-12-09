package ar.edu.utn.dds.GUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import DTO.CuentaDTO;
import DTO.EmpresaDTO;
import DTO.IndicadorDTO;
import DTO.MetodologiaDTO;
import DTO.UsuarioDTO;
import DTO.Condiciones.LongevidadDTO;
import DTO.Condiciones.MargenCrecienteDTO;
import DTO.Condiciones.MaximizarIndicadorDTO;
import DTO.Condiciones.MinimizarIndicadorDTO;
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
		
		facebook = new EmpresaDTO("Facebook",listaCuentasFacebook,listaIndicadoresFacebook, LocalDate.now());
		google = new EmpresaDTO("Google",listaCuentasGoogle,listaIndicadoresGoogle, LocalDate.now().minusYears(50));
		twitter = new EmpresaDTO("Twitter",listaCuentasTwitter,listaIndicadoresTwitter, LocalDate.now().minusYears(20));
		
		indicadorROEFacebook = new IndicadorDTO("ROE", "cu.EBIDTA",usuario1);
		indicadorROAFacebook = new IndicadorDTO("ROA", "cu.Margen",usuario1);
		indicadorDeudaFacebook = new IndicadorDTO("DEUDA","300",usuario1);
		indicadorROEGoogle = new IndicadorDTO("ROE", "cu.EBIDTA",usuario2);
		indicadorROAGoogle = new IndicadorDTO("ROA", "cu.Margen",usuario2);
		indicadorDeudaGoogle = new IndicadorDTO("DEUDA","400",usuario2);
		indicadorROETwitter = new IndicadorDTO("ROE", "cu.EBIDTA",usuario2);
		indicadorROATwitter= new IndicadorDTO("ROA", "cu.Margen",usuario2);
		indicadorDeudaTwitter = new IndicadorDTO("DEUDA","1000",usuario2);
		
		listaIndicadoresFacebook.add(indicadorROEFacebook);
		listaIndicadoresFacebook.add(indicadorROAFacebook);
		listaIndicadoresFacebook.add(indicadorDeudaFacebook);
		listaIndicadoresGoogle.add(indicadorROEGoogle);
		listaIndicadoresGoogle.add(indicadorROAGoogle);
		listaIndicadoresGoogle.add(indicadorDeudaGoogle);
		listaIndicadoresTwitter.add(indicadorROETwitter);
		listaIndicadoresTwitter.add(indicadorROATwitter);
		listaIndicadoresTwitter.add(indicadorDeudaTwitter);
		
		
		facebook.setUsuarioAsociado(usuario1);
		google.setUsuarioAsociado(usuario2);
		twitter.setUsuarioAsociado(usuario2);
		
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

