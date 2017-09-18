package ar.edu.utn.dds.grupo5;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "condicion", schema = "dds2017")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_condicion")
public abstract class Condicion {
	protected String nombre;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCondicion;

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
