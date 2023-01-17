package it.polito.tdp.nyc;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.nyc.model.Adiacenza;
import it.polito.tdp.nyc.model.Model;
import it.polito.tdp.nyc.model.Zona;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController {

	Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="clPeso"
    private TableColumn<Adiacenza, Integer> clPeso; // Value injected by FXMLLoader

    @FXML // fx:id="clV1"
    private TableColumn<Adiacenza, String> clV1; // Value injected by FXMLLoader

    @FXML // fx:id="clV2"
    private TableColumn<Adiacenza, String> clV2; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBorough"
    private ComboBox<String> cmbBorough; // Value injected by FXMLLoader

    @FXML // fx:id="tblArchi"
    private TableView<Adiacenza> tblArchi; // Value injected by FXMLLoader

    @FXML // fx:id="txtDurata"
    private TextField txtDurata; // Value injected by FXMLLoader

    @FXML // fx:id="txtProb"
    private TextField txtProb; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doAnalisiArchi(ActionEvent event) {
    
    	String b= cmbBorough.getValue();
    	if(b==null) {
    		txtResult.appendText("seleziona un borgo per creare il grafo");
    		return;
    	}
    	
    	List<Adiacenza> result= this.model.getArchiPesoMinore();
    	txtResult.appendText("#Archi trovati= "+result.size()+"\n");
    	txtResult.appendText("peso medio= "+this.model.pesoMedio()+"\n");
    	tblArchi.setItems(FXCollections.observableArrayList(result));
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	String b= cmbBorough.getValue();
    	if(b==null) {
    		txtResult.appendText("seleziona un borgo per creare il grafo");
    		return;
    	}
    	String result=this.model.creaGrafo(b);
    	txtResult.appendText(result+"\n");
    	
    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clPeso != null : "fx:id=\"clPeso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clV1 != null : "fx:id=\"clV1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clV2 != null : "fx:id=\"clV2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBorough != null : "fx:id=\"cmbBorough\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tblArchi != null : "fx:id=\"tblArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDurata != null : "fx:id=\"txtDurata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtProb != null : "fx:id=\"txtProb\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

        clV1.setCellValueFactory(new PropertyValueFactory<Adiacenza, String>("z1Name"));
        clV2.setCellValueFactory(new PropertyValueFactory<Adiacenza, String>("z2Name"));
        clPeso.setCellValueFactory(new PropertyValueFactory<Adiacenza, Integer>("peso"));
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbBorough.getItems().addAll(this.model.getBorghi());
    }

}
