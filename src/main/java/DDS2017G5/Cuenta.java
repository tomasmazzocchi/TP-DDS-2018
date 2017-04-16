package DDS2017G5;

import java.time.LocalDate;

public class Cuenta {
	private String nombre;
	private double valor;
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;
	
	// Getters and Setters
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
	// Constructors
	public Cuenta(String nombre,double valor,LocalDate fechaDesde,LocalDate fechaHasta)
	{
		this.setNombre(nombre);
		this.setValor(valor);
		this.setFechaDesde(fechaDesde);
		this.setFechaHasta(fechaHasta);
	}

}
