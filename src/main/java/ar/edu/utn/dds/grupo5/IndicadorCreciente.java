package ar.edu.utn.dds.grupo5;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IndicadorCreciente implements Condicion {

	private String nombre;
	List<Empresa> listaEmpresa;
	
	 public String getNombre() {
		 return this.nombre;
	 }
	
	public IndicadorCreciente() {
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		Collections.sort(empresas, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {
				if ( empresa1.getListaCuentas().stream().filter(cuenta->cuenta.getNombre().equals("Margen")).collect(Collectors.toList()).get(0).getValor() > 
				(empresa2.getListaCuentas().stream().filter(cuenta->cuenta.getNombre().equals("Margen")).collect(Collectors.toList()).get(0).getValor())) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		listaEmpresa = empresas;
		return listaEmpresa;
	}

	
	
}
