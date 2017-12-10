package DTO.Condiciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import DTO.CondicionDTO;
import DTO.EmpresaDTO;
import DTO.IndicadorDTO;

@Entity
@DiscriminatorValue(value = "min_indicador")
public class MinimizarIndicadorDTO extends CondicionDTO {
	@OneToOne
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
