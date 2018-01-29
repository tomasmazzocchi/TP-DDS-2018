package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "metodologia", schema = "dds2017")
public class Metodologia {
	@Id
	@GeneratedValue
	@Column(name = "id_metodologia")
	private int idMetodologia;
	@Column(name = "nombre")
	private String nombre;
	@OneToMany
	private List<Condicion> condiciones;
	@ManyToOne
	@JoinColumn(name = "id_usuario")	
	private Usuario usuarioAsociado;
	@Transient
	private Map<String, List<Empresa>> resultados = new LinkedHashMap<>();


	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;
	}

	// ----------------------GETTERS AND SETTERS-----------------------------------
	public Metodologia(){
		
	}
	
	public List<Condicion> getCondiciones() {
		return condiciones;
	}
	
	public Usuario getUs() {
		return usuarioAsociado;
	}

	public void setUs(Usuario us) {
		this.usuarioAsociado = us;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		resultados.clear();
		condiciones.stream().forEach(condicion -> {
			List<Empresa> resultado = new ArrayList<>();
			resultado = condicion.aplicarCondicion(empresas);
			resultados.put(condicion.getNombre(), resultado);
		});
	}
}
