package DDS2017G5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class RepoEmpresasTest {
    
	Empresa empresa1,empresa2,empresa3;
	Cuenta cuenta;
	Indicador indicador;
	List<Cuenta> listaCuentas;
	List<Indicador> listaIndicadores;
	RepoEmpresas repoEmpresas;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void init(){
		cuenta = new Cuenta("EBIDTA",200,LocalDate.now(),LocalDate.now());
		indicador = new Indicador("MayorQue",200);
		listaCuentas = new ArrayList<>();
		listaIndicadores = new ArrayList<>();
		listaCuentas.add(cuenta);
		listaIndicadores.add(indicador);
		empresa1=new Empresa("Facebook",listaCuentas,listaIndicadores);
		empresa2=new Empresa("Google",listaCuentas,listaIndicadores);
		empresa3=new Empresa("Apple",listaCuentas,listaIndicadores);
		repoEmpresas = new RepoEmpresas("repoG5");
	}
	@Test
	public void siagregoTresEmpresasAlRepoVerificoQueSeAgregaronTres() {
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.agregarEmpresa(empresa2);
		repoEmpresas.agregarEmpresa(empresa3);
		Assert.assertTrue(repoEmpresas.getListaEmpresa().size() == 3);
	}
	
	@Test
	public void siagregoUnaEmpresExistenteNoSeAgregaEnRepo() {
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.agregarEmpresa(empresa2);
		repoEmpresas.agregarEmpresa(empresa3);
		repoEmpresas.agregarEmpresa(empresa1);
		Assert.assertTrue(repoEmpresas.getListaEmpresa().size() == 3);
	}
	@Test
	public void eliminoUnaEmpresaExistente() {
		int cantEmpresasAntes;
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.agregarEmpresa(empresa2);
		repoEmpresas.agregarEmpresa(empresa3);
		cantEmpresasAntes = repoEmpresas.getListaEmpresa().size();
		repoEmpresas.quitarEmpresa(empresa1);
		Assert.assertTrue(repoEmpresas.getListaEmpresa().size() == (cantEmpresasAntes-1));
	}
	@Test
	public void eliminoUnaEmpresaInexistente() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("No existe la empresa");
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.quitarEmpresa(empresa2);
	}
	

}
