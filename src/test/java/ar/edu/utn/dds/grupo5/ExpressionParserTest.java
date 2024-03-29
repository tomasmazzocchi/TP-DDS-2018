package ar.edu.utn.dds.grupo5;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.utn.dds.ExceptionHandler.ArgumentoIlegalException;
import ar.edu.utn.dds.ExceptionHandler.ExpressionParserException;
import ar.edu.utn.dds.ExceptionHandler.RepoIndicadoresException;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.ExpressionParser;
import ar.edu.utn.dds.grupo5.Indicador;

public class ExpressionParserTest {
	private ExpressionParser _parser;
	private Cuenta cuentaEBIDTA;
	private Cuenta cuentafds;
	private Empresa empresa;
	private Indicador indicadorROE;
	private Indicador indicadorDIV;
	private Indicador indicadorPOR;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;

	@Before
	public void setup() {
		_parser = new ExpressionParser();
		cuentaEBIDTA = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentafds = new Cuenta("fds", 200, LocalDate.now(), LocalDate.now());
		indicadorROE = new Indicador("ROE", "20");
		indicadorDIV = new Indicador("DIV", "cu.EBIDTA");
		indicadorPOR = new Indicador("POR", "in.DIV");
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		listaCuentas.add(cuentaEBIDTA);
		listaCuentas.add(cuentafds);
		listaIndicadores.add(indicadorROE);
		listaIndicadores.add(indicadorDIV);
		listaIndicadores.add(indicadorPOR);
		empresa = new Empresa("Facebook", listaCuentas, listaIndicadores, LocalDate.now());
		RepoEmpresas.limpiarListaEmpresas();
		RepoEmpresas.getListaEmpresa().add(empresa);
		RepoIndicadores.setearIndicadores(listaIndicadores);

	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void ingresoUnaFormulaConUnaCuentaExistente() {
		assertThat(_parser.resolverFormula("(cu.EBIDTA+1)*2", empresa), equalTo(402));
	}

	@Test
	public void ingresoFormulaConDosCuentasExistentes() {
		assertThat(_parser.resolverFormula("(cu.EBIDTA+cu.fds)", empresa), equalTo(400));
	}

	@Test
	public void ingresoFormulaConCaracterInvalido() {
		thrown.expect(ArgumentoIlegalException.class);
		thrown.expectMessage(containsString("Formula no v�lida"));
		_parser.resolverFormula("(cu.EBIDTA # 2)", empresa);
	}

	@Test
	public void ingresoUnaFormulaConUnIndicador() {
		assertThat(_parser.resolverFormula("(in.ROE+1)*2", empresa), equalTo(42));
	}

	@Test
	public void ingresoUnaFormulaConUnIndicadoryCuentas() {
		assertThat(_parser.resolverFormula("(in.DIV+cu.EBIDTA)/2", empresa), equalTo(200));
	}

	@Test
	public void ingresoUnaFormulaConCuentaInexistente() {
		thrown.expect(ExpressionParserException.class);
		thrown.expectMessage("No existe la cuenta pepe en la empresa Facebook");
		_parser.resolverFormula("cu.pepe/2", empresa);
	}

	@Test
	public void ingresoUnaFormulaConIndicadorInexistente() {
		thrown.expect(RepoIndicadoresException.class);
		thrown.expectMessage("No existe el nombre del Indicador");
		_parser.resolverFormula("in.pepe/2", empresa);
	}

	@Test
	public void ingresoUnaFormulaConIndicadorExistenteQueTieneComoFormulaOtroIndicador() {
		assertThat(_parser.resolverFormula("in.POR*2", empresa), equalTo(400));
	}

}
