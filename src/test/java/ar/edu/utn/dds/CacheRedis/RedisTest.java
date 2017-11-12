package ar.edu.utn.dds.CacheRedis;

import org.junit.*;

import ar.edu.utn.dds.HerramientasExternas.Cache;

public class RedisTest {

	@Before
	public void init() {
		Cache.getInstancia().activarCache();
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
}
