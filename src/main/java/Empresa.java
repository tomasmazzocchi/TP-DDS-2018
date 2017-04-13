import java.util.List;

public class Empresa {
	private String nombre;
	private List<Cuenta> listaCuentas;
	private List<Indicador> listaIndicadores;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
