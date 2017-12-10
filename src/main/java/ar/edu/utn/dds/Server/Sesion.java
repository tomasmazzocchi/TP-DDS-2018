package ar.edu.utn.dds.Server;

import DTO.UsuarioDTO;

public class Sesion {

	private String id;

	private UsuarioDTO usuario;

	public Sesion(String string, UsuarioDTO usuario2) {
		this.id = string;
		this.usuario = usuario2;
	}

	public String getId() {
		return id;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
}