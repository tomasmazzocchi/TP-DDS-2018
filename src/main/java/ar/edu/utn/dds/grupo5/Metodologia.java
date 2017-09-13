package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table( name="Metodologia")
public class Metodologia {
	@Id @GeneratedValue
	@Column(name="id_metodologia")
	private int idMetodologia;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Condicion> condiciones;
	
    @Transient
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
