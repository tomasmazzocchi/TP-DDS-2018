package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.dds.ExceptionHandler.IndicadorExistenteException;
import ar.edu.utn.dds.ExceptionHandler.IndicadorInexistenteException;

public class RepoIndicadores {
	private String nombre;
	private List<Indicador> listaIndicadores = new ArrayList<>();

	public RepoIndicadores(String nombre) {
		this.nombre = nombre;
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
}
