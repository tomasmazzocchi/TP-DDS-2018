package ar.edu.utn.dds.grupo5.Condiciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;

public class MaximizarIndicador implements Condicion {

	private Indicador indicador;
	private String nombre; 
	private List<Empresa> listaEmpresas = new ArrayList<>();

	public MaximizarIndicador(Indicador indicador) {
		this.indicador = indicador;
		nombre = "Maximizar " + indicador.getNombre();
	}
	
	public String getNombre(){
		return this.nombre;
	}

	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		listaEmpresas.addAll(empresas);
		
		Collections.sort(listaEmpresas, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {
				if (indicador.calcularIndicador(empresa1) > (indicador.calcularIndicador(empresa2))) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		return listaEmpresas;
}
}
