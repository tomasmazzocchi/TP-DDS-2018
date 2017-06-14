package ar.edu.utn.dds.grupo5;

import org.junit.*;
import org.junit.rules.ExpectedException;
import java.io.*;
import java.net.URLDecoder;

import ar.edu.utn.dds.ExceptionHandler.ArchivoException;
import ar.edu.utn.dds.grupo5.GestorArchivo;
import ar.edu.utn.dds.grupo5.RepoEmpresas;

public class GestorArchivoTest {

	private RepoEmpresas repositorio;
	private GestorArchivo gestor;
	private String jsonValido;
	private String jsonInvalido;
	private ClassLoader classLoader;
	private File archivojsonInvalido;
	private File archivojsonValido;
	private java.net.URL resource;

	@Before
	public void init() throws IOException {
		repositorio = new RepoEmpresas("Bancos");
		gestor = GestorArchivo.getInstance();
		jsonValido = "ArchivoValido.json";
		jsonInvalido = "ArchivoInvalido.json";
		classLoader = GestorArchivo.class.getClassLoader();
		resource = classLoader.getResource(jsonValido);
		archivojsonValido = new File(URLDecoder.decode(resource.getFile(), "UTF-8"));
		resource = classLoader.getResource(jsonInvalido);
		archivojsonInvalido = new File(URLDecoder.decode(resource.getFile(), "UTF-8"));
	}

	@After
	public void limpiarRepo() {
		repositorio.reset();
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void siColocoUnArchivoConJsonValidoEntoncesSeCarganAlRepositorio() {
		gestor.cargarArchivo(repositorio, archivojsonValido);
		Assert.assertTrue(repositorio.getListaEmpresa().size() > 0);
	}

	@Test
	public void siColocoUnArchivoConJsonInvalidoEntoncesNoSeCarganEmpresasAlRepositorio() {
		thrown.expect(ArchivoException.class);
		thrown.expectMessage("Archivo invalido");
		gestor.cargarArchivo(repositorio, archivojsonInvalido);
	}

}
