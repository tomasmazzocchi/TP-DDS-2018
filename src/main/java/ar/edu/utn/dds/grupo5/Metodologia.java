package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Metodologia {
	private List<Condicion> condiciones;
	private Map<String, List<Empresa>> resultados = new LinkedHashMap<>();

	public Metodologia(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	// ----------------------GETTERS AND SETTERS-----------------------------------
	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	public Map<String, List<Empresa>> getResultados() {
		return resultados;
	}

	public void setResultados(LinkedHashMap<String, List<Empresa>> resultados) {
		this.resultados = resultados;
	}

	public void aplicarCondiciones(List<Empresa> empresas) {
		condiciones.stream().forEach(condicion -> {
			List<Empresa> resultado = new ArrayList<>();
			resultado = condicion.aplicarCondicion(empresas);
			resultados.put(condicion.getNombre(), resultado);
		});
	}
}
