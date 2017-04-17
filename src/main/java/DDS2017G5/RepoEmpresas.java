package DDS2017G5;

import java.util.ArrayList;
import java.util.List;

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

	public List<Empresa> getListaEmpresa(){
		return this.listaEmpresas;
	}

	// Functions and Procedures
    
	public void reset() {
		listaEmpresas.clear();
	}
	
	public void agregarEmpresa(Empresa unaEmpresa){
		if(!listaEmpresas.contains(unaEmpresa)){
			listaEmpresas.add(unaEmpresa);
		} 
		else{
			throw new RuntimeException ("Empresa ya existente");
		}
	}
	public RepoEmpresas(String nombre) {
		this.nombre = nombre;
	}
	
	public void quitarEmpresa(Empresa unaEmpresa){
		if(listaEmpresas.contains(unaEmpresa)){
			listaEmpresas.remove(unaEmpresa);
		} else{
			throw new RuntimeException ("No existe la empresa");
		}
	}
}
