package ar.edu.utn.dds.grupo5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.edu.utn.dds.ExceptionHandler.ExpressionParserException;
import ar.edu.utn.dds.ExceptionHandler.RepoIndicadoresException;

@Entity
@Table(name = "empresa", schema = "dds2017")
public class Empresa {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_empresa")
	private int idEmpresa;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "anio_fundacion")
	private LocalDate anioFundacion;
	@OneToMany
	@JoinColumn(name = "id_empresa")
	private List<Cuenta> listaCuentas = new ArrayList<>();
	@OneToMany
	@Column(name = "id_empresa")
	private List<Indicador> listaIndicadores = new ArrayList<>();


	public Empresa(String nombre, List<Cuenta> listaCuentas, List<Indicador> listaIndicadores,
			LocalDate anioFundacion) {
		this.nombre = nombre;
		this.listaCuentas = listaCuentas;
		this.listaIndicadores = listaIndicadores;
		this.anioFundacion = anioFundacion;
	}

	protected Empresa() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getAnioFundacion() {
		return anioFundacion;
	}

	public void setAnioFundacion(LocalDate anioFundacion) {
		this.anioFundacion = anioFundacion;
	}

	public List<Cuenta> getListaCuentas() {
		return this.listaCuentas;
	}

	public List<Indicador> getListaIndicadores() {
		return this.listaIndicadores;
	}

	public void setCuenta(Cuenta unaCuenta) {
		this.listaCuentas.add(unaCuenta);
	}

	public boolean esLongeva(LocalDate fechaDesde) {
		return this.anioFundacion.compareTo(fechaDesde) <= 0;
	}

	public static List<Cuenta> listaDeCuentasValidas(Empresa empresa, LocalDate fechaDesde, LocalDate fechaHasta) {

		List<Cuenta> listaCuentas = empresa.getListaCuentas();

		List<Cuenta> listaCuentasValidas = listaCuentas.stream() // convert list to stream
				.filter(cuenta -> fechaDesde.equals(cuenta.getFechaDesde())
						&& fechaHasta.equals(cuenta.getFechaHasta()))
				.collect(Collectors.toList()); // collect the output and convert streams to a List

		listaCuentasValidas.forEach(System.out::println); // output : spring, node

		return listaCuentasValidas;
	}

	public List<Cuenta> cuentasValidasPorFecha(LocalDate fechaDesde, LocalDate fechaHasta) {

		List<Cuenta> listaCuentasValidasPorFecha = this.listaCuentas.stream().filter(
				p -> p.getFechaDesde().compareTo(fechaDesde) >= 0 && p.getFechaHasta().compareTo(fechaHasta) <= 0)
				.collect(Collectors.toList());
		if (listaCuentasValidasPorFecha.isEmpty()) {
			throw new RepoIndicadoresException("No existen Cuentas para ese Rango de Fechas");
		} else {
			return (listaCuentasValidasPorFecha);
		}
	}

	public Cuenta buscarCuenta(String nombre) {
		List<Cuenta> lista = this.listaCuentas.stream().filter(p -> p.getNombre().equals(nombre))
				.collect(Collectors.toList());
		if (lista.isEmpty()) {
			throw new ExpressionParserException("No existe el nombre de Cuenta");
		} else {
			return (lista.get(0));
		}
	}

	public void agregarIndicadorAEmpresa(Indicador indicador) {
		this.getListaIndicadores().add(indicador);

	}
}
