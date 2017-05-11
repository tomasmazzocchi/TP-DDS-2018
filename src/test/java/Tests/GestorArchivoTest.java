package Tests;

import org.junit.*;
import org.junit.rules.ExpectedException;

import dds2017g5.GestorArchivo;
import dds2017g5.RepoEmpresas;

public class GestorArchivoTest {
	
	private RepoEmpresas repositorio;
	private GestorArchivo gestor;
	private String rutaValida;
	private String rutaInvalida;
	private String rutaConJsonInvalido;
	
	@Before
	public void init(){
		repositorio = new RepoEmpresas("Bancos");
		gestor = GestorArchivo.getInstance();
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
		gestor.cargarArchivo(repositorio, rutaInvalida);
		Assert.assertTrue(repositorio.getListaEmpresa().size()==0);
	}
	@Test
	public void siColocoUnaRutaValidaConUnJsonInvalidoEntoncesNoSeCarganEmpresasAlRepositorio(){
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Archivo invalido");
		gestor.cargarArchivo(repositorio, rutaConJsonInvalido);
		Assert.assertTrue(repositorio.getListaEmpresa().size()==0);
	}
}
