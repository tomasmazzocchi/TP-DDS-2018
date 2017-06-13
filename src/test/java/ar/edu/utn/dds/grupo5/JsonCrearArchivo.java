package ar.edu.utn.dds.grupo5;

import com.google.gson.Gson;

import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.RepoEmpresas;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonCrearArchivo {

	public static void main(String[] args) {

		List<Empresa> listaEmpresas = new ArrayList<>();
		List<Cuenta> listaCuentas = new ArrayList<>();
		List<Indicador> listaIndicadores = new ArrayList<>();

		String nombre_cuenta1 = "EBDITA";
		String nombre_cuenta2 = "Free Cash Flow";
		Cuenta cuenta1 = new Cuenta(nombre_cuenta1, 200, LocalDate.now(), LocalDate.now());
		Cuenta cuenta2 = new Cuenta(nombre_cuenta2, 100, LocalDate.parse("2017-01-01"), LocalDate.parse("2017-02-01"));
		listaCuentas.add(cuenta1);
		listaCuentas.add(cuenta2);
		Empresa empresa1 = new Empresa("Facebook", listaCuentas, listaIndicadores, LocalDate.now());
		Empresa empresa2 = new Empresa("Google", listaCuentas, listaIndicadores, LocalDate.now());
		listaEmpresas.add(empresa1);
		listaEmpresas.add(empresa2);

		RepoEmpresas repoEmpresasJson = new RepoEmpresas("Repositorio Empresas");
		repoEmpresasJson.agregarEmpresa(empresa1);
		repoEmpresasJson.agregarEmpresa(empresa2);

		Gson gson = new Gson();
		String json = gson.toJson(repoEmpresasJson);
		System.out.println(json);

		try (FileWriter writer = new FileWriter("C:\\Users\\Tomás\\Desktop\\RepoEmpresasJson.json")) {

			gson.toJson(repoEmpresasJson, writer);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
