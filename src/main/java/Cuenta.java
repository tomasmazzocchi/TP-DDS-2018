import java.time.LocalDateTime;

public class Cuenta {
	private String nombre;
	private double valor;
	private LocalDateTime fechaDesde;
	private LocalDateTime fechaHasta;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public LocalDateTime getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(LocalDateTime fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public LocalDateTime getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(LocalDateTime fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}

