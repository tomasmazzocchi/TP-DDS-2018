package ar.edu.utn.dds.grupo5;

public class Indicador {
	private String nombre;
	private String formula ;   /* Cambio 08 */
	
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
	
	//Constructor 
	public Indicador(String nombre,String formula){
		this.setNombre(nombre);
		this.setFormula(formula);
	}
}