package ar.edu.utn.dds.grupo5;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaximizarIndicador implements Condicion {

	private Indicador indicador;
	
	private String nombre = "Maximizar Indicador " + indicador.getNombre();

	public MaximizarIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
	
	public String getNombre(){
		return this.nombre;
	}

	public List<Empresa> aplicarCondicion(List<Empresa> listaEmpresas) {

		Collections.sort(listaEmpresas, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {
				if (indicador.calcularIndicador(empresa1) >= (indicador.calcularIndicador(empresa2))) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return listaEmpresas;
	}
}
