package ar.edu.utn.dds.HerramientasExternas;

import java.util.List;

import ar.edu.utn.dds.grupo5.Indicador;
import redis.clients.jedis.Jedis;


public class Cache {
	
	private static Jedis jedis = new Jedis();
	private static Cache instancia;
	//Como Jedis utiliza segundos. Sirve para setear el tiempo en que la key es valida y existe, despues de este tiempo si libera la key.
	private static int durationXKey = 60;
	
	public static Cache getInstancia() {
		if(instancia == null) {
			instancia = new Cache();
		}
		return instancia;
	}
	
	//Este metodo activa la cache si se la habia desactivado. Si estaba activada no pasa nada
	public void activarCache() {
		if(jedis==null) {
			jedis = new Jedis();
		}
	}
	
	//Si quiero desactivar la cache dinamicamente: se limpia la db de redis, cierro la conexion y seteo atributo nulo
	public void desactivarCache() {
		jedis.flushDB();
		jedis.close();
		jedis = null;
	}
	
	public boolean criterioEstaEnCache(String criterio) {
		return jedis != null && jedis.exists(criterio);
	}
	
	//Ingreso indicadores a la cache
	public void agregarIndicador(String key, String value) {
		if(jedis!=null && value !=null) {
			jedis.setex(key, durationXKey, value);
		}
	}
	
	/*public void agregarListaIndicadoresAEmpresa(String empresa,List<Indicador> lista) {
		if(jedis!=null && lista != null) {
			lista.stream().forEach(x -> jedis.sadd(empresa, Integer.toString(x.getValor()))); 
		}
	}*/
	
	public String obtenerIndicador(String key){
		return jedis.get(key);
	}
	/*
	public static void main(String[] args) {
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		// set the data in redis string
		jedis.set("tutorial-name", "Redis tutorial");
		// Get the stored data and print it
		System.out.println("Stored string in redis:: " + jedis.get("tutorialname"));
		//jedis.close();
		System.out.println(jedis.get("tutorial-name"));
	}
	*/
}