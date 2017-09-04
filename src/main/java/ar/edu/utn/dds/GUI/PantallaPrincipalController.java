package ar.edu.utn.dds.GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

	@FXML
	private Button btnCreacion;
	@FXML
	private Button btnExistente;
	@FXML
	private Button btnSalir;

	@FXML
	public void salir() {
		Platform.exit();
	}

	@FXML
	public void botonMetodologiaExistente(ActionEvent evento) throws IOException {
		Parent home_page_parent = null;
		home_page_parent = FXMLLoader.load(getClass().getResource("PantallaMetodologiaExistente.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();

		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	}

	@FXML
	public void botonCrearMetodologia(ActionEvent evento) throws IOException {
		Alert informacion = new Alert(AlertType.INFORMATION);
		informacion.setTitle("Información");
		informacion.setHeaderText(null);
		informacion.setContentText("Ésta funcionalidad aún se encuentra en desarrollo...");

		informacion.showAndWait();
	}
}
