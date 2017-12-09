package DTO;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "cuenta", schema = "dds2017")
public class CuentaDTO {
	@Column(name = "id_cuenta")
	@Id
	@GeneratedValue
	private int idCuenta;
	@Column(name = "fecha_desde")
	private LocalDate fechaDesde;
	@Column(name = "fecha_hasta")
	private LocalDate fechaHasta;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "valor")
	private double valor;
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioDTO usuarioAsociado;

	public CuentaDTO(LocalDate fecDesde, LocalDate fecHasta, String nombre, double valor, UsuarioDTO us) {
		this.fechaDesde = fecDesde;
		this.fechaHasta = fecHasta;
		this.nombre = nombre;
		this.valor = valor;
		this.usuarioAsociado = us;
	}

	public CuentaDTO() {

	}

	public LocalDate getFechaDesde() {
		return fechaDesde;
	}

	public LocalDate getFechaHasta() {
		return fechaHasta;
	}

	public String getNombre() {
		return nombre;
	}

	public UsuarioDTO getUsuarioAsociado() {
		return usuarioAsociado;
	}

	public double GetValor() {
		return valor;
	}

}
