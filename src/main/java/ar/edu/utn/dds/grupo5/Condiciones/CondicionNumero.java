package ar.edu.utn.dds.grupo5.Condiciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Empresa;

@Entity
@DiscriminatorValue(value = "longevidad")
public class CondicionNumero extends Condicion {
	@Column(name = "anios")
	private int anios;
	@Transient
	private List<Empresa> listaEmpresas = new ArrayList<>();

	public CondicionNumero(String nombre, int anios, int pond, char restr) {
		this.anios = anios;
		this.nombre = nombre;
		this.ponderacion = pond;
		this.restriccion = restr;
	}

	protected CondicionNumero() {
	}

	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		LocalDate fechaDesde;
		fechaDesde = LocalDate.now().minusYears(anios);

		if(this.restriccion=='<') {
		listaEmpresas = empresas.stream().filter(empresa -> empresa.esMenorA(fechaDesde)).collect(Collectors.toList());

		Collections.sort(listaEmpresas, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {
				return empresa2.getAnioFundacion().compareTo(empresa1.getAnioFundacion());
			}
		});

		return listaEmpresas;
		}
		else {
			listaEmpresas = empresas.stream().filter(empresa -> empresa.esMayorA(fechaDesde)).collect(Collectors.toList());

			Collections.sort(listaEmpresas, new Comparator<Empresa>() {
				@Override
				public int compare(Empresa empresa1, Empresa empresa2) {
					return empresa1.getAnioFundacion().compareTo(empresa2.getAnioFundacion());
				}
			});

			return listaEmpresas;
		}
			
	}

}
