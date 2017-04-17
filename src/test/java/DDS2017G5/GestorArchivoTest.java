package DDS2017G5;

import org.junit.*;
import org.junit.rules.ExpectedException;

public class GestorArchivoTest {
	
	RepoEmpresas repositorio;
	GestorArchivo gestor;
	String rutaValida;
	String rutaInvalida;
	String rutaConJsonInvalido;
	
	@Before
	public void init(){
		repositorio = new RepoEmpresas("Bancos");
		gestor = GestorArchivo.getInstance();
		rutaConJsonInvalido = "C:\\Users\\Julian\\Desktop\\Archivo Invalido.json";
		rutaValida = "C:\\Users\\Julian\\Desktop\\Archivo Valido.json";
	    rutaInvalida = "C:\\Users\\Julian\\Desktop\\Hola.json";
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
		thrown.expectMessage("Archivo inválido");
		gestor.cargarArchivo(repositorio, rutaConJsonInvalido);
		Assert.assertTrue(repositorio.getListaEmpresa().size()==0);
	}
}
