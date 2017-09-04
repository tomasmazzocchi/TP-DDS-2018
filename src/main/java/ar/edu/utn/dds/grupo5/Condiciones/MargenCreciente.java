package ar.edu.utn.dds.grupo5.Condiciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Empresa;

@Entity
public class MargenCreciente extends Condicion {

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCondicion")
	private List<Empresa> listaEmpresas = new ArrayList<>();

	public MargenCreciente() {
		this.nombre = "Margen creciente";
	}

	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		listaEmpresas.addAll(empresas);
		Collections.sort(listaEmpresas, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {
				if (empresa1.getListaCuentas().stream().filter(cuenta -> cuenta.getNombre().equals("Margen"))
						.collect(Collectors.toList()).get(0)
						.getValor() > (empresa2.getListaCuentas().stream()
								.filter(cuenta -> cuenta.getNombre().equals("Margen")).collect(Collectors.toList())
								.get(0).getValor())) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		// List<String> listaNombreEmpresas = new ArrayList<>();
		// listaEmpresas.stream().forEach(x -> listaNombreEmpresas.add(x.getNombre()));
		return listaEmpresas;
	}

}
