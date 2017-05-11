package dds2017g5;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorArchivo {
	
	private static GestorArchivo instance = null;
	
	private GestorArchivo(){		
	}
	
	public static GestorArchivo getInstance(){
		if(instance == null){
			instance = new GestorArchivo();
		}
		return instance;
	}

	public void cargarArchivo(RepoEmpresas repoEmpresas, String rutaArchivo) {

        Gson gson = new Gson();
        Empresa empresaJson;
        
        try (Reader reader = new FileReader(rutaArchivo))
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
        	throw new RuntimeException("Archivo invalido");
        }
        catch (IOException e) {
        	throw new RuntimeException("Ruta inexistente");
        }
        
    }
}