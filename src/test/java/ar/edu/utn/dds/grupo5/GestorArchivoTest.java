package ar.edu.utn.dds.grupo5;

import org.junit.*;
import org.junit.rules.ExpectedException;
import java.io.*;
import java.net.URLDecoder;

import ar.edu.utn.dds.grupo5.CompiladorParser;
import ar.edu.utn.dds.grupo5.RepoEmpresas;

public class GestorArchivoTest {
	
	private RepoEmpresas repositorio;
	private CompiladorParser gestor;
	private String jsonValido;
	private String jsonInvalido;
	private ClassLoader classLoader;
	private File archivojsonInvalido;
	private File archivojsonValido;
	private java.net.URL resource;
	
	@Before
	public void init() throws IOException{
		repositorio = new RepoEmpresas("Bancos");
		gestor = CompiladorParser.getInstance();
		jsonValido = "ArchivoValido.json";
		jsonInvalido = "ArchivoInvalido.json";
		classLoader = CompiladorParser.class.getClassLoader();
		resource = classLoader.getResource(jsonValido);
		archivojsonValido = new File(URLDecoder.decode(resource.getFile(), "UTF-8"));
		resource = classLoader.getResource(jsonInvalido);
		archivojsonInvalido = new File(URLDecoder.decode(resource.getFile(), "UTF-8"));
	}
	
	@After
	public void limpiarRepo(){
		repositorio.reset();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void siColocoUnArchivoConJsonValidoEntoncesSeCarganAlRepositorio(){
		gestor.cargarArchivo(repositorio, archivojsonValido);
		Assert.assertTrue(repositorio.getListaEmpresa().size() > 0);
	}

	@Test
	public void siColocoUnArchivoConJsonInvalidoEntoncesNoSeCarganEmpresasAlRepositorio(){
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Archivo invalido");
		gestor.cargarArchivo(repositorio, archivojsonInvalido);
	}
	
}
