package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class telaNovoBemController implements Initializable {
	ObservableList<String> itensChoiseCategoria = FXCollections.observableArrayList(
			"Informatica","Mobiliário","Veículos","nova"
	);

    @FXML
    private TextField tfDescrBreve;
    
    @FXML
    private ChoiceBox<String> cbCategoria;

    @FXML
    private TextArea taDetalhes;
    
    @FXML
    private Button bConcluido;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbCategoria.setItems(itensChoiseCategoria);
		
	}
	
	@FXML
	void Concluido(ActionEvent event) {
		//lê os campos selecionados e executa a ação
		 
		Stage stage = (Stage) bConcluido.getScene().getWindow();
	    stage.close();
	}

}
