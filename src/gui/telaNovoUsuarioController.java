package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class telaNovoUsuarioController implements Initializable{
	ObservableList<String> itensChoiseTipo = FXCollections.observableArrayList("Pessoa Juridica","Pessoa F�sica");
	
    @FXML
    private Button bConcluido;

    @FXML
    private Label lDado; //muda o valor de acordo com o tipo de usu�rio
    
    @FXML
    private Label lNome; //muda o valor de acordo com o tipo de usu�rio
   
    @FXML
    private Label lEmail;

    @FXML
    private TextField tfNome;
    
    @FXML
    private TextField tfEmail;
    
    @FXML
    private TextField tfDado; 

    @FXML
    private ChoiceBox<String> cbTipo;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbTipo.setItems(itensChoiseTipo);
		cbTipo.setTooltip(new Tooltip("Pessoa F�sica ou Juridica"));
	}
	
	
    @FXML
    void Refresh(MouseEvent event) {
    	if(cbTipo.getSelectionModel().getSelectedIndex()==0){
			lDado.setText("CNPJ");
			lNome.setText("Raz�o Social");
			lDado.setDisable(false);
			lNome.setDisable(false);
			lEmail.setDisable(false);
			tfNome.setEditable(true);
			tfEmail.setEditable(true);
			tfDado.setEditable(true);
		}
    	if(cbTipo.getSelectionModel().getSelectedIndex()==1){
    		lDado.setText("CPF");
    		lNome.setText("Nome Completo");
    		lDado.setDisable(false);
			lNome.setDisable(false);
			lEmail.setDisable(false);
			tfNome.setEditable(true);
			tfEmail.setEditable(true);
			tfDado.setEditable(true);
    	}
    }
	
    
    @FXML
    void Concluido(ActionEvent event) {
    	//l� os campos selecionados e executa a a��o
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }

}