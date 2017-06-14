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


public class MetodologiasTest {
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;
	private Empresa empresa1;
	private Empresa empresa2;
	private Empresa empresa3;
	private Empresa empresa4;
	private Empresa empresa5;
	private RepoEmpresas repoEmpresas;
	private Longevidad unaLongevidad;

	@Before
	public void setup() {
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		empresa1 = new Empresa("Facebook",listaCuentas,listaIndicadores, LocalDate.now().minusYears(11));
		empresa2 = new Empresa("Google",listaCuentas,listaIndicadores, LocalDate.now().minusYears(8));
		empresa3 = new Empresa("Microsoft",listaCuentas,listaIndicadores, LocalDate.now().minusYears(10));
		empresa4 = new Empresa("Samsung",listaCuentas,listaIndicadores, LocalDate.now().minusYears(9));
		empresa5 = new Empresa("Philco",listaCuentas,listaIndicadores, LocalDate.now().minusYears(12));
		repoEmpresas = new RepoEmpresas("repoEmpresas");
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.agregarEmpresa(empresa2);
		repoEmpresas.agregarEmpresa(empresa3);
		repoEmpresas.agregarEmpresa(empresa4);
		repoEmpresas.agregarEmpresa(empresa5);
		unaLongevidad = new Longevidad(10);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void ingresoUnRepoConEmpresasYDevuelvoLaDeMayorLongevidad() {
		unaLongevidad.aplicarCondicion(repoEmpresas.getListaEmpresa());
		Assert.assertEquals(repoEmpresas.getListaEmpresa().get(0),empresa5);
	}

	

}
