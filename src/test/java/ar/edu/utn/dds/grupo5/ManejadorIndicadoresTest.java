package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.utn.dds.ExceptionHandler.ArgumentoIlegalException;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.ExpressionParser;
import ar.edu.utn.dds.grupo5.Indicador;

public class ManejadorIndicadoresTest {
	ExpressionParser parser;
	private RepoIndicadores repoIndicadores;
	private Cuenta cuentaEBIDTA;
	private Cuenta cuentafds;
	private Indicador indicadorROE;
	private Indicador indicadorDIV;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;
	private Empresa empresaTest;

	@Before
	public void setup() {
		repoIndicadores = new RepoIndicadores("repoIndiG5");
		parser = new ExpressionParser();
		cuentaEBIDTA = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentafds = new Cuenta("fds", 200, LocalDate.now(), LocalDate.now());
		indicadorROE = new Indicador("ROE", "20"); 
		indicadorDIV = new Indicador("DIV", "cu.EBIDTA"); 
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		listaCuentas.add(cuentaEBIDTA);
		listaCuentas.add(cuentafds);
		listaIndicadores.add(indicadorROE);
		listaIndicadores.add(indicadorDIV);
		empresaTest = new Empresa("HSBC",listaCuentas,listaIndicadores,LocalDate.now());
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void siGuardoIndicadorEnRepoConFormulaCorrecta() {
		repoIndicadores.guardarIndicadorEnRepo("indicador1", "(cu.EBIDTA+1)*2");
		Assert.assertEquals(1, (repoIndicadores.getListaIndicadores().size()));
	}

	@Test
	public void siGuardoIndicadorEnRepoConFormulaIncorrecta() {
		thrown.expect(ArgumentoIlegalException.class);
		thrown.expectMessage("Formula no Valida");
		repoIndicadores.guardarIndicadorEnRepo("indicador1", "(pepe+1)*2");
	}
	@Test
	public void siGuardoIndicadorEnUnaEmpresaConFormulaCorrectaEntoncesSeGuarda(){
		repoIndicadores.guardarIndicadorEnEmpresa(empresaTest, "indicador1", "(cu.EBIDTA+1)*2");
		Indicador ind = RepoIndicadores.buscarIndicador("indicador1", empresaTest.getListaIndicadores());
		Assert.assertEquals(ind.getNombre(),"indicador1");
	}
	@Test
	public void siGuardoIndicadorEnUnaEmpresaConFormulaIncorrectaEntoncesNoSeGuarda(){
		thrown.expect(ArgumentoIlegalException.class);
		thrown.expectMessage("Formula no Valida");
		repoIndicadores.guardarIndicadorEnEmpresa(empresaTest, "indicador1", "(pepe+1)*2");
	}
}
