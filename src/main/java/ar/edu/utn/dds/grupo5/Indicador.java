package ar.edu.utn.dds.grupo5;

import java.util.Iterator;
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
		this.setFormula(formula);
	}

	//Buscar si existe un indicador
	
	public Boolean NoExisteNombreIndicador(List<Indicador> listaIndicadores,String indicadorNombre) {
		
        List<Indicador> lista = listaIndicadores.stream()               
                .filter(indicador -> indicadorNombre.equals(indicador.getNombre()))
                .collect(Collectors.toList());              

        return lista.isEmpty();
		
	}
	//metodo buscar indicador
		public static Indicador BuscaIndicador(String nombre , List<Indicador> listaIndicadores) {
		Iterator<Indicador> i = listaIndicadores.iterator();
		while (i.hasNext())
		  {
			Indicador indicador = (Indicador) i.next();
			if (nombre.equals(indicador.getNombre()))
			{
				return indicador;
			}

		}
		return null;
	}
}