package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.List;

public class ManejadorIndicadores {

	private static ManejadorIndicadores instance = null;
	ExpressionParser parser;
	Indicador indicador;

	private ManejadorIndicadores() {
		parser = new ExpressionParser();
	}

	public static ManejadorIndicadores getInstance() {
		if (instance == null) {
			instance = new ManejadorIndicadores();
		}
		return instance;
	}

	public void guardarIndicadorEnEmpresa(Empresa empresa, String indicadorNombre, String indicadorFormula) {
		
		parser.resolverFormula(indicadorFormula, empresa);
		indicador = new Indicador(indicadorNombre, indicadorFormula);
		empresa.getListaIndicadores().add(indicador);

	}

	public void guardarIndicadorEnRepo(RepoIndicadores repoIndicadores, String indicadorNombre,String indicadorFormula) {
		
		if (parser.validarFormula(indicadorFormula)) {
			indicador = new Indicador(indicadorNombre, indicadorFormula);
			repoIndicadores.agregarIndicador(indicador);
		}

	}
	public void guardarIndicadores(RepoIndicadores repoIndicadores,List<String> nombres, List<String> formulas){
		int count  = nombres.size();
		for (int i=0; i<count ; i++) {
		     this.guardarIndicadorEnRepo(repoIndicadores, nombres.get(i),formulas.get(i)); 
		}
	}
}
