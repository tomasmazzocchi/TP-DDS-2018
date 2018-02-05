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
import ar.edu.utn.dds.grupo5.ResultadoMetodologia;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionCuenta;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionIndicador;
import ar.edu.utn.dds.grupo5.Condiciones.CondicionNumero;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.stage.Stage;

public class MetodologiaExistenteController implements Initializable {

	@FXML
	private Button btnVolver;
	@FXML
	private Button btnAplicar;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private TableView<ResultadoMetodologia> tblEMpresa;
	@FXML
	private TableView<ResultadoMetodologia> tblPuntaje;
	@FXML
	private TableView<Empresa> tblMinROA;
	@FXML
	private TableView<Empresa> tblMargenCreciente;
	@FXML
	private TableColumn<ResultadoMetodologia, String> columnaEmpresa = new TableColumn<ResultadoMetodologia, String>();
	@FXML
	private TableColumn<ResultadoMetodologia, Number> columnaPuntaje = new TableColumn<ResultadoMetodologia, Number>();

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
	private Indicador indicadorROE;
	private Indicador indicadorROA;
	private Indicador indicadorDeudaFacebook;
	private Indicador indicadorDeudaGoogle;
	private Indicador indicadorDeudaTwitter;
	private CondicionNumero longevidad;
	private CondicionIndicador maximizarROE;
	private CondicionIndicador minimizarROA;
	private CondicionCuenta margenCreciente;
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
		longevidad = new CondicionNumero("longevidad",10,10,'<');
		maximizarROE = new CondicionIndicador("Maximizar ROE", indicadorROE, 8, '>');
		minimizarROA = new CondicionIndicador("Minimizar ROA", indicadorROA, 2, '<');
		margenCreciente = new CondicionCuenta("Margen Creciente", cuentaMARGENGooge, 6, '>');

		repoEmpresas = new RepoEmpresas("repoEmpresas");
		repoEmpresas.agregarEmpresa(facebook);
		repoEmpresas.agregarEmpresa(google);
		repoEmpresas.agregarEmpresa(twitter);
		List<Condicion> condiciones = new ArrayList<>();
		condiciones.add(longevidad);
		condiciones.add(maximizarROE);
		condiciones.add(minimizarROA);
		condiciones.add(margenCreciente);
		metodologiaBuffet = new Metodologia("Metodologia Buffet", condiciones);
		metodologiaBuffet.aplicarCondiciones(repoEmpresas.getListaEmpresa());

	}

	@FXML
	public void aplicarMetodologia(ActionEvent event) throws IOException {
		if (event.getSource() == btnAplicar) {
			tblEMpresa.setItems(FXCollections
					.observableArrayList(metodologiaBuffet.getListaResultado()));
			columnaEmpresa
					.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmpresa().getNombre()));

			tblPuntaje.setItems(
					FXCollections.observableArrayList(metodologiaBuffet.getListaResultado()));
			columnaPuntaje.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPuntuacion()));
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
