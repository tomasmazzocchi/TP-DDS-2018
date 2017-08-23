package ar.edu.utn.dds.grupo5.Condiciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Empresa;

public class MargenCreciente implements Condicion {

	private String nombre="Margen creciente";
	List<Empresa> listaEmpresa = new ArrayList<>();
	
	 public String getNombre() {
		 return this.nombre;
	 }
	
	public MargenCreciente() {
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		listaEmpresa.addAll(empresas);
		Collections.sort(listaEmpresa, new Comparator<Empresa>() {
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
		return listaEmpresa;
	}

	
	
}
