package DTO.Condiciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import DTO.CondicionDTO;
import ar.edu.utn.dds.grupo5.Empresa;

@Entity
@DiscriminatorValue(value = "margen_creciente ")
public class MargenCrecienteDTO extends CondicionDTO {
	@Transient
	private List<Empresa> listaEmpresas = new ArrayList<>();

	public MargenCrecienteDTO() {
		this.nombre = "Margen creciente";
	}

}
