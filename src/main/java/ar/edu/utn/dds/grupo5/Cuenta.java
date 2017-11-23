package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuenta", schema = "dds2017")
public class Cuenta {
	@Column(name = "id_cuenta")
	@Id	@GeneratedValue
	private int idCuenta;
	@Column(name = "fecha_desde" )
	private LocalDate fechaDesde;
	@Column(name = "fecha_hasta" )
	private LocalDate fechaHasta;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "valor")
	private double valor;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public LocalDate getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Cuenta(String nombre, double valor, LocalDate fechaDesde, LocalDate fechaHasta) {
		this.setNombre(nombre);
		this.setValor(valor);
		this.setFechaDesde(fechaDesde);
		this.setFechaHasta(fechaHasta);
	}

	protected Cuenta() {

	}
}
