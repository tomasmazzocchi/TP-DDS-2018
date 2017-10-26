package ar.edu.utn.dds.grupo5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {
	@Column(name = "id_usuario")
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nombre")
	private String nombreUsuario;
	@Column(name = "pass")
	private String password;
		
	public Usuario(String nombre, String pass) {
		this.setNombreUsuario(nombre);
		this.setPassword(pass);
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
