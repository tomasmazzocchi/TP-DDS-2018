package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ar.edu.utn.dds.ExceptionHandler.IndicadorExistenteException;
import ar.edu.utn.dds.ExceptionHandler.IndicadorInexistenteException;
import ar.edu.utn.dds.ExceptionHandler.RepoIndicadoresException;
import ar.edu.utn.dds.rest.EMFactorySingleton;

public class RepoIndicadores {
	private String nombre;
	private static List<Indicador> listaIndicadores = new ArrayList<>();
	private ExpressionParser parser;

	public RepoIndicadores(String nombre) {
		this.nombre = nombre;
		this.parser = new ExpressionParser();
	}

	public String getNombre() {
		return nombre;
	}

	public List<Indicador> getListaIndicadores() {
		return listaIndicadores;
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

	public static Indicador buscarIndicador(String nombre) {
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

	public List<Indicador> obtenerIndicadores(EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Indicador> criteria = criteriaBuilder.createQuery(Indicador.class);
		Root<Indicador> rootEntry = criteria.from(Indicador.class);
		CriteriaQuery<Indicador> all = criteria.select(rootEntry);
		List<Indicador> resultList = em.createQuery(all).getResultList();
		return resultList;
	}

	public List<Indicador> obtenerIndicadores(EntityManager em, String nombre) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Indicador> criteria = criteriaBuilder.createQuery(Indicador.class);
		Root<Indicador> root = criteria.from(Indicador.class);
		criteria.where(criteriaBuilder.equal(root.get("nombre"), nombre));
		List<Indicador> resultList = em.createQuery(criteria).getResultList();
		return resultList;
	}

	public Indicador obtenerIndicador(EntityManager em, int id) {
		return em.find(Indicador.class, id);
	}

	public static void setearIndicadores(List<Indicador> lista) {
		listaIndicadores = lista;
	}
}
