package ar.edu.utn.dds.grupo5;

public class ManejadorIndicadores {
	
	private static ManejadorIndicadores instance = null;
	ExpressionParser _parser;
	Indicador indicador;
	
	private ManejadorIndicadores(){		
	}
	
	public static ManejadorIndicadores getInstance(){
		if(instance == null){
			instance = new ManejadorIndicadores();
		}
		return instance;
	}
	
	public void guardarIndicadorEnEmpresa(Empresa empresa,String indicadorNombre,String indicadorFormula){
		_parser = new ExpressionParser();
		_parser.parse(indicadorFormula,empresa.getListaCuentas(),empresa.getListaIndicadores());
		indicador = new Indicador(indicadorNombre,indicadorFormula);
		empresa.getListaIndicadores().add(indicador);		
		
	}
	
	public void guardarIndicadorEnRepo(RepoIndicadores repoIndicadores,String indicadorNombre,String indicadorFormula){
		_parser = new ExpressionParser();
		if (_parser.validarExpresion(indicadorFormula))
		{
			indicador = new Indicador(indicadorNombre,indicadorFormula);
			repoIndicadores.getListaIndicadores().add(indicador);	
		}
		
	}

}
