package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Empresa {
	private String nombre;
	private List<Cuenta> listaCuentas = new ArrayList<>();
	private List<Indicador> listaIndicadores = new ArrayList<>();
	
	// Getters and Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Cuenta> getListaCuentas(){
		return this.listaCuentas;
	}
	public List<Indicador> getListaIndicadores(){
		return this.listaIndicadores;
	}
	// Constructors
	
	public Empresa(String nombre,List<Cuenta> listaCuentas,List<Indicador> listaIndicadores) 
	{
		this.nombre = nombre;
		this.listaCuentas = listaCuentas;
		this.listaIndicadores = listaIndicadores;
	}
	
	//List Cuentas Validas entre dos Fechas
	
	public static List<Cuenta> ListaDeCuentasValidas(Empresa empresa, LocalDate fechaDesde, LocalDate fechaHasta) {
		// TODO Auto-generated method stub

        List<Cuenta> listaCuentas = empresa.getListaCuentas();

        List<Cuenta> listaCuentasValidas = listaCuentas.stream()                // convert list to stream
                .filter(cuenta -> fechaDesde.equals(cuenta.getFechaDesde())&&fechaHasta.equals(cuenta.getFechaHasta()))   
                .collect(Collectors.toList());              // collect the output and convert streams to a List

        listaCuentasValidas.forEach(System.out::println);                //output : spring, node
		
		return listaCuentasValidas;
	}

	
}
