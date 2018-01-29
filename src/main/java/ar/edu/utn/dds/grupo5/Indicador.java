package ar.edu.utn.dds.grupo5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "indicador", schema = "dds2017")
public class Indicador {
	@Column(name = "id_indicador")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idIndicador;
	@Column(name = "formula")
	private String formula;
	@Column(name = "nombre")
	private String nombre;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuarioAsociado;
	@Transient
	private int valor;

	public Indicador(String nombre, String formula) {
		this.setNombre(nombre);
		this.setFormula(formula);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public void setUsuario(Usuario us) {
		this.usuarioAsociado = us;
	}

	public Usuario getUsuario() {
		return this.usuarioAsociado;
	}

	public Indicador() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public int calcularIndicador(Empresa empresa) {

		ExpressionParser parser;
		parser = new ExpressionParser();

		setValor(parser.resolverFormula(this.formula, empresa));
		return getValor();
	}

}
