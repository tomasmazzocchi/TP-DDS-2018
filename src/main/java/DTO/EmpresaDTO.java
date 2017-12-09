package DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Indicador;

@Entity
@Table(name = "empresa", schema = "dds2017")
public class EmpresaDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_empresa")
	private int id_empresa;
	@Column(name = "anio_fundacion")
	private LocalDate anio_fundacion;
	@Column(name = "nombre")
	private String nombre;
	@OneToMany
	@JoinColumn(name = "id_empresa")
	private List<Cuenta> listaCuentas = new ArrayList<>();
	@OneToMany
	@JoinColumn(name = "id_empresa")
	private List<Indicador> listaIndicadores = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioDTO usuarioAsociado;

	public EmpresaDTO(LocalDate anio_fundacion, String nombre, List<Cuenta> listaCuentas,
			List<Indicador> listaIndicadores, UsuarioDTO usuarioAsociado) {
		this.anio_fundacion = anio_fundacion;
		this.nombre = nombre;
		this.listaCuentas = listaCuentas;
		this.listaIndicadores = listaIndicadores;
		this.usuarioAsociado = usuarioAsociado;
	}

	public EmpresaDTO() {

	}

	public LocalDate getAnio_fundacion() {
		return anio_fundacion;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public List<Indicador> getListaIndicadores() {
		return listaIndicadores;
	}

	public UsuarioDTO getUsuarioAsociado() {
		return usuarioAsociado;
	}
}
