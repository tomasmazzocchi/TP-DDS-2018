package ar.edu.utn.dds.GUI;

import java.util.List;
import java.util.Observable;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Longevidad;
import ar.edu.utn.dds.grupo5.MaximizarIndicador;
import ar.edu.utn.dds.grupo5.Metodologia;
import ar.edu.utn.dds.grupo5.RepoEmpresas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MetodologiaExistenteController implements Initializable {

	@FXML private Button btnVolver;
	@FXML private Button btnAplicar;
	@FXML private ComboBox<String> comboBox;
	@FXML private TableView<Empresa> tblListado;
	HashMap<String,List<Empresa>> resultados = new HashMap<String,List<Empresa>>();
	RepoEmpresas repoEmpresas = new RepoEmpresas("Empresas");
	List<Cuenta> listaCuentas1 = new ArrayList<Cuenta>();
	List<Cuenta> listaCuentas2 = new ArrayList<Cuenta>();
	List<Indicador> listaIndicadores = new ArrayList<Indicador>();
	List<Condicion> condiciones = new ArrayList<Condicion>();
	Metodologia metodologiaBuffet;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBox.getItems().removeAll(comboBox.getItems());
	    comboBox.getItems().addAll("Metodologia Buffet");
	    comboBox.getSelectionModel().select("Metodologia Buffet");
		Cuenta cuentaEBIDTA1 = new Cuenta("EBIDTA", 100, LocalDate.now(), LocalDate.now());
		Cuenta cuentaEBIDTA2 = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		listaCuentas1.add(cuentaEBIDTA1);
		listaCuentas2.add(cuentaEBIDTA2);
		Empresa empresa1 = new Empresa("Facebook",listaCuentas1,listaIndicadores, LocalDate.now().minusYears(11));
		Empresa empresa2 = new Empresa("Google",listaCuentas2,listaIndicadores, LocalDate.now().minusYears(8));		
		Indicador indicadorROE = new Indicador("ROE", "cu.EBIDTA");
		Longevidad unaLongevidad = new Longevidad(10);
		MaximizarIndicador maxIndicador = new MaximizarIndicador(indicadorROE);
		repoEmpresas = new RepoEmpresas("repoEmpresas");
		repoEmpresas.agregarEmpresa(empresa1);
		repoEmpresas.agregarEmpresa(empresa2);
		condiciones.add(unaLongevidad);
		condiciones.add(maxIndicador);
		metodologiaBuffet = new Metodologia(condiciones);
	}
	
	@FXML public void aplicarMetodologia(ActionEvent event) throws IOException{
		if(event.getSource()==btnAplicar){
			resultados = metodologiaBuffet.aplicarCondiciones(repoEmpresas.getListaEmpresa());
			
		}
	}
	
	@FXML public void volver(ActionEvent event) throws IOException{
		Parent home_page_parent = null;
		if(event.getSource()==btnVolver){
			home_page_parent = FXMLLoader.load(getClass().getResource("PantallaPrincipal.fxml"));
		} 
	  Scene home_page_scene = new Scene(home_page_parent);
      Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
      app_stage.close();
      app_stage.setScene(home_page_scene);
      app_stage.show();
	}
}
