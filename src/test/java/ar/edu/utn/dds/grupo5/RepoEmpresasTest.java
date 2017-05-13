package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.utn.dds.ExceptionHandler.EmpresaExistenteException;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.RepoEmpresas;


public class RepoEmpresasTest {
    
	private Empresa empresa1,empresa2,empresa3;
	private Cuenta cuentaEBIDTA;
	private Cuenta cuentaFDS;
	private Indicador indicadorMayorQue;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;
	private RepoEmpresas repoEmpresas;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void init(){
		cuentaEBIDTA = new Cuenta("EBIDTA",200,LocalDate.now(),LocalDate.now());
		cuentaFDS = new Cuenta("FDS",500,LocalDate.now(),LocalDate.now());
		indicadorMayorQue = new Indicador("MayorQue","200");  /* Cambio 08 */
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		listaCuentas.add(cuentaEBIDTA);
		listaCuentas.add(cuentaFDS);
		listaIndicadores.add(indicadorMayorQue);
		empresa1=new Empresa("Facebook",listaCuentas,listaIndicadores);
		empresa2=new Empresa("Google",listaCuentas,listaIndicadores);
		empresa3=new Empresa("Apple",listaCuentas,listaIndicadores);
		repoEmpresas = new RepoEmpresas("repoG5");
	}
	
	@After
	public void limpiarRepo(){
		repoEmpresas.reset();
	}
	
	@Test
	public void siAgregoTresEmpresasAlRepoVerificoQueSeAgregaronTres() {
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.agregarEmpresa(empresa2);
		repoEmpresas.agregarEmpresa(empresa3);
		Assert.assertEquals(3, (repoEmpresas.getListaEmpresa().size()));
	}
	
	@Test
	public void agregoUnaEmpresExistenteLanzaExcepcion() {
		thrown.expect(EmpresaExistenteException.class);
		thrown.expectMessage("Empresa ya existente");
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.agregarEmpresa(empresa2);
		repoEmpresas.agregarEmpresa(empresa3);
		repoEmpresas.agregarEmpresa(empresa1);
	}
	
	@Test
	public void eliminoUnaEmpresaExistente() {
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.agregarEmpresa(empresa2);
		repoEmpresas.agregarEmpresa(empresa3);
		repoEmpresas.quitarEmpresa(empresa1);
		Assert.assertEquals(2, repoEmpresas.getListaEmpresa().size());
	}
	
	@Test
	public void eliminoUnaEmpresaInexistenteLanzaExcepcion() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("No existe la empresa");
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.quitarEmpresa(empresa2);
	}

	@Test
	public void agregoEmpresaConDosCuentasYVerificoQueSeHayanAgregadoCorrectamente(){
		repoEmpresas.agregarEmpresa(empresa1);
		Assert.assertEquals(2, repoEmpresas.getListaEmpresa().get(0).getListaCuentas().size());
	}

	@Test
	public void agregoEmpresaConUnIndicadorYVerificoQueSeHayanAgregadoCorrectamente(){
		repoEmpresas.agregarEmpresa(empresa1);
		Assert.assertEquals(1, repoEmpresas.getListaEmpresa().get(0).getListaIndicadores().size());
	}
	
}
