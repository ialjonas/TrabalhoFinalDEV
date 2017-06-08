package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class tela1Controller  implements Initializable{

	@FXML
	private Button btNovo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
			
	}
	 
	@FXML
	void Novo(ActionEvent event) throws IOException {
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("telaNovoLeilao.fxml"));
	                Parent root1 = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root1));  
	                stage.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	        }
		
	}
	    
	

}
