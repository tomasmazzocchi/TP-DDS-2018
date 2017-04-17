package DDS2017G5;

public class Indicador {
	private String nombre;
	private double valor;
	
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
	//Constructor 
	public Indicador(String nombre,double valor){
		this.setNombre(nombre);
		this.setValor(valor);
	}
	
	
}
