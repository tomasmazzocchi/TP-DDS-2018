package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.dds.ExceptionHandler.EmpresaExistenteException;
import ar.edu.utn.dds.ExceptionHandler.EmpresaInexistenteException;

public class RepoEmpresas {
	private String nombre;
	private List<Empresa> listaEmpresas = new ArrayList<>();

	// Getters and Setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Empresa> getListaEmpresa() {
		return this.listaEmpresas;
	}

	// Functions and Procedures

	public void reset() {
		listaEmpresas.clear();
	}

	public void agregarEmpresa(Empresa unaEmpresa) {
		if (!listaEmpresas.contains(unaEmpresa)) {
			listaEmpresas.add(unaEmpresa);
		} else {
			throw new EmpresaExistenteException("Empresa ya existente");
		}
	}

	public RepoEmpresas(String nombre) {
		this.nombre = nombre;
	}

	public void quitarEmpresa(Empresa unaEmpresa) {
		if (listaEmpresas.contains(unaEmpresa)) {
			listaEmpresas.remove(unaEmpresa);
		} else {
			throw new EmpresaInexistenteException("No existe la empresa");
		}
	}
}
