package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionCuenta;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionIndicador;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionNumero;

public class CondicionesTest {
	private List<Cuenta> listaCuentasFacebook = new ArrayList<>();
	private List<Cuenta> listaCuentasGoogle = new ArrayList<>();
	private List<Cuenta> listaCuentasTwitter = new ArrayList<>();
	private List<Indicador> listaIndicadoresFacebook;
	private List<Indicador> listaIndicadoresGoogle;
	private List<Indicador> listaIndicadoresTwitter;
	private Empresa facebook;
	private Empresa google;
	private Empresa twitter;
	private Metodologia metodologiaBuffet;
	private CondicionNumero longevidad;
	private CondicionIndicador maximizarROE;
	private CondicionIndicador minimizarROA;
	private CondicionCuenta margenCreciente;
	private Indicador indicadorROE;
	private Indicador indicadorROA;
	private Indicador indicadorDeudaFacebook;
	private Indicador indicadorDeudaGoogle;
	private Indicador indicadorDeudaTwitter;
	private Cuenta cuentaEBIDTAFacebook;
	private Cuenta cuentaMargenFacebook;
	private Cuenta cuentaEBIDTAGoogle;
	private Cuenta cuentaMARGENGooge;
	private Cuenta cuentaEBIDTATwitter;
	private Cuenta cuentaMARGENTwitter;

	@Before
	public void setup() {
		cuentaEBIDTAFacebook = new Cuenta("EBIDTA", 100, LocalDate.now(), LocalDate.now());
		cuentaMargenFacebook = new Cuenta("Margen", 200, LocalDate.now(), LocalDate.now());
		cuentaEBIDTAGoogle = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentaMARGENGooge = new Cuenta("Margen", 300, LocalDate.now(), LocalDate.now());
		cuentaEBIDTATwitter = new Cuenta("EBIDTA", 300, LocalDate.now(), LocalDate.now());
		cuentaMARGENTwitter = new Cuenta("Margen", 400, LocalDate.now(), LocalDate.now());

		listaCuentasFacebook.add(cuentaEBIDTAFacebook);
		listaCuentasFacebook.add(cuentaMargenFacebook);
		listaCuentasGoogle.add(cuentaEBIDTAGoogle);
		listaCuentasGoogle.add(cuentaMARGENGooge);
		listaCuentasTwitter.add(cuentaEBIDTATwitter);
		listaCuentasTwitter.add(cuentaMARGENTwitter);

		listaIndicadoresFacebook = new ArrayList<>();
		listaIndicadoresGoogle = new ArrayList<>();
		listaIndicadoresTwitter = new ArrayList<>();

		indicadorROE = new Indicador("ROE", "cu.EBIDTA");
		indicadorROA = new Indicador("ROA", "cu.Margen");
		indicadorDeudaFacebook = new Indicador("DEUDA", "300");
		indicadorDeudaGoogle = new Indicador("DEUDA", "400");
		indicadorDeudaTwitter = new Indicador("DEUDA", "1000");

		listaIndicadoresFacebook.add(indicadorROE);
		listaIndicadoresFacebook.add(indicadorROA);
		listaIndicadoresFacebook.add(indicadorDeudaFacebook);
		listaIndicadoresGoogle.add(indicadorROE);
		listaIndicadoresGoogle.add(indicadorROA);
		listaIndicadoresGoogle.add(indicadorDeudaGoogle);
		listaIndicadoresTwitter.add(indicadorROE);
		listaIndicadoresTwitter.add(indicadorROA);
		listaIndicadoresTwitter.add(indicadorDeudaTwitter);

		facebook = new Empresa("Facebook", listaCuentasFacebook, listaIndicadoresFacebook, LocalDate.now());
		google = new Empresa("Google", listaCuentasGoogle, listaIndicadoresGoogle, LocalDate.now().minusYears(50));
		twitter = new Empresa("Twitter", listaCuentasTwitter, listaIndicadoresTwitter, LocalDate.now().minusYears(20));

		// Condiciones
		longevidad = new CondicionNumero("longevidad",10,10,'<');
		maximizarROE = new CondicionIndicador("Maximizar ROE", indicadorROE, 8, '>');
		minimizarROA = new CondicionIndicador("Minimizar ROA", indicadorROA, 2, '<');
		margenCreciente = new CondicionCuenta("Margen Creciente", cuentaMARGENGooge, 6, '>');

		RepoEmpresas.getListaEmpresa().add(facebook);
		RepoEmpresas.getListaEmpresa().add(google);
		RepoEmpresas.getListaEmpresa().add(twitter);

		List<Condicion> condiciones = new ArrayList<>();
		condiciones.add(longevidad);
		condiciones.add(maximizarROE);
		condiciones.add(minimizarROA);
		condiciones.add(margenCreciente);
		metodologiaBuffet = new Metodologia("Metodologia Buffet", condiciones);
	}

