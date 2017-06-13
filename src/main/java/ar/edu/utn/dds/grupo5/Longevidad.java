package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Longevidad extends Condicion {
		
	public List<Empresa> longevidadEmpresas(int aniosAntiguedad,RepoEmpresas repoEmpresas) {
		
		List<Empresa> listaEmpresas;
		LocalDate fechaDesde;
		
		listaEmpresas = repoEmpresas.getListaEmpresa();
	
		fechaDesde = LocalDate.now().minusYears(aniosAntiguedad);
		
		
	    //ORDENAR LA LISTA DE ACUERDO AL ANIO DE FUNDACION
		
		List<Empresa> listaEmpresasLongevas = listaEmpresas.stream().filter(
				empresa -> empresa.getAnioFundacion().compareTo(fechaDesde) <= 0)
				.collect(Collectors.toList());
		
		return listaEmpresasLongevas;
		
	}
}
