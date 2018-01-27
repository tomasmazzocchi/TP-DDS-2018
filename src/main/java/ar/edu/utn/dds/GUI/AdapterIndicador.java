package ar.edu.utn.dds.GUI;

import DTO.IndicadorDTO;
import ar.edu.utn.dds.grupo5.Indicador;

public class AdapterIndicador {
	public static Indicador adaptar(IndicadorDTO ind) {
		Indicador indicadorAdaptado = new Indicador(ind.getNombre(),ind.getFormula());
		return indicadorAdaptado;
	}
}
