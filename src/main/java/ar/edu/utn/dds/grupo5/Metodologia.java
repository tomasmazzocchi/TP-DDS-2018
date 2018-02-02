package ar.edu.utn.dds.grupo5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "metodologia", schema = "dds2017")
public class Metodologia {
	@Id
	@GeneratedValue
	@Column(name = "id_metodologia")
	private int idMetodologia;
	@Column(name = "nombre")
	private String nombre;
	@ManyToMany
	private List<Condicion> condiciones;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuarioAsociado;
	@Transient
	private Map<String, List<Empresa>> hashMapResultados = new LinkedHashMap<>();
	@Transient
	private List<ResultadoMetodologia> listaResultado = new ArrayList<ResultadoMetodologia>();

	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;
	}

	// ----------------------GETTERS AND SETTERS-----------------------------------
	public Metodologia() {

	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public Usuario getUs() {
		return usuarioAsociado;
	}

	public void setUs(Usuario us) {
		this.usuarioAsociado = us;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	public Map<String, List<Empresa>> getHashMapResultados() {
		return hashMapResultados;
	}

	public List<ResultadoMetodologia> getListaResultado() {
		return this.listaResultado;
	}

	public void aplicarCondiciones(List<Empresa> empresas) {
		hashMapResultados.clear();
		condiciones.stream().forEach(condicion -> {
			List<Empresa> resultado = new ArrayList<>();
			resultado = condicion.aplicarCondicion(empresas);
			hashMapResultados.put(condicion.getNombre(), resultado);
		});
		
		empresas.stream().forEach(empresa -> this.listaResultado.add(new ResultadoMetodologia(empresa)));
		
		condiciones.stream().forEach(cond -> hashMapResultados.get(cond.getNombre()).stream().forEach(empresa -> this.sumarPuntuacionAEmpresa(empresa,cond.getNombre(),cond.getPonderacion())));

		Collections.sort(this.listaResultado, new Comparator<ResultadoMetodologia>() {
			@Override
			public int compare (ResultadoMetodologia r1, ResultadoMetodologia r2) {
				return new Integer(r2.getPuntuacion()).compareTo(new Integer(r1.getPuntuacion()));
			}
		});
	}
	
	public void sumarPuntuacionAEmpresa(Empresa e, String nombreCond, int ponderacion) {
		List<Empresa> listaAux = hashMapResultados.get(nombreCond);
		int posicion = listaAux.indexOf(e)+1;
		ResultadoMetodologia res = listaResultado.stream().filter(x -> x.getEmpresa().equals(e)).collect(Collectors.toList()).get(0);
		res.sumarPuntuacion(ponderacion*posicion);
		listaResultado.removeIf(x -> x.getEmpresa().equals(e));
		listaResultado.add(res);
	}
}
