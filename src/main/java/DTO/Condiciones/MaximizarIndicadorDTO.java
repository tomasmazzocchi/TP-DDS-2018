package DTO.Condiciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToOne;
import javax.persistence.Transient;

import DTO.CondicionDTO;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;

public class MaximizarIndicadorDTO extends CondicionDTO {
	@OneToOne()
	private Indicador indicador;
	@Transient
	private List<Empresa> listaEmpresas = new ArrayList<>();

	public MaximizarIndicadorDTO(Indicador indicador) {
		this.indicador = indicador;
		this.nombre = "Maximizar " + indicador.getNombre();
	}

	protected MaximizarIndicadorDTO() {

	}

	public Indicador getIndicador() {
		return indicador;
	}

}
