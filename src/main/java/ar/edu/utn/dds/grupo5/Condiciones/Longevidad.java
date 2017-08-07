package ar.edu.utn.dds.grupo5.Condiciones;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Empresa;

public class Longevidad implements Condicion {
	
	private int aniosAntiguedad;
	private String nombre = "Longevidad";
	
	public Longevidad(int aniosAntiguedad) {
		this.aniosAntiguedad = aniosAntiguedad;
	}
	
	public String getNombre(){
		return this.nombre;
	}

	public List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas) {
		LocalDate fechaDesde;
		fechaDesde = LocalDate.now().minusYears(aniosAntiguedad);

		listaEmpresas.stream().filter(empresa -> empresa.esLongeva(fechaDesde)).collect(Collectors.toList());
		
		Collections.sort(listaEmpresas, new Comparator<Empresa>(){
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {				
				return empresa1.getAnioFundacion().compareTo(empresa2.getAnioFundacion());
			}			
		});
		return listaEmpresas;
	}
}
