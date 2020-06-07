package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//controller turno B --> switchare al branch master_turnoA o master_turnoC per turno A o C

public class FXMLController {
	
	private Model model;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> boxSquadra;

    @FXML
    private Button btnCalcolaConnessioniSquadra;

    @FXML
    private Button btnSimulaEspulsi;

    @FXML
    private Button btnAnalizzaStagioni;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalizzaStagioni(ActionEvent event) {

    }

    @FXML
    void doCalcolaConnessioniStagione(ActionEvent event) {

    }

    @FXML
    void doSimulaEspulsi(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniSquadra != null : "fx:id=\"btnCalcolaConnessioniSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaEspulsi != null : "fx:id=\"btnSimulaEspulsi\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaStagioni != null : "fx:id=\"btnAnalizzaStagioni\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}