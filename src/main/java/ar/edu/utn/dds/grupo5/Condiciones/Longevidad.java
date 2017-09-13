package ar.edu.utn.dds.grupo5.Condiciones;

import java.time.LocalDate;

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
public class Longevidad extends Condicion {

	private int aniosAntiguedad;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_condicion")
	private List<Empresa> listaEmpresas = new ArrayList<>();

	public Longevidad(int aniosAntiguedad) {
		this.aniosAntiguedad = aniosAntiguedad;
		this.nombre = "Longevidad";
	}

	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		LocalDate fechaDesde;
		fechaDesde = LocalDate.now().minusYears(aniosAntiguedad);

		listaEmpresas = empresas.stream().filter(empresa -> empresa.esLongeva(fechaDesde)).collect(Collectors.toList());

		Collections.sort(listaEmpresas, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {
				return empresa1.getAnioFundacion().compareTo(empresa2.getAnioFundacion());
			}
		});

		return listaEmpresas;
	}
}
