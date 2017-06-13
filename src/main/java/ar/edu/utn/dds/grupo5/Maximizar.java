package ar.edu.utn.dds.grupo5;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Maximizar extends Condicion {
		
	public List<Empresa> maximizarIndicador(Indicador unIndicador,RepoEmpresas repoEmpresas) {
		
		Empresa unaEmpresa;
		List<Empresa> listaEmpresas;
		
	    //ORDENAR LA LISTA DE ACUERDO AL INDICADOR
		Collections.sort(listaEmpresas, (e1, e2) -> 
		unIndicador.calcularIndicador(e1)>=(unIndicador.calcularIndicador(e2)));
		
	}
}
