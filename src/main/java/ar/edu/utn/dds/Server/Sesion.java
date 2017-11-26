package ar.edu.utn.dds.Server;

import ar.edu.utn.dds.grupo5.Usuario;

public class Sesion {

	private String id;

	private Usuario usuario;

	public Sesion(String string, Usuario usuario) {
		this.id = string;
		this.usuario = usuario;
	}

	public String getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}