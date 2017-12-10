package DTO;

import javax.persistence.Column;
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
public class CondicionDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_condicion")
	private int id_condicion;
	@Column(name = "nombre")
	protected String nombre;

	protected CondicionDTO() {

	}

	public String getNombre() {
		return nombre;
	}
}
