package ar.edu.utn.dds.grupo5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Indicador")
public class Indicador {
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idIndicador")
	private int idIndicador;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "formula")
	private String formula;

	public Indicador(String nombre, String formula) {
		this.setNombre(nombre);
		this.setFormula(formula);
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