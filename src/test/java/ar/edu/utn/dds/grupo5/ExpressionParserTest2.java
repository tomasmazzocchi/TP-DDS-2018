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
public class ExpressionParserTest2 {
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
		indicadorROE = new Indicador("ROE","20");  /* Cambio 08 */
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		listaCuentas.add(cuentaEBIDTA);
		listaCuentas.add(cuentafds);
		listaIndicadores.add(indicadorROE);
    }
    
    @Test
    public void ingresoUnaFormulaConUnIndicador() {
        assertThat(_parser.parse("(in.ROE+1)*2",listaCuentas, listaIndicadores), equalTo(42));
    }


    


}
