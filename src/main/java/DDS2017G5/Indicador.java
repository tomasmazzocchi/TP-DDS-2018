package dds2017g5;

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
	
	public double CalcularFormula(Empresa empresa){
		double valor;
		ExpressionParser _parser;
		_parser = new ExpressionParser();
		valor= _parser.parse(this.getFormula(),empresa.getListaCuentas());
		return valor;
		
	}
	
	//Buscar si existe un indicador
	
	public Boolean NoExisteIndicador(List<Indicador> listaIndicadores,String indicadorNombre) {
		
        List<Indicador> lista = listaIndicadores.stream()               
                .filter(indicador -> indicadorNombre.equals(indicador.getNombre()))
                .collect(Collectors.toList());              

        return lista.isEmpty();
		
	}
	
	//Generar indicador por medio de un string.
	
	public void GenerarIndicador(Empresa empresa,String indicadorNombre,String indicadorFormula){
		
		Indicador indicador;
				
        if (NoExisteIndicador(empresa.getListaIndicadores(),indicadorNombre))
        	//Seguir con la generación
        {
        	indicador= new Indicador(indicadorNombre,indicadorFormula);
        	indicador.CalcularFormula(empresa);  
        	//SUPONGO QUE SI DA EXCEPTION NO SE CARGA
        	empresa.getListaIndicadores().add(indicador);
        }
        else
        	//MENSAJE YA EXISTE UN INDICADOR CON ESE NOMBRE
        	throw new RuntimeException ("Indicador ya existente");
		}
}
