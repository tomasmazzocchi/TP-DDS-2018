package ar.edu.utn.dds.grupo5;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import ar.edu.utn.dds.ExceptionHandler.ArchivoException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompiladorParser {
	
	private static CompiladorParser instance = null;
	
	private CompiladorParser(){		
	}
	
	public static CompiladorParser getInstance(){
		if(instance == null){
			instance = new CompiladorParser();
		}
		return instance;
	}

	public void cargarArchivo(RepoEmpresas repoEmpresas, File archivo) {

        Gson gson = new Gson();
        Empresa empresaJson;
        
        try (Reader reader = new FileReader(archivo))
        {
            List<Empresa> listaEmpresasJson = new ArrayList<Empresa>();	
            
            RepoEmpresas repoEmpresasJson = gson.fromJson(reader, RepoEmpresas.class);
            listaEmpresasJson = repoEmpresasJson.getListaEmpresa();
            

            Iterator<Empresa> iterador = listaEmpresasJson.listIterator();
            
              while( iterador.hasNext() ) {
                     empresaJson = (Empresa) iterador.next();
                     repoEmpresas.agregarEmpresa(empresaJson);
              }    
              
        }
        catch (JsonSyntaxException f){
        	throw new ArchivoException("Archivo invalido");
        }
        catch (IOException e) {
        	throw new ArchivoException("Ups, hubo un problema al abrir el archivo");
        }
        
    }
}