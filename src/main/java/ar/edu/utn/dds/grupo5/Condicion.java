package ar.edu.utn.dds.grupo5;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Condicion")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Condicion {
	protected String nombre;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCondicion;

	public abstract List<Empresa> aplicarCondicion(List<Empresa> empresas);

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
