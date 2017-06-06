package ar.edu.utn.dds.GUI;

import javafx.scene.control.Button;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PantallaPrincipalController {	
	
	@FXML private Button btnCreacion;
	@FXML private Button btnExistente;
	@FXML private Button btnSalir;
	
	@FXML public void salir() {
		Platform.exit();
	}
	
	@FXML public void cambiarPantalla(ActionEvent event) throws IOException{
		  Parent home_page_parent = null;
			if(event.getSource()==btnCreacion){
				home_page_parent = FXMLLoader.load(getClass().getResource("PantallaCreacionMetodologia.fxml"));
			} else if(event.getSource()==btnExistente){
				home_page_parent = FXMLLoader.load(getClass().getResource("PantallaMetodologiaExistente.fxml"));
			}
		  Scene home_page_scene = new Scene(home_page_parent);
	      Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        
	      app_stage.hide();
	      app_stage.setScene(home_page_scene);
	      app_stage.show(); 		  
	}
}
