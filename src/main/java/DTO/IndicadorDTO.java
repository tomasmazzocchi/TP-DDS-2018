package DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.edu.utn.dds.grupo5.Usuario;

@Entity
@Table(name = "indicador", schema = "dds2017")
public class IndicadorDTO {	
		@Column(name = "id_indicador")
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int idIndicador;
		@Column(name = "formula")
		private String formula;
		@Column(name = "nombre")
		private String nombre;
		@OneToOne
		@JoinColumn(name = "id_usuario")
		private Usuario usuarioAsociado;
		@Column(name = "id_empresa")
		private int idEmpresa;		
		@Transient
		private int valor;

		public IndicadorDTO(String nombre, String formula, int idEmpresa, Usuario us) {
			this.nombre = nombre;
			this.formula = formula;
			this.idEmpresa = idEmpresa;
			this.usuarioAsociado = us;
		}
		public IndicadorDTO(){
			
		}
		public int getValor() {
			return valor;
		}

		public String getNombre() {
			return nombre;
		}
		
		public Usuario getUsuario() {
			return usuarioAsociado;
		}

		public String getFormula() {
			return formula;
		}	

}
