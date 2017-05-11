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

import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.ExpressionParser;
import ar.edu.utn.dds.grupo5.Indicador;

/*
 * Test set that tests different expressions against their expected results.
 */
public class ExpressionParserTest {
    private ExpressionParser _parser;
    private Cuenta cuentaEBIDTA;
	private Cuenta cuentafds;
	private Indicador indicadorROE;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;
    @Rule
    public ExpectedException _expected = ExpectedException.none();
    

    @Before
    public void setup() {
        _parser = new ExpressionParser();
        cuentaEBIDTA = new Cuenta("EBIDTA",200,LocalDate.now(),LocalDate.now());
        cuentafds = new Cuenta("fds",200,LocalDate.now(),LocalDate.now());
		indicadorROE = new Indicador("ROE","EBIDTA");  /* Cambio 08 */
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		listaCuentas.add(cuentaEBIDTA);
		listaCuentas.add(cuentafds);
		listaIndicadores.add(indicadorROE);
    }

    @Test
    public void ingresoUnaFormulaConUnaCuentaExistente() {
        assertThat(_parser.parse("(cu.EBIDTA+1)*2",listaCuentas, listaIndicadores), equalTo(402));
    }
    
    @Test
    public void ingresonumero() {
        assertThat(_parser.parse("42",listaCuentas, listaIndicadores), equalTo(42));
    }

    @Test
    public void ingresoFormulaConDosCuentasExistentes() {
        assertThat(_parser.parse("(cu.EBIDTA+cu.fds)",listaCuentas, listaIndicadores), equalTo(400));
    }

    @Test
    public void ingresoFormulaConCaracterInvalido() {
        _expected.expect(IllegalArgumentException.class);
        _expected.expectMessage(containsString("token recognition error at: '#'"));

        assertThat(_parser.parse("(21 # 2)",listaCuentas, listaIndicadores), equalTo(42));
    }

}
