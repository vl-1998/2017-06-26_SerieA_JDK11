package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.*;
import it.polito.tdp.seriea.model.Season;
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
    private ChoiceBox<Season> boxSquadra;

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
    	txtResult.clear();
    	this.model.creaGrafo();
    	txtResult.appendText("Grafo creato!\n #Vertici= "+this.model.getVertex().size()+"#Archi= "+this.model.getEdge().size());
    	boxSquadra.getItems().clear();
    	boxSquadra.getItems().addAll(this.model.getVertex());
    }

    @FXML
    void doCalcolaConnessioniStagione(ActionEvent event) {
    	txtResult.clear();
    	Season s = boxSquadra.getValue();
    	if (s==null) {
    		txtResult.appendText("Inserire una stagione!");
    	}
    	
    	for(StagioneAdiacente se : this.model.squadreComuni(s)) {
    		txtResult.appendText(se.toString()+"\n");
    	}

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