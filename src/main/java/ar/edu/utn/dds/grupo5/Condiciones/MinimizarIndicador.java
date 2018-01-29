package ar.edu.utn.dds.grupo5.Condiciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;

@Entity
@DiscriminatorValue(value = "min_indicador")
public class MinimizarIndicador extends Condicion {

	@Transient
	private List<Empresa> listaEmpresas = new ArrayList<>();

	public MinimizarIndicador(Indicador indicador) {
		this.indicador = indicador;
		this.nombre = "Minimizar " + indicador.getNombre();
	}

	protected MinimizarIndicador() {

	}

	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		if (!listaEmpresas.isEmpty()) {
			listaEmpresas.clear();
		}
		listaEmpresas.addAll(empresas);

		Collections.sort(listaEmpresas, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {
				if (indicador.calcularIndicador(empresa1) > (indicador.calcularIndicador(empresa2))) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return listaEmpresas;
	}
}
