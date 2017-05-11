package ar.edu.utn.dds.grupo5;

public class GestorIndicador {
	
	private static GestorIndicador instance = null;
	
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
            Indicador indicador;
            ExpressionParser _parser;
            

            indicador = new Indicador(indicadorNombre,indicadorFormula);
            _parser = new ExpressionParser();
            
        	// verificar si existe nombre Indicador
            if (indicador.NoExisteNombreIndicador(empresa.getListaIndicadores(),indicadorNombre))
            {
            	//Verifico si la formula funciona
            	indicador.CalcularFormula(empresa, _parser);  
            	//SUPONGO QUE SI DA EXCEPTION NO SE CARGA
            	empresa.getListaIndicadores().add(indicador);
            }
            else
            	//MENSAJE YA EXISTE UN INDICADOR CON ESE NOMBRE
            	throw new RuntimeException ("Nombre Indicador Duplicado");
    		}
	}
}
        		
		