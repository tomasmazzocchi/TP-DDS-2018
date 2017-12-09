package DTO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Usuario;

@Entity
@Table(name = "metodologia", schema = "dds2017")
public class MetodologiaDTO {
	@Id
	@GeneratedValue
	@Column(name = "id_metodologia")
	private int idMetodologia;
	@Column(name = "nombre")
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Condicion> condiciones;
	@Transient
	private Map<String, List<Empresa>> resultados = new LinkedHashMap<>();
	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuarioAsociado;

	public MetodologiaDTO(String nombre, List<Condicion> condiciones, Map<String, List<Empresa>> resultados,
			Usuario usuarioAsociado) {
		this.nombre = nombre;
		this.condiciones = condiciones;
		this.resultados = resultados;
		this.usuarioAsociado = usuarioAsociado;
	}

	public MetodologiaDTO() {

	}

	public String getNombre() {
		return nombre;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public Map<String, List<Empresa>> getResultados() {
		return resultados;
	}

	public Usuario getUsuarioAsociado() {
		return usuarioAsociado;
	}

}
