package DTO.Condiciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToOne;
import javax.persistence.Transient;

import DTO.CondicionDTO;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;

public class MinimizarIndicadorDTO extends CondicionDTO {
	@OneToOne()
	private Indicador indicador;
	@Transient
	private List<Empresa> listaEmpresas = new ArrayList<>();
	
	public MinimizarIndicadorDTO(Indicador indicador) {
		this.indicador = indicador;
		this.nombre = "Minimizar " + indicador.getNombre();
	}
	
	protected MinimizarIndicadorDTO() {
		
	}

	public Indicador getIndicador() {
		return indicador;
	}
	
}
