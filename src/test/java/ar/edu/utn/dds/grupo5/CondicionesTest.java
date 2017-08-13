package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Condiciones.MargenCreciente;
import ar.edu.utn.dds.grupo5.Condiciones.Longevidad;
import ar.edu.utn.dds.grupo5.Condiciones.MaximizarIndicador;
import ar.edu.utn.dds.grupo5.Condiciones.MinimizarIndicador;


public class CondicionesTest {
	private List<Cuenta> listaCuentasFacebook = new ArrayList<>();
	private List<Cuenta> listaCuentasGoogle = new ArrayList<>();
	private List<Cuenta> listaCuentasTwitter = new ArrayList<>();
	private List<Indicador> listaIndicadoresFacebook;
	private List<Indicador> listaIndicadoresGoogle;
	private List<Indicador> listaIndicadoresTwitter;
	private Empresa facebook;
	private Empresa google;
	private Empresa twitter;
	private RepoEmpresas repoEmpresas;
	private Metodologia metodologiaBuffet;
	private Longevidad unaLongevidad;
	private Indicador indicadorROE;
	private Indicador indicadorROA;
	private Indicador indicadorDeudaFacebook;
	private Indicador indicadorDeudaGoogle;
	private Indicador indicadorDeudaTwitter;
	private MaximizarIndicador maxIndicador;
	private MinimizarIndicador minIndicador;
	private MargenCreciente margenCreciente;
	private Cuenta cuentaEBIDTAFacebook;
	private Cuenta cuentaMargenFacebook;
	private Cuenta cuentaEBIDTAGoogle;
	private Cuenta cuentaMARGENGooge;
	private Cuenta cuentaEBIDTATwitter;
	private Cuenta cuentaMARGENTwitter;

	@Before
	public void setup() {
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
		metodologiaBuffet = new Metodologia(condiciones);
	}

	@After
	public void after() {
		metodologiaBuffet.getResultados().clear();
		metodologiaBuffet.getCondiciones().clear();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void ingresaListadoDeEmpresasYDevuelvoLaDeMayorLongevidad() {
		List<Empresa> resultado = unaLongevidad.aplicarCondicion(repoEmpresas.getListaEmpresa());
 		Assert.assertEquals(resultado.get(0),google);
	}
	
	@Test
	public void ingresaListadoDeEmpresasYDevuelvoLaDeMayorROE(){
		List<Empresa> resultado = maxIndicador.aplicarCondicion(repoEmpresas.getListaEmpresa());
		Assert.assertEquals(resultado.get(0), twitter);
 	}	
	
	@Test
	public void ingresaListadoDeEmpresasYDevuelvoLaDeMenorROA(){
		List<Empresa> resultado = minIndicador.aplicarCondicion(repoEmpresas.getListaEmpresa());
		Assert.assertEquals(resultado.get(0), facebook);
	}	
	
	@Test
	public void ingresaListadoDeEmpresasYDevuelvoLaDeMargenCreciente(){
		List<Empresa> resultado = margenCreciente.aplicarCondicion(repoEmpresas.getListaEmpresa());
		Assert.assertEquals(resultado.get(0), facebook);
	}	

	@Test
	public void aplicarMetodologiaBuffet() {
		metodologiaBuffet.aplicarCondiciones(repoEmpresas.getListaEmpresa());
		
		Assert.assertEquals(metodologiaBuffet.getResultados().get(unaLongevidad.getNombre()).get(0),google);
		//El test falla por la condicion maxIndicador que no ordena bien la lista al momento de guardarla en el hash
		//Individualmente la condicion funciona perfectamente, no se porque rompe cuando quiero aplicar todas las condiciones juntas
		Assert.assertEquals(metodologiaBuffet.getResultados().get(maxIndicador.getNombre()).get(0),twitter);
		Assert.assertEquals(metodologiaBuffet.getResultados().get(minIndicador.getNombre()).get(0), facebook);
		Assert.assertEquals(metodologiaBuffet.getResultados().get(margenCreciente.getNombre()).get(0), facebook);
	}
	
}
