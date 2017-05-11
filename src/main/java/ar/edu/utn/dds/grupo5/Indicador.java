package ar.edu.utn.dds.grupo5;

import java.util.List;
import java.util.stream.Collectors;

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
		this.setFormula(nombre);
	}
	//Calcular Indicador   /* Cambio 08 */
	
	public int CalcularFormula(List<Cuenta> listaCuentas,List<Indicador> listaIndicadores,ExpressionParser _parser){
		return (_parser.parse(this.getFormula(),listaCuentas,listaIndicadores));
	}
	
	//Buscar si existe un indicador
	
	public Boolean NoExisteNombreIndicador(List<Indicador> listaIndicadores,String indicadorNombre) {
		
        List<Indicador> lista = listaIndicadores.stream()               
                .filter(indicador -> indicadorNombre.equals(indicador.getNombre()))
                .collect(Collectors.toList());              

        return lista.isEmpty();
		
	}
}