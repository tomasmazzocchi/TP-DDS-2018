package ar.edu.utn.dds.grupo5;

import java.util.HashMap;
import java.util.List;

public class Metodologia {
	private List<Condicion> condiciones;
	private HashMap<String,List<Empresa>> resultados = new HashMap<>();
	
	public Metodologia(List <Condicion> condiciones){
		this.condiciones = condiciones;
	}
//----------------------GETTERS AND SETTERS-----------------------------------		
	public List<Condicion> getCondiciones() {
		return condiciones;
	}
	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}
	public HashMap<String, List<Empresa>> getResultados() {
		return resultados;
	}
	public void setResultados(HashMap<String, List<Empresa>> resultados) {
		this.resultados = resultados;
	}



	public void aplicarCondiciones(List<Empresa> empresas){
		condiciones.stream().forEach(condicion-> resultados.put(condicion.getNombre() ,condicion.aplicarCondicion(empresas)));
	}
}
