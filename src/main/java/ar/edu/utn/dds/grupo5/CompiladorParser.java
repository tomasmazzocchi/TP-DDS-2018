package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.List;

import ar.edu.utn.dds.ExceptionHandler.IndicadorExistenteException;

public class CompiladorParser {
	
	private static CompiladorParser instance = null;
	private ExpressionParser _parser;
	
	private CompiladorParser(){		
	}
	
	public static CompiladorParser getInstance(){
		if(instance == null){
			instance = new CompiladorParser();
		}
		return instance;
	}

	public void GenerarIndicador(Empresa empresa,String indicadorNombre,String indicadorFormula) {

        {
            Indicador indicador = new Indicador(indicadorNombre,indicadorFormula);
         
        	// verificar si existe nombre Indicador
            if (ManejadorIndicadores.getInstance().noExisteNombreIndicador(empresa.getListaIndicadores(),indicadorNombre))
            {
            	//Verifico si la formula funciona
            	_parser.resolverFormula(indicador.getFormula(),empresa.getListaCuentas(),empresa.getListaIndicadores()); 
            	//SUPONGO QUE SI DA EXCEPTION NO SE CARGA
            	empresa.getListaIndicadores().add(indicador);
            }
            else
            	//MENSAJE YA EXISTE UN INDICADOR CON ESE NOMBRE
            	throw new IndicadorExistenteException ("Indicador ya existente");
    		}
	}
	public int CalcularIndicador(Empresa empresa,LocalDate fechaDesde,LocalDate fechaHasta,Indicador indicador){
		//Lo que hago aca es generar una lista de cuentas para calcular el indicador en un rango de fechas		
		List<Cuenta> listaCuentasPorFecha = ManejadorIndicadores.getInstance().cuentasValidasPorFecha(empresa.getListaCuentas(), fechaDesde, fechaHasta);
		return(_parser.resolverFormula(indicador.getFormula(),listaCuentasPorFecha,empresa.getListaIndicadores()));		
	}
}
        		
		