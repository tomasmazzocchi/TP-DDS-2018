package ar.edu.utn.dds.grupo5;

public class ResultadoMetodologia {
	private Empresa empresa;
	private int puntuacion;

	public ResultadoMetodologia(Empresa e) {
		this.empresa = e;
		this.puntuacion = 0;
	}
	
	public Empresa getEmpresa() {
		return this.empresa;
	}
	
	public int getPuntuacion() {
		return this.puntuacion;
	}
	
	public void sumarPuntuacion(int puntos) {
		this.puntuacion += puntos;
	}
}
