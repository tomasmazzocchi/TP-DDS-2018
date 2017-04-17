package Tests;

import com.google.gson.Gson;

import DDS2017G5.Cuenta;
import DDS2017G5.Empresa;
import DDS2017G5.Indicador;
import DDS2017G5.RepoEmpresas;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonCrearArchivo {

    public static void main(String[] args) {
    	
        /** Creo listas **/
    	List<Empresa> listaEmpresas = new ArrayList<>();
    	List<Cuenta> listaCuentas = new ArrayList<>();
    	List<Indicador> listaIndicadores = new ArrayList<>();
    	
    	String nombre_cuenta1 = "EBDITA";
    	String nombre_cuenta2 = "Free Cash Flow";
    	/** creo Cuentas **/    	
    	Cuenta cuenta1 = new Cuenta(nombre_cuenta1,200,LocalDate.now(),LocalDate.now());
    	Cuenta cuenta2 = new Cuenta(nombre_cuenta2,100,LocalDate.parse("2017-01-01"),LocalDate.parse("2017-02-01"));
    	listaCuentas.add(cuenta1);
    	listaCuentas.add(cuenta2);
    	/** creo empresas **/
    	Empresa empresa1 = new Empresa("Facebook",listaCuentas,listaIndicadores);
    	Empresa empresa2 = new Empresa("Google",listaCuentas,listaIndicadores);
    	listaEmpresas.add(empresa1);
    	listaEmpresas.add(empresa2);
    	/** creo un repo aux **/
    	
    	RepoEmpresas repoEmpresasJson = new RepoEmpresas("Repositorio Empresas");
    	repoEmpresasJson.agregarEmpresa(empresa1);
    	repoEmpresasJson.agregarEmpresa(empresa2);
    		  	
    	    	
        //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(repoEmpresasJson);
        System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter("C:\\Users\\Tomás\\Desktop\\RepoEmpresasJson.json")) {

            gson.toJson(repoEmpresasJson, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

