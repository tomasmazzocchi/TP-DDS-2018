package ar.edu.utn.dds.grupo5;

import java.util.List;

public interface Condicion {
		
	public List<Empresa> aplicarCondicion(List<Empresa> empresas);
	public String getNombre();
}
