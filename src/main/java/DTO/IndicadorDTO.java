package DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "indicador", schema = "dds2017")
public class IndicadorDTO {
	@Column(name = "id_indicador")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idIndicador;
	@Column(name = "formula")
	private String formula;
	@Column(name = "nombre")
	private String nombre;
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioDTO usuarioAsociado;
	@OneToOne
	@JoinColumn(name = "id_empresa")
	private EmpresaDTO empresaAsociada;
	@Transient
	private int valor;

	public IndicadorDTO(String nombre, String formula, EmpresaDTO empresa, UsuarioDTO us) {
		this.nombre = nombre;
		this.formula = formula;
		this.empresaAsociada = empresa;
		this.usuarioAsociado = us;
	}

	public IndicadorDTO() {

	}

	public EmpresaDTO getEmpresaAsociada() {
		return empresaAsociada;
	}

	public int getValor() {
		return valor;
	}

	public String getNombre() {
		return nombre;
	}

	public UsuarioDTO getUsuario() {
		return usuarioAsociado;
	}

	public String getFormula() {
		return formula;
	}

}
