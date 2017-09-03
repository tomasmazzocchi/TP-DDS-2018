package ar.edu.utn.dds.grupo5.Condiciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;

public class MinimizarIndicador implements Condicion {

	private Indicador indicador;
	private List<Empresa> listaEmpresa = new ArrayList<>();;
	private String nombre;

	public MinimizarIndicador(Indicador indicador) {
		this.indicador = indicador;
		nombre = "Minimizar " + indicador.getNombre();
	}
	
	public String getNombre(){
		return this.nombre;
	}

	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		listaEmpresa.addAll(empresas);

		Collections.sort(listaEmpresa, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {
				if (indicador.calcularIndicador(empresa1) > (indicador.calcularIndicador(empresa2))) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return listaEmpresa;
	}
}
