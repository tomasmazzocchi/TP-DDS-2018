package ar.edu.utn.dds.grupo5;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.utn.dds.ExceptionHandler.SintaxisException;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.ExpressionParser;
import ar.edu.utn.dds.grupo5.Indicador;

/*
 * Test set that tests different expressions against their expected results.
 */
public class ManejadorIndicadoresTest {
	private RepoIndicadores repoIndicadores;
	private ManejadorIndicadores manejadorIndicadores;
    private ExpressionParser _parser;
    private Cuenta cuentaEBIDTA;
	private Cuenta cuentafds;
	private Indicador indicadorROE;
	private Indicador indicadorDIV;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;
    @Rule
    public ExpectedException _expected = ExpectedException.none();
    

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
    public void guardarIndicadorEnRepo() {
    	manejadorIndicadores.guardarIndicadorEnRepo(repoIndicadores,"indicador1","(cu.EBIDTA+1)*2");
    	System.out.println(repoIndicadores.getListaIndicadores().get(0).getNombre());
    	Assert.assertEquals(1,(repoIndicadores.getListaIndicadores().size()));
    }
    
   

}
