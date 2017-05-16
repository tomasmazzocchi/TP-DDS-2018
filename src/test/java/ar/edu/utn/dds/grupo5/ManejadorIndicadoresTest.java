package ar.edu.utn.dds.grupo5;

import static org.hamcrest.CoreMatchers.containsString;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
public class ManejadorIndicadoresTest {
	ExpressionParser _parser; 
	private RepoIndicadores repoIndicadores;
	private ManejadorIndicadores manejadorIndicadores;
    private Cuenta cuentaEBIDTA;
	private Cuenta cuentafds;
	private Indicador indicadorROE;
	private Indicador indicadorDIV;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;
	
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    

    @Before
    public void setup() {
    	repoIndicadores = new RepoIndicadores("repoIndiG5");
    	manejadorIndicadores = ManejadorIndicadores.getInstance();
        _parser = new ExpressionParser();
        cuentaEBIDTA = new Cuenta("EBIDTA",200,LocalDate.now(),LocalDate.now());
        cuentafds = new Cuenta("fds",200,LocalDate.now(),LocalDate.now());
		indicadorROE = new Indicador("ROE","20");  /* Cambio 08 */
		indicadorDIV= new Indicador("DIV","cu.EBIDTA");  /* Cambio 08 */
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		listaCuentas.add(cuentaEBIDTA);
		listaCuentas.add(cuentafds);
		listaIndicadores.add(indicadorROE);
		listaIndicadores.add(indicadorDIV);
    }

    @Test
    public void siGuardoIndicadorEnRepoConFormulaCorrecta() {
    	manejadorIndicadores.guardarIndicadorEnRepo(repoIndicadores,"indicador1","(cu.EBIDTA+1)*2");
    	Assert.assertEquals(1,(repoIndicadores.getListaIndicadores().size()));
    }
    
    @Test
    public void siGuardoIndicadorEnRepoConFormulaIncorrecta() {
    	thrown.expect(IllegalArgumentException.class);
    	thrown.expectMessage(containsString("Formula no Valida"));
    	manejadorIndicadores.guardarIndicadorEnRepo(repoIndicadores,"indicador1","(pepe+1)*2");
    }
    
}
