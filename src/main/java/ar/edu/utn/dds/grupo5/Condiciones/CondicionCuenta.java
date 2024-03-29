package ar.edu.utn.dds.grupo5.Condiciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;

@Entity
@DiscriminatorValue(value = "condicionCuenta")
public class CondicionCuenta extends Condicion {
	@Transient
	private List<Empresa> listaEmpresas = new ArrayList<>();

			public CondicionCuenta(String nombre, Cuenta cuen, int pond, char restr) {
				this.nombre = nombre;
				this.cuenta = cuen;
				this.ponderacion = pond;
				this.restriccion = restr;
			}
			
	public CondicionCuenta(){
		
	}
			
	@Override
	public List<Empresa> aplicarCondicion(List<Empresa> empresas) {
		if (!listaEmpresas.isEmpty()) {
			listaEmpresas.clear();
		}
		
		listaEmpresas.addAll(empresas);
		
		if(this.restriccion=='>') {
			
		Collections.sort(listaEmpresas, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa empresa1, Empresa empresa2) {
				if (empresa1.getListaCuentas().stream().filter(cuen -> cuen.getNombre().contains(cuenta.getNombre()) )
						.collect(Collectors.toList()).get(0)
						.getValor() < (empresa2.getListaCuentas().stream()
								.filter(cuen -> cuen.getNombre().contains(cuenta.getNombre())).collect(Collectors.toList())
								.get(0).getValor())) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return listaEmpresas;
		} else {
			Collections.sort(listaEmpresas, new Comparator<Empresa>() {
				@Override
				public int compare(Empresa empresa1, Empresa empresa2) {
					if (empresa2.getListaCuentas().stream().filter(cuen -> cuen.getNombre().contains(cuenta.getNombre()))
							.collect(Collectors.toList()).get(0)
							.getValor() < (empresa1.getListaCuentas().stream()
									.filter(cuen -> cuen.getNombre().contains(cuenta.getNombre())).collect(Collectors.toList())
									.get(0).getValor())) {
						return 1;
					} else {
						return -1;
					}
				}
			});
			return listaEmpresas;
		}
	}
}