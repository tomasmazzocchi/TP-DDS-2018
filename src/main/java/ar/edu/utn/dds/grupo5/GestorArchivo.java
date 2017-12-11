package ar.edu.utn.dds.grupo5;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import ar.edu.utn.dds.ExceptionHandler.ArchivoException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorArchivo {

	private static GestorArchivo instance = null;

	private GestorArchivo() {
	}

	public static GestorArchivo getInstance() {
		if (instance == null) {
			instance = new GestorArchivo();
		}
		return instance;
	}

	public void cargarArchivo( File archivo) {

		Gson gson = new Gson();
		Empresa empresaJson;

		try (Reader reader = new FileReader(archivo)) {
			Type tipoListaEmpresas = new TypeToken<List<Empresa>>(){}.getType();
			List<Empresa> listaEmpresasJson = new ArrayList<Empresa>();
			
			listaEmpresasJson = gson.fromJson(reader, tipoListaEmpresas);


			Iterator<Empresa> iterador = listaEmpresasJson.listIterator();

			while (iterador.hasNext()) {
				empresaJson = (Empresa) iterador.next();
				RepoEmpresas.getListaEmpresa().add(empresaJson);
			}

		} catch (JsonSyntaxException f) {
			throw new ArchivoException("Archivo invalido");
		} catch (IOException e) {
			throw new ArchivoException("Ups, hubo un problema al abrir el archivo");
		}

	}
}