package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class telaNovoLeilaoController implements Initializable {
	ObservableList<String> itensChoiseLeilao = FXCollections.observableArrayList("Demanda","Oferta");
	ObservableList<String> itensChoiseLance = FXCollections.observableArrayList("Aberto","Fechado");
	    
    @FXML
    private ComboBox<?> cbUser;

    @FXML
    private DatePicker dpInicio;

    @FXML
    private TextField tfValor;

    @FXML
    private DatePicker dpFim;

    @FXML
    private ChoiceBox<String> cbLance;

    @FXML
    private ChoiceBox<String> cbLeilao;
    
    @FXML
    private Button bAddItens;
    
    @FXML
    private Button bConcluido;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbLance.setItems(itensChoiseLance);
		cbLeilao.setItems(itensChoiseLeilao);
		
	}
	
	@FXML
    void NovoLote(ActionEvent event) {
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("telaNovoLote.fxml"));
	                Parent parentNovoLote = (Parent) fxmlLoader.load();
	                Stage stageNovoLote = new Stage();
	                stageNovoLote.setScene(new Scene(parentNovoLote));
	                stageNovoLote.setTitle("Novo Lote");
	                stageNovoLote.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	        }
    }
	
    @FXML
    void Concluido(ActionEvent event) {
    	//lê os campos selecionados e executa a ação
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }
    


}
