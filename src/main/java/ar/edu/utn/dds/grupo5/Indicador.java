package ar.edu.utn.dds.grupo5;

public class Indicador {
	private String nombre;
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