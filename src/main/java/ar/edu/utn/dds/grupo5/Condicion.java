package ar.edu.utn.dds.grupo5;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "condicion", schema = "dds2017")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_condicion")
public abstract class Condicion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_condicion;
	@Column(name = "nombre")
	protected String nombre;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_indicador")
	protected Indicador indicador;

	protected Condicion() {

	}

	public abstract List<Empresa> aplicarCondicion(List<Empresa> empresas);

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
