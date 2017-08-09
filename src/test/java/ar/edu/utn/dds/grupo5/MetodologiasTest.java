package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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


public class MetodologiasTest {
	private List<Cuenta> listaCuentas1;
	private List<Cuenta> listaCuentas2;
	private List<Indicador> listaIndicadores;
	private Empresa empresa1;
	private Empresa empresa2;
	private RepoEmpresas repoEmpresas;
	private Metodologia metodologiaBuffet;
	private Longevidad unaLongevidad;
	private Indicador indicadorROE;
	private Indicador indicadorROA;
	private MaximizarIndicador maxIndicador;
	private MinimizarIndicador minIndicador;
	private MargenCreciente margenCreciente;
	private Cuenta cuentaEBIDTA1;
	private Cuenta cuentaMargen1;
	private Cuenta cuentaEBIDTA2;
	private Cuenta cuentaMARGEN2;

	@Before
	public void setup() {
		listaCuentas1 = new ArrayList<>();
		listaCuentas2 = new ArrayList<>();
		
		cuentaEBIDTA1 = new Cuenta("EBIDTA", 100, LocalDate.now(), LocalDate.now());
		cuentaMargen1 = new Cuenta("Margen", 200, LocalDate.now(), LocalDate.now());
		cuentaEBIDTA2 = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentaMARGEN2 = new Cuenta("Margen", 300, LocalDate.now(), LocalDate.now());
		
		listaCuentas1.add(cuentaEBIDTA1);
		listaCuentas1.add(cuentaMargen1);
		listaCuentas2.add(cuentaEBIDTA2);
		listaCuentas2.add(cuentaMARGEN2);
		
		listaIndicadores = new ArrayList<>();
		indicadorROE = new Indicador("ROE", "cu.EBIDTA");
		indicadorROA = new Indicador("ROA", "cu.Margen");
		listaIndicadores.add(indicadorROE);
		listaIndicadores.add(indicadorROA);
		
		empresa1 = new Empresa("Facebook",listaCuentas1,listaIndicadores, LocalDate.now());
		empresa2 = new Empresa("Google",listaCuentas2,listaIndicadores, LocalDate.now().minusYears(50));
		
		unaLongevidad = new Longevidad(10);
		maxIndicador = new MaximizarIndicador(indicadorROE);
		margenCreciente = new MargenCreciente();
		minIndicador = new MinimizarIndicador(indicadorROA);
		repoEmpresas = new RepoEmpresas("repoEmpresas");
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.agregarEmpresa(empresa2);
		List<Condicion> condiciones = new ArrayList<>();
		condiciones.add(unaLongevidad);
		condiciones.add(maxIndicador);
		condiciones.add(margenCreciente);
		condiciones.add(minIndicador);
		metodologiaBuffet = new Metodologia(condiciones);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void ingresoUnRepoConEmpresasYDevuelvoLaDeMayorLongevidad() {
		unaLongevidad.aplicarCondicion(repoEmpresas.getListaEmpresa());
		Assert.assertEquals(repoEmpresas.getListaEmpresa().get(0),empresa2);
	}
	
	@Test
	public void ingresoUnRepoConEmpresasYDevuelvoLaDeMayorROE(){
		maxIndicador.aplicarCondicion(repoEmpresas.getListaEmpresa());
		Assert.assertEquals(repoEmpresas.getListaEmpresa().get(0), empresa2);
	}	
	
	@Test
	public void ingresoUnRepoConEmpresasYDevuelvoLaDeMenorROE(){
		minIndicador.aplicarCondicion(repoEmpresas.getListaEmpresa());
		Assert.assertEquals(repoEmpresas.getListaEmpresa().get(0), empresa1);
	}	
	
	@Test
	public void ingresoUnRepoConEmpresasYDevuelvoLaDeMargenCreciente(){
		minIndicador.aplicarCondicion(repoEmpresas.getListaEmpresa());
		Assert.assertEquals(repoEmpresas.getListaEmpresa().get(0), empresa1);
	}	

	@Test
	public void aplicarMetodologiaBuffet() {
		metodologiaBuffet.aplicarCondiciones(repoEmpresas.getListaEmpresa());
		Assert.assertEquals(metodologiaBuffet.getResultados().get(maxIndicador.getNombre()).get(0),empresa1);
		Assert.assertEquals(metodologiaBuffet.getResultados().get(unaLongevidad.getNombre()).get(0),empresa1);
		Assert.assertEquals(metodologiaBuffet.getResultados().get(margenCreciente.getNombre()).get(0), empresa1);
		Assert.assertEquals(metodologiaBuffet.getResultados().get(minIndicador.getNombre()).get(0), empresa1);
	}
}
