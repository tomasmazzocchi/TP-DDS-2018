package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.dds.ExceptionHandler.CondicionExistenteException;
import ar.edu.utn.dds.ExceptionHandler.CondicionInexistenteException;


public class RepoCondiciones {
	private String nombre;
	private List<Condicion> listaCondiciones = new ArrayList<>();


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Condicion> getListaCondiciones() {
		return this.listaCondiciones;
	}

	public void reset() {
		listaCondiciones.clear();
	}

	public void agregarCondicion(Condicion unaCondicion) {
		if (!listaCondiciones.contains(unaCondicion)) {
			listaCondiciones.add(unaCondicion);
		} else {
			throw new CondicionExistenteException("Condicion ya existente");
		}
	}

	public RepoCondiciones(String nombre) {
		this.nombre = nombre;
	}

	public void quitarCondicion(Condicion unaCondicion) {
		if (listaCondiciones.contains(unaCondicion)) {
			listaCondiciones.remove(unaCondicion);
		} else {
			throw new CondicionInexistenteException("No existe la condicion");
		}
	}
}
