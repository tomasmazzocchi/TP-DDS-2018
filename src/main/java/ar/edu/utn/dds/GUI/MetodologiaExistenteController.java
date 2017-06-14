package ar.edu.utn.dds.GUI;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MetodologiaExistenteController implements Initializable {

	@FXML private Button btnVolver;
	@FXML private Button btnAplicar;
	@FXML private ComboBox<String> comboBox;
	@FXML private TableView<?> tblListado;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBox.getItems().removeAll(comboBox.getItems());
	    comboBox.getItems().addAll("Metodologia Buffet");
	    comboBox.getSelectionModel().select("Metodologia Buffet");
	}
	
	public void aplicarMetodologia(ActionEvent event) throws IOException{
		if(event.getSource()==btnAplicar){
			
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
