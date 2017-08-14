package ar.edu.utn.dds.grupo5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metodologia {
	private List<Condicion> condiciones;
	private Map<String,List<Empresa>> resultados = new HashMap<>();
	
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
	public Map<String, List<Empresa>> getResultados() {
		return resultados;
	}
	public void setResultados(HashMap<String, List<Empresa>> resultados) {
		this.resultados = resultados;
	}



	public void aplicarCondiciones(List<Empresa> empresas){
		condiciones.stream().forEach(condicion->{
		System.out.println(condicion.getNombre());
		System.out.println(condicion.aplicarCondicion(empresas).get(0));	
		System.out.println(condicion.aplicarCondicion(empresas).get(0).getNombre());	
		resultados.put(condicion.getNombre(),condicion.aplicarCondicion(empresas));
		});
	}
}
