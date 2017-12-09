package DTO.Condiciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToOne;
import javax.persistence.Transient;

import DTO.CondicionDTO;
import DTO.EmpresaDTO;
import DTO.IndicadorDTO;

public class MinimizarIndicadorDTO extends CondicionDTO {
	@OneToOne()
	private IndicadorDTO indicador;
	@Transient
	private List<EmpresaDTO> listaEmpresas = new ArrayList<>();

	public MinimizarIndicadorDTO(IndicadorDTO indicador) {
		this.indicador = indicador;
		this.nombre = "Minimizar " + indicador.getNombre();
	}

	protected MinimizarIndicadorDTO() {

	}

	public IndicadorDTO getIndicador() {
		return indicador;
	}

}
