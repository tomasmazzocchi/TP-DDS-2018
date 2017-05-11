package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

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
	//metodo buscar cuenta
	public static Cuenta BuscaCuenta(String nombre , List<Cuenta> listaCuentas) {
	Iterator<Cuenta> i = listaCuentas.iterator();
	while (i.hasNext())
	  {
		Cuenta cuenta = (Cuenta) i.next();
		if (nombre.equals(cuenta.getNombre()))
		{
			return cuenta;
		}

	}
	return null;
}
}

