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
	private List<CondicionDTO> condiciones;
	@Transient
	private Map<String, List<EmpresaDTO>> resultados = new LinkedHashMap<>();
	@ManyToOne(cascade = CascadeType.ALL)
	private UsuarioDTO usuarioAsociado;

	public MetodologiaDTO(String nombre, List<CondicionDTO> condiciones, UsuarioDTO usuarioAsociado) {
		this.nombre = nombre;
		this.condiciones = condiciones;
		this.usuarioAsociado = usuarioAsociado;
	}

	public MetodologiaDTO() {

	}

	public String getNombre() {
		return nombre;
	}

	public List<CondicionDTO> getCondiciones() {
		return condiciones;
	}

	public Map<String, List<EmpresaDTO>> getResultados() {
		return resultados;
	}

	public UsuarioDTO getUsuarioAsociado() {
		return usuarioAsociado;
	}

}
