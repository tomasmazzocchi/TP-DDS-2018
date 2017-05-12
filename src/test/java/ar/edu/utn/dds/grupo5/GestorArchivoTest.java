package ar.edu.utn.dds.grupo5;

import org.junit.*;
import org.junit.rules.ExpectedException;
import java.io.*;
import org.apache.commons.io.IOUtils;

import ar.edu.utn.dds.grupo5.GestorArchivo;
import ar.edu.utn.dds.grupo5.RepoEmpresas;

public class GestorArchivoTest {
	
	private RepoEmpresas repositorio;
	private GestorArchivo gestor;
	private String rutaValida;
	private String rutaInvalida;
	private String rutaConJsonInvalido;
	private InputStream hola;
	
	@Before
	public void init() throws IOException{
		repositorio = new RepoEmpresas("Bancos");
		gestor = GestorArchivo.getInstance();
		/*hola = hola.getClass().getResourceAsStream("Archivo Ivalido.json");
		//rutaConJsonInvalido.getClass().getResourceAsStream("\\Archivo Invalido.json"); /*"\\GitHub\\2017-mn-group-05\\src\\test\\resources\\Archivo Invalido.json";*/
		//rutaConJsonInvalido = IOUtils.toString(hola, "UTF-8");// .toString(hola);
		/*StringWriter writer = new StringWriter();
		IOUtils.copy(hola, writer, "UTF-8");
		rutaConJsonInvalido = writer.toString();*/
		
		
		//rutaConJsonInvalido = IOUtils.toString(InputStream.class.getResourceAsStream("\\Archivo Ivalido.json"),"UTF-8"); 
		rutaConJsonInvalido = "\\GitHub\\2017-mn-group-05\\src\\test\\resources\\Archivo Invalido.json";
		rutaValida = "\\GitHub\\2017-mn-group-05\\src\\test\\resources\\Archivo Valido.json";
	    rutaInvalida = "C:\\Users\\Tomás\\Desktop\\Hola.json";
	}
	
	@After
	public void limpiarRepo(){
		repositorio.reset();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void siColocoUnaRutaValidaEntoncesSeCarganAlRepositorio(){
		gestor.cargarArchivo(repositorio, rutaValida);
		Assert.assertTrue(repositorio.getListaEmpresa().size() > 0);
	}
	@Test
	public void siColocoUnaRutaInvalidaEntoncesNoSeCarganEmpresasAlRepositorio(){
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Ruta inexistente");
		gestor.cargarArchivo(repositorio,rutaInvalida);
		//Assert.assertTrue(repositorio.getListaEmpresa().size()==0);
	}
	@Test
	public void siColocoUnaRutaValidaConUnJsonInvalidoEntoncesNoSeCarganEmpresasAlRepositorio(){
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Archivo invalido");
		gestor.cargarArchivo(repositorio, rutaConJsonInvalido);
		//Assert.assertTrue(repositorio.getListaEmpresa().size()==0);
	}
}
