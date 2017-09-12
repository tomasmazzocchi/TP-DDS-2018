package dbtest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.RepoEmpresas;

public class PersistenciaTest {
	private Empresa empresa1, empresa2, empresa3;
	private Cuenta cuentaEBIDTA;
	private Cuenta cuentaFDS;
	private Indicador indicadorMayorQue;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;
	//private RepoEmpresas repoEmpresas;

	@Before
	public void init() {
		cuentaEBIDTA = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentaFDS = new Cuenta("FDS", 500, LocalDate.now(), LocalDate.now());
		indicadorMayorQue = new Indicador("MayorQue", "200"); 
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		listaCuentas.add(cuentaEBIDTA);
		listaCuentas.add(cuentaFDS);
		listaIndicadores.add(indicadorMayorQue);
		empresa1 = new Empresa("Facebook", listaCuentas, listaIndicadores,LocalDate.now());
		empresa2 = new Empresa("Google", listaCuentas, listaIndicadores,LocalDate.now());
		empresa3 = new Empresa("Apple", listaCuentas, listaIndicadores,LocalDate.now());
		//repoEmpresas = new RepoEmpresas("repoG5");
	}
	
	@Test
	public void siPersistoUnaEmpresaYLaBuscoEnLaBdLaEncuentra(){
		
	}
}
