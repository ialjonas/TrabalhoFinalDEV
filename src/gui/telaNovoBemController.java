package gui;

import java.net.URL;
import java.util.ResourceBundle;

import dados.BemDAOJavaDb;
import dados.DAOException;
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
import negocio.Bem;

public class telaNovoBemController implements Initializable {
	BemDAOJavaDb bemDB=BemDAOJavaDb.getInstance();
	ObservableList<String> itensChoiseCategoria = FXCollections.observableArrayList(
			"Informatica","Mobiliário","Veículos","nova"
	);

    @FXML
    private TextField tfDescricao;
    
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
	void Concluido(ActionEvent event) throws DAOException {
		String descricao = tfDescricao.getText();
		String detalhes = taDetalhes.getText();
		String categoria = cbCategoria.getValue();
		Bem b=new Bem(descricao,detalhes,categoria);
		bemDB.adicionar(b);
		Stage stage = (Stage) bConcluido.getScene().getWindow();
	    stage.close();
	}

}
