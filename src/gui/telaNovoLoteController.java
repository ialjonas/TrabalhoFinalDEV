package gui;

import java.net.URL;
import java.util.ResourceBundle;

import dados.BemJavaDb;
import dados.DAOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import negocio.Bem;

public class telaNovoLoteController implements Initializable {
	BemJavaDb bemDB=BemJavaDb.getInstance();
	ObservableList<Bem> listaBens=FXCollections.observableArrayList();

    @FXML
    private ListView<?> lvLote;  //listar bens já pertencentes ao lote, remover com o clique
    
    @FXML
    private ListView<Bem> lvBens; //listar bens disponíveis para add ao lote com o clique

    @FXML
    private Button bConcluido;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			for(int i=0;i<bemDB.getTodos().size();i++){
				listaBens.add(bemDB.getTodos().get(i));
			}
			lvBens.setItems(listaBens);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//listaBens.setItems(lvBens);
		
	}
	
	
	@FXML
    void Concluido(ActionEvent event) {
    	//lê os campos selecionados e executa a ação
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }

}
