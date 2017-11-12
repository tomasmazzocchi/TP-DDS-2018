package ar.edu.utn.dds.CacheRedis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import ar.edu.utn.dds.HerramientasExternas.Cache;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;

public class RedisTest {

	private Indicador indicadorROE;
	private Cuenta cuentaEBIDTAFacebook;
	private List<Cuenta> listaCuentasFacebook = new ArrayList<>();
	private List<Indicador> listaIndicadoresFacebook=  new ArrayList<>();
	private Empresa facebook;

	@Before
	public void init() {
		Cache.getInstancia().activarCache();
		
		cuentaEBIDTAFacebook = new Cuenta("EBIDTA", 100, LocalDate.now(), LocalDate.now());
		indicadorROE = new Indicador("ROE", "cu.EBIDTA");
		
		listaCuentasFacebook.add(cuentaEBIDTAFacebook);
		listaIndicadoresFacebook.add(indicadorROE);
		
		facebook = new Empresa("Facebook",listaCuentasFacebook,listaIndicadoresFacebook, LocalDate.now());


	}
	
	@Test
	public void redisTest() {
		Cache.getInstancia().actualizarCache("Hola", "Mundo");
		Assert.assertTrue(Cache.getInstancia().criterioEstaEnCache("Hola"));
	}
	
	@Test
	public void desactivoCche() {
		Cache.getInstancia().actualizarCache("River", "Plate");
		Cache.getInstancia().desactivarCache();
		Assert.assertFalse(Cache.getInstancia().criterioEstaEnCache("River"));
	}
	
	@Test
	public void funcionamientoCache() {
		Cache.getInstancia().actualizarCache(facebook.getListaIndicadores().get(0).getNombre(), Integer.toString(indicadorROE.calcularIndicador(facebook)));
		Assert.assertEquals(Integer.toString(indicadorROE.calcularIndicador(facebook)), Cache.getInstancia().obtenerIndicador(facebook.getListaIndicadores().get(0).getNombre()));
	}
}
