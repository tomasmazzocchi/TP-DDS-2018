import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorArchivo {

	public void cargarArchivo(RepoEmpresas repoEmpresas, String rutaArchivo) {

        Gson gson = new Gson();
         
        try (Reader reader = new FileReader(rutaArchivo)) 
        {
            // new list JSON
            List<Empresa> listaEmpresasJson = new ArrayList<Empresa>();	
            
            // Convert JSON to Java Object
            RepoEmpresas repoEmpresasJson = gson.fromJson(reader, RepoEmpresas.class);
            listaEmpresasJson = repoEmpresasJson.getListaEmpresa();
            

            Iterator<Empresa> iterador = listaEmpresasJson.listIterator();
            //While Iterator has a next element
              while( iterador.hasNext() ) {
                     Empresa empresaJson = (Empresa) iterador.next();
                     repoEmpresas.agregarEmpresa(empresaJson);
                     // Manejar EXCEPCION si la empresa existe
              }
                  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}