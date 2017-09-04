package ar.edu.utn.dds.grupo5.Condiciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;

public class MaximizarIndicador extends Condicion {

	private Indicador indicador;
	private List<Empresa> listaEmpresas = new ArrayList<>();

	public MaximizarIndicador(Indicador indicador) {
		this.indicador = indicador;
		this.nombre = "Maximizar " + indicador.getNombre();
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
		//List<String> listaNombreEmpresas = new ArrayList<>();
		//listaEmpresas.stream().forEach(x -> listaNombreEmpresas.add(x.getNombre()));
		return listaEmpresas;}
}
