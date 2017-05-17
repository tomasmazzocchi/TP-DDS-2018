package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.dds.ExceptionHandler.ManejadorIndicadoresException;

public class ManejadorIndicadores {

	private static ManejadorIndicadores instance = null;
	ExpressionParser _parser;
	Indicador indicador;

	private ManejadorIndicadores() {
	}

	public static ManejadorIndicadores getInstance() {
		if (instance == null) {
			instance = new ManejadorIndicadores();
		}
		return instance;
	}

	public void guardarIndicadorEnEmpresa(Empresa empresa, String indicadorNombre, String indicadorFormula) {
		_parser = new ExpressionParser();
		_parser.resolverFormula(indicadorFormula, empresa.getListaCuentas(), empresa.getListaIndicadores());
		indicador = new Indicador(indicadorNombre, indicadorFormula);
		empresa.getListaIndicadores().add(indicador);

	}

	public void guardarIndicadorEnRepo(RepoIndicadores repoIndicadores, String indicadorNombre,
			String indicadorFormula) {
		_parser = new ExpressionParser();
		if (_parser.validarFormula(indicadorFormula)) {
			indicador = new Indicador(indicadorNombre, indicadorFormula);
			repoIndicadores.getListaIndicadores().add(indicador);
		}

	}

	public int calcularIndicadorEntreFechas(Empresa empresa, LocalDate fechaDesde, LocalDate fechaHasta,
			Indicador indicador) {
		// Lo que hago aca es generar una lista de cuentas para calcular el indicador en un rango de fechas
		List<Cuenta> listaCuentasPorFecha = ManejadorIndicadores.getInstance()
				.cuentasValidasPorFecha(empresa.getListaCuentas(), fechaDesde, fechaHasta);
		return (_parser.resolverFormula(indicador.getFormula(), listaCuentasPorFecha, empresa.getListaIndicadores()));
	}

	// metodo buscar cuenta
	public Cuenta buscaCuenta(String nombre, List<Cuenta> listaCuentas) {
		List<Cuenta> lista = listaCuentas.stream().filter(p -> p.getNombre().equals(nombre))
				.collect(Collectors.toList());
		if (lista.isEmpty()) {
			throw new ManejadorIndicadoresException("No existe el nombre de Cuenta");
		} else {
			return (lista.get(0));
		}
	}

	// Busca cuentas entre dos fechas
	public List<Cuenta> cuentasValidasPorFecha(List<Cuenta> listaCuentas, LocalDate fechaDesde, LocalDate fechaHasta) {

		List<Cuenta> listaCuentasValidasPorFecha = listaCuentas.stream().filter(
				p -> p.getFechaDesde().compareTo(fechaDesde) >= 0 && p.getFechaHasta().compareTo(fechaHasta) <= 0)
				.collect(Collectors.toList());
		if (listaCuentasValidasPorFecha.isEmpty()) {
			throw new ManejadorIndicadoresException("No existen Cuentas para ese Rango de Fechas");
		} else {
			return (listaCuentasValidasPorFecha);
		}
	}

	// Buscar si existe un indicador

	public Boolean noExisteNombreIndicador(List<Indicador> listaIndicadores, String indicadorNombre) {

		List<Indicador> lista = listaIndicadores.stream()
				.filter(indicador -> indicadorNombre.equals(indicador.getNombre())).collect(Collectors.toList());

		return lista.isEmpty();

	}

	// metodo buscar indicador
	public Indicador buscaIndicador(String nombre, List<Indicador> listaIndicadores) {
		List<Indicador> lista = listaIndicadores.stream().filter(p -> p.getNombre().equals(nombre))
				.collect(Collectors.toList());
		if (lista.isEmpty()) {
			throw new ManejadorIndicadoresException("No existe el nombre del Indicador");
		} else {
			return (lista.get(0));
		}
	}
}
