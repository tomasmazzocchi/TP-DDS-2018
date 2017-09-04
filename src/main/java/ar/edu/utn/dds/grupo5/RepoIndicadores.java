package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.dds.ExceptionHandler.IndicadorExistenteException;
import ar.edu.utn.dds.ExceptionHandler.IndicadorInexistenteException;
import ar.edu.utn.dds.ExceptionHandler.RepoIndicadoresException;

public class RepoIndicadores {
	private String nombre;
	private List<Indicador> listaIndicadores = new ArrayList<>();
	private ExpressionParser parser;

	public RepoIndicadores(String nombre) {
		this.nombre = nombre;
		this.parser = new ExpressionParser();
	}

	public String getNombre() {
		return nombre;
	}

	public List<Indicador> getListaIndicadores() {
		return this.listaIndicadores;
	}

	public void reset() {
		listaIndicadores.clear();
	}

	public void agregarIndicador(Indicador unIndicador) {
		if (!listaIndicadores.contains(unIndicador)) {
			listaIndicadores.add(unIndicador);
		} else {
			throw new IndicadorExistenteException("Indicador ya existente");
		}
	}

	public void quitarIndicador(Indicador unaIndicador) {
		if (listaIndicadores.contains(unaIndicador)) {
			listaIndicadores.remove(unaIndicador);
		} else {
			throw new IndicadorInexistenteException("No existe el Indicador");
		}
	}

	public Boolean indicadorExistente(List<Indicador> listaIndicadores, String indicadorNombre) {

		List<Indicador> lista = listaIndicadores.stream()
				.filter(indicador -> indicadorNombre.equals(indicador.getNombre())).collect(Collectors.toList());

		return !lista.isEmpty();

	}

	public static Indicador buscarIndicador(String nombre, List<Indicador> listaIndicadores) {

		List<Indicador> lista = listaIndicadores.stream().filter(p -> p.getNombre().equals(nombre))
				.collect(Collectors.toList());
		if (lista.isEmpty()) {
			throw new RepoIndicadoresException("No existe el nombre del Indicador");
		} else {
			return (lista.get(0));
		}
	}

	public void guardarIndicadorEnEmpresa(Empresa empresa, String indicadorNombre, String indicadorFormula) {
		parser.resolverFormula(indicadorFormula, empresa);
		Indicador indicador = new Indicador(indicadorNombre, indicadorFormula);
		empresa.agregarIndicadorAEmpresa(indicador);

	}

	public void guardarIndicadorEnRepo(String indicadorNombre, String indicadorFormula) {

		if (parser.validarFormula(indicadorFormula)) {
			Indicador indicador = new Indicador(indicadorNombre, indicadorFormula);
			agregarIndicador(indicador);
		}

	}

	public void guardarIndicadores(List<String> nombres, List<String> formulas) {
		int count = nombres.size();
		for (int i = 0; i < count; i++) {
			this.guardarIndicadorEnRepo(nombres.get(i), formulas.get(i));
		}
	}
}
