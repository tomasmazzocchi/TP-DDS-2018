package ar.edu.utn.dds.GUI;

import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ar.edu.utn.dds.grupo5.Condicion;
import ar.edu.utn.dds.grupo5.Cuenta;
import ar.edu.utn.dds.grupo5.Empresa;
import ar.edu.utn.dds.grupo5.Indicador;
import ar.edu.utn.dds.grupo5.Metodologia;
import ar.edu.utn.dds.grupo5.RepoEmpresas;
import ar.edu.utn.dds.grupo5.Condiciones.Longevidad;
import ar.edu.utn.dds.grupo5.Condiciones.MargenCreciente;
import ar.edu.utn.dds.grupo5.Condiciones.MaximizarIndicador;
import ar.edu.utn.dds.grupo5.Condiciones.MinimizarIndicador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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

	@FXML
	private Button btnVolver;
	@FXML
	private Button btnAplicar;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private TableView<Empresa> tblLongevidad;
	@FXML
	private TableView<Empresa> tblMaxROE;
	@FXML
	private TableView<Empresa> tblMinROA;
	@FXML
	private TableView<Empresa> tblMargenCreciente;
	@FXML
	private TableColumn<Empresa, String> columnaLongevidad = new TableColumn<Empresa, String>();
	@FXML
	private TableColumn<Empresa, String> columnaMaxROE = new TableColumn<Empresa, String>();
	@FXML
	private TableColumn<Empresa, String> columnaMinROA = new TableColumn<Empresa, String>();
	@FXML
	private TableColumn<Empresa, String> columnaMargenCreciente = new TableColumn<Empresa, String>();

	private List<Cuenta> listaCuentasFacebook = new ArrayList<>();
	private List<Cuenta> listaCuentasGoogle = new ArrayList<>();
	private List<Cuenta> listaCuentasTwitter = new ArrayList<>();
	private List<Indicador> listaIndicadoresFacebook;
	private List<Indicador> listaIndicadoresGoogle;
	private List<Indicador> listaIndicadoresTwitter;
	private Empresa facebook;
	private Empresa google;
	private Empresa twitter;
	private RepoEmpresas repoEmpresas;
	private Metodologia metodologiaBuffet;
	private Longevidad unaLongevidad;
	private Indicador indicadorROE;
	private Indicador indicadorROA;
	private Indicador indicadorDeudaFacebook;
	private Indicador indicadorDeudaGoogle;
	private Indicador indicadorDeudaTwitter;
	private MaximizarIndicador maxIndicador;
	private MinimizarIndicador minIndicador;
	private MargenCreciente margenCreciente;
	private Cuenta cuentaEBIDTAFacebook;
	private Cuenta cuentaMargenFacebook;
	private Cuenta cuentaEBIDTAGoogle;
	private Cuenta cuentaMARGENGooge;
	private Cuenta cuentaEBIDTATwitter;
	private Cuenta cuentaMARGENTwitter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBox.getItems().removeAll(comboBox.getItems());
		comboBox.getItems().addAll("Metodologia Buffet");
		comboBox.getSelectionModel().select("Metodologia Buffet");

		cuentaEBIDTAFacebook = new Cuenta("EBIDTA", 100, LocalDate.now(), LocalDate.now());
		cuentaMargenFacebook = new Cuenta("Margen", 200, LocalDate.now(), LocalDate.now());
		cuentaEBIDTAGoogle = new Cuenta("EBIDTA", 200, LocalDate.now(), LocalDate.now());
		cuentaMARGENGooge = new Cuenta("Margen", 300, LocalDate.now(), LocalDate.now());
		cuentaEBIDTATwitter = new Cuenta("EBIDTA", 300, LocalDate.now(), LocalDate.now());
		cuentaMARGENTwitter = new Cuenta("Margen", 400, LocalDate.now(), LocalDate.now());

		listaCuentasFacebook.add(cuentaEBIDTAFacebook);
		listaCuentasFacebook.add(cuentaMargenFacebook);
		listaCuentasGoogle.add(cuentaEBIDTAGoogle);
		listaCuentasGoogle.add(cuentaMARGENGooge);
		listaCuentasTwitter.add(cuentaEBIDTATwitter);
		listaCuentasTwitter.add(cuentaMARGENTwitter);

		listaIndicadoresFacebook = new ArrayList<>();
		listaIndicadoresGoogle = new ArrayList<>();
		listaIndicadoresTwitter = new ArrayList<>();

		indicadorROE = new Indicador("ROE", "cu.EBIDTA");
		indicadorROA = new Indicador("ROA", "cu.Margen");
		indicadorDeudaFacebook = new Indicador("DEUDA", "300");
		indicadorDeudaGoogle = new Indicador("DEUDA", "400");
		indicadorDeudaTwitter = new Indicador("DEUDA", "1000");

		listaIndicadoresFacebook.add(indicadorROE);
		listaIndicadoresFacebook.add(indicadorROA);
		listaIndicadoresFacebook.add(indicadorDeudaFacebook);
		listaIndicadoresGoogle.add(indicadorROE);
		listaIndicadoresGoogle.add(indicadorROA);
		listaIndicadoresGoogle.add(indicadorDeudaGoogle);
		listaIndicadoresTwitter.add(indicadorROE);
		listaIndicadoresTwitter.add(indicadorROA);
		listaIndicadoresTwitter.add(indicadorDeudaTwitter);

		facebook = new Empresa("Facebook", listaCuentasFacebook, listaIndicadoresFacebook, LocalDate.now());
		google = new Empresa("Google", listaCuentasGoogle, listaIndicadoresGoogle, LocalDate.now().minusYears(50));
		twitter = new Empresa("Twitter", listaCuentasTwitter, listaIndicadoresTwitter, LocalDate.now().minusYears(20));

		// Condiciones
		unaLongevidad = new Longevidad(10);
		maxIndicador = new MaximizarIndicador(indicadorROE);
		margenCreciente = new MargenCreciente();
		minIndicador = new MinimizarIndicador(indicadorROA);

		repoEmpresas = new RepoEmpresas("repoEmpresas");
		repoEmpresas.agregarEmpresa(facebook);
		repoEmpresas.agregarEmpresa(google);
		repoEmpresas.agregarEmpresa(twitter);
		List<Condicion> condiciones = new ArrayList<>();
		condiciones.add(unaLongevidad);
		condiciones.add(maxIndicador);
		condiciones.add(minIndicador);
		condiciones.add(margenCreciente);
		metodologiaBuffet = new Metodologia(condiciones);
		metodologiaBuffet.aplicarCondiciones(repoEmpresas.getListaEmpresa());

	}

	@FXML
	public void aplicarMetodologia(ActionEvent event) throws IOException {
		if (event.getSource() == btnAplicar) {
			tblLongevidad.setItems(FXCollections
					.observableArrayList(metodologiaBuffet.getResultados().get(unaLongevidad.getNombre())));
			columnaLongevidad
					.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));

			tblMaxROE.setItems(
					FXCollections.observableArrayList(metodologiaBuffet.getResultados().get(maxIndicador.getNombre())));
			columnaMaxROE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));

			tblMinROA.setItems(
					FXCollections.observableArrayList(metodologiaBuffet.getResultados().get(minIndicador.getNombre())));
			columnaMinROA.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));

			tblMargenCreciente.setItems(FXCollections
					.observableArrayList(metodologiaBuffet.getResultados().get(margenCreciente.getNombre())));
			columnaMargenCreciente
					.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
		}
	}

	@FXML
	public void volver(ActionEvent event) throws IOException {
		Parent home_page_parent = null;
		if (event.getSource() == btnVolver) {
			home_page_parent = FXMLLoader.load(getClass().getResource("PantallaPrincipal.fxml"));
		}
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		app_stage.close();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	}
}
