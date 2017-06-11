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
    private ListView<?> lvLote;  //listar bens j� pertencentes ao lote, remover com o clique
    
    @FXML
    private ListView<?> lvBens; //listar bens dispon�veis para add ao lote com o clique

    @FXML
    private Button bConcluido;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	@FXML
    void Concluido(ActionEvent event) {
    	//l� os campos selecionados e executa a a��o
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }

}