	@After
	public void after() {
		metodologiaBuffet.getHashMapResultados().clear();
		metodologiaBuffet.getCondiciones().clear();
		RepoEmpresas.limpiarListaEmpresas();
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void ingresaListadoDeEmpresasYDevuelvoLaDeMayorLongevidad() {
		List<Empresa> resultado = longevidad.aplicarCondicion(RepoEmpresas.getListaEmpresa());
		Assert.assertEquals(resultado.get(resultado.size()-1), google);
	}

	@Test
	public void ingresaListadoDeEmpresasYDevuelvoLaDeMayorROE() {
		List<Empresa> resultado = maximizarROE.aplicarCondicion(RepoEmpresas.getListaEmpresa());
		Assert.assertEquals(resultado.get(resultado.size()-1), twitter);
	}

	@Test
	public void ingresaListadoDeEmpresasYDevuelvoLaDeMenorROA() {
		List<Empresa> resultado = minimizarROA.aplicarCondicion(RepoEmpresas.getListaEmpresa());
		Assert.assertEquals(resultado.get(resultado.size()-1), facebook);
	}

	@Test
	public void ingresaListadoDeEmpresasYDevuelvoLaDeMargenCreciente() {
		List<Empresa> resultado = margenCreciente.aplicarCondicion(RepoEmpresas.getListaEmpresa());
		Assert.assertEquals(resultado.get(resultado.size()-1), facebook);
	}

	@Test
	public void aplicarMetodologiaBuffet() {
		metodologiaBuffet.aplicarCondiciones(RepoEmpresas.getListaEmpresa());

		Assert.assertEquals(metodologiaBuffet.getHashMapResultados().get(longevidad.getNombre()).get(
					metodologiaBuffet.getHashMapResultados().get(longevidad.getNombre()).size()-1), google);
		Assert.assertEquals(metodologiaBuffet.getHashMapResultados().get(maximizarROE.getNombre()).get(
				metodologiaBuffet.getHashMapResultados().get(maximizarROE.getNombre()).size()-1), twitter);
		Assert.assertEquals(metodologiaBuffet.getHashMapResultados().get(minimizarROA.getNombre()).get(
				metodologiaBuffet.getHashMapResultados().get(minimizarROA.getNombre()).size()-1), facebook);
		Assert.assertEquals(metodologiaBuffet.getHashMapResultados().get(margenCreciente.getNombre()).get(
				metodologiaBuffet.getHashMapResultados().get(margenCreciente.getNombre()).size()-1), facebook);
	}

	@Test
	public void aplicarMetodologiaBuffetYCorroborarEmpresaGanadoraSegunPuntaje() {
		metodologiaBuffet.aplicarCondiciones(RepoEmpresas.getListaEmpresa());
		Assert.assertEquals(metodologiaBuffet.getListaResultado().get(0).getEmpresa(),google);
		Assert.assertEquals(metodologiaBuffet.getListaResultado().get(0).getPuntuacion(),52);
		Assert.assertEquals(metodologiaBuffet.getListaResultado().get(1).getEmpresa(),twitter);
		Assert.assertEquals(metodologiaBuffet.getListaResultado().get(1).getPuntuacion(),42);
		Assert.assertEquals(metodologiaBuffet.getListaResultado().get(2).getEmpresa(),facebook);
		Assert.assertEquals(metodologiaBuffet.getListaResultado().get(2).getPuntuacion(),32);
	}
}
