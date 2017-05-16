package ar.edu.utn.dds.grupo5;

public class ManejadorIndicadores {
	ExpressionParser _parser;
	Indicador indicador;
	
	public void guardarIndicadorEnEmpresa(Empresa empresa,String indicadorNombre,String indicadorFormula){
		_parser.validarExpresion(indicadorFormula,empresa.getListaCuentas(),empresa.getListaIndicadores());
		indicador = new Indicador(indicadorNombre,indicadorFormula);
		empresa.getListaIndicadores().add(indicador);		
		
	}
	
	public void guardarIndicadorEnRepo(RepoIndicadores repoIndicadores,String indicadorNombre,String indicadorFormula){
		_parser.validarExpresion(indicadorFormula,empresa.getListaCuentas(),empresa.getListaIndicadores());
		indicador = new Indicador(indicadorNombre,indicadorFormula);
		empresa.getListaIndicadores().add(indicador);		
		
	}

}
