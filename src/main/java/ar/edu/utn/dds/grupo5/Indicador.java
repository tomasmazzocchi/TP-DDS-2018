package ar.edu.utn.dds.grupo5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "indicador", schema = "dds2017")
public class Indicador {
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_indicador")
	private int idIndicador;
	@Column(name = "formula")
	private String formula;
	@Column(name = "nombre")
	private String nombre;

	public Indicador(String nombre, String formula) {
		this.setNombre(nombre);
		this.setFormula(formula);
	}

	protected Indicador() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public int calcularIndicador(Empresa unaEmpresa) {

		ExpressionParser parser;
		parser = new ExpressionParser();

		// Hay QUE VER QUE PASARIA SI LA EMPRESA NO TIENE ESE INDICADOR

		return parser.resolverFormula(this.formula, unaEmpresa);

	}

}
