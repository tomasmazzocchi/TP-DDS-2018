package ar.edu.utn.dds.grupo5;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

//@org.mongodb.morphia.annotations.Embedded
@Entity
public abstract class Condicion {
	protected String nombre;
	
	@Id
	@GeneratedValue
	private int idCondicion;
	
	public abstract List<Empresa> aplicarCondicion(List<Empresa> empresas);
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
}
