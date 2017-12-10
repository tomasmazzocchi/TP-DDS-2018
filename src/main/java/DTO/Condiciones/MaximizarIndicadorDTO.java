package DTO.Condiciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import DTO.CondicionDTO;
import DTO.EmpresaDTO;
import DTO.IndicadorDTO;

@Entity
@DiscriminatorValue(value = "max_indicador")
public class MaximizarIndicadorDTO extends CondicionDTO {
	@OneToOne
	@JoinColumn(name = "id_indicador")
	private IndicadorDTO indicador;
	@Transient
	private List<EmpresaDTO> listaEmpresas = new ArrayList<>();

	public MaximizarIndicadorDTO(IndicadorDTO indicador) {
		this.indicador = indicador;
		this.nombre = "Maximizar " + indicador.getNombre();
	}

	protected MaximizarIndicadorDTO() {

	}

	public IndicadorDTO getIndicador() {
		return indicador;
	}

}
