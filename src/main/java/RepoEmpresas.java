import java.util.ArrayList;
import java.util.List;

public class RepoEmpresas {
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private List<Empresa> listaEmpresas = new ArrayList<>();
	
	public void agregarEmpresa(Empresa unaEmpresa){
		if(!listaEmpresas.contains(unaEmpresa)){
			listaEmpresas.add(unaEmpresa);
		} else{
			throw new RuntimeException ("Empresa ya existente");
		}
	}
	public void quitarEmpresa(Empresa unaEmpresa){
		if(listaEmpresas.contains(unaEmpresa)){
			listaEmpresas.remove(unaEmpresa);
		} else{
			throw new RuntimeException ("No existe la empresa");
		}
	}
}
