import java.util.ArrayList;
import java.util.List;

public class Empresa {
	private String nombre;
	private List<Cuenta> listaCuentas = new ArrayList<>();
	private List<Indicador> listaIndicadores = new ArrayList<>();
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
