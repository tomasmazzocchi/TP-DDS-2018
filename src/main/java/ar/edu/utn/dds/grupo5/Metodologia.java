package ar.edu.utn.dds.grupo5;

import java.util.HashMap;
import java.util.List;

public class Metodologia {
	private List<Condicion> condiciones;
	private HashMap<String,List<Empresa>> resultados = new HashMap<>();
	
	public Metodologia(List <Condicion> condiciones){
		this.condiciones = condiciones;
	}
	public void aplicarCondiciones(List<Empresa> empresas){
		condiciones.stream().forEach(condicion-> resultados.put(condicion.getNombre() ,condicion.aplicarCondicion(empresas)));
	}
}
