package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.dds.ExceptionHandler.EmpresaExistenteException;
import ar.edu.utn.dds.ExceptionHandler.EmpresaInexistenteException;

public class RepoEmpresas {
	private String nombre;
	private static List<Empresa> listaEmpresas = new ArrayList<>();

	public RepoEmpresas(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static List<Empresa> getListaEmpresa() {
		return listaEmpresas;
	}

	public List<Empresa> obtenerEmpresas() {
		return listaEmpresas;
	}

	public void reset() {
		listaEmpresas.clear();
	}

	public static void agregarEmpresas(List<Empresa> empresas) {
		listaEmpresas.addAll(empresas);
	}

	public void agregarEmpresa(Empresa unaEmpresa) {
		if (!listaEmpresas.contains(unaEmpresa)) {
			listaEmpresas.add(unaEmpresa);
		} else {
			throw new EmpresaExistenteException("Empresa ya existente");
		}
	}

	public void quitarEmpresa(Empresa unaEmpresa) {
		if (listaEmpresas.contains(unaEmpresa)) {
			listaEmpresas.remove(unaEmpresa);
		} else {
			throw new EmpresaInexistenteException("No existe la empresa");
		}
	}

	public static Empresa getEmpresaFromId(int id) {
		return listaEmpresas.stream().filter(x -> x.getId() == id).collect(Collectors.toList()).get(0);
	}

	public static void limpiarListaEmpresas() {
		listaEmpresas.clear();
	}
}
