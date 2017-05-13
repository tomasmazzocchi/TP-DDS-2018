package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.List;

import ar.edu.utn.dds.ExceptionHandler.IndicadorExistenteException;

public class GestorIndicador {
	
	private static GestorIndicador instance = null;
	private ExpressionParser _parser;
	
	private GestorIndicador(){		
	}
	
	public static GestorIndicador getInstance(){
		if(instance == null){
			instance = new GestorIndicador();
		}
		return instance;
	}

	public void GenerarIndicador(Empresa empresa,String indicadorNombre,String indicadorFormula) {

        {
            Indicador indicador = new Indicador(indicadorNombre,indicadorFormula);
            _parser = new ExpressionParser();
            
        	// verificar si existe nombre Indicador
            if (indicador.NoExisteNombreIndicador(empresa.getListaIndicadores(),indicadorNombre))
            {
            	//Verifico si la formula funciona
            	indicador.CalcularFormula(empresa.getListaCuentas(),empresa.getListaIndicadores(), _parser);  
            	//SUPONGO QUE SI DA EXCEPTION NO SE CARGA
            	empresa.getListaIndicadores().add(indicador);
            }
            else
            	//MENSAJE YA EXISTE UN INDICADOR CON ESE NOMBRE
            	throw new IndicadorExistenteException ("Indicador ya existente");
    		}
	}
	public int CalcularIndicador(Empresa empresa,LocalDate fechaDesde,LocalDate fechaHasta,Indicador indicador){
		List<Cuenta> listaCuentasPorFecha = Cuenta.CuentasValidasPorFecha(empresa.getListaCuentas(), fechaDesde, fechaHasta);
		return(indicador.CalcularFormula(listaCuentasPorFecha,empresa.getListaIndicadores(), _parser));		
	}
}
        		
		