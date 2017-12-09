package DTO.Condiciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import DTO.CondicionDTO;
import ar.edu.utn.dds.grupo5.Empresa;

@Entity
@DiscriminatorValue(value = "longevidad")
public class LongevidadDTO extends CondicionDTO {
	@Column(name = "aniosAntiguedad")
	private int aniosAntiguedad;
	@Transient
	private List<Empresa> listaEmpresas = new ArrayList<>();

	public LongevidadDTO(int aniosAntiguedad) {
		this.aniosAntiguedad = aniosAntiguedad;
		this.nombre = "Longevidad";
	}

	protected LongevidadDTO() {

	}

	public int getAniosAntiguedad() {
		return aniosAntiguedad;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

}
