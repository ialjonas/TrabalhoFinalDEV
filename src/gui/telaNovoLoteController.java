package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class telaNovoLoteController implements Initializable {

    @FXML
    private ListView<?> lvLote;  //listar bens já pertencentes ao lote, remover com o clique
    
    @FXML
    private ListView<?> lvBens; //listar bens disponíveis para add ao lote com o clique

    @FXML
    private Button bConcluido;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	@FXML
    void Concluido(ActionEvent event) {
    	//lê os campos selecionados e executa a ação
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }

}
