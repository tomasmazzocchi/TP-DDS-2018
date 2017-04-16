import java.util.ArrayList;
import java.util.List;

import pruebadds2017.Cuenta;
import pruebadds2017.Indicador;

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
				;
	}
	// Constructors
	
	public Empresa(String nombre,List<Cuenta> listaCuentas,List<Indicador> listaIndicadores) 
	{
		this.nombre = nombre;
		this.listaCuentas = listaCuentas;
		this.listaIndicadores = listaIndicadores;
	}

	
}
