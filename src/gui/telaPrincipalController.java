package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class telaPrincipalController implements Initializable{
	
	@FXML
	private MenuItem miUsuario;

	@FXML
	private MenuItem miLeilao;

	@FXML
	private MenuItem miBem;

	@FXML
	private MenuItem miFechar;

	@FXML
	private MenuItem miSobre;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
			
	}
	 
	@FXML
	void NovoLeilao(ActionEvent event) throws IOException {
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("telaNovoLeilao.fxml"));
	                Parent parentNovoLeilao = (Parent) fxmlLoader.load();
	                Stage stageNovoLeilao = new Stage();
	                stageNovoLeilao.setScene(new Scene(parentNovoLeilao));
	                stageNovoLeilao.setTitle("Novo Leilão");
	                stageNovoLeilao.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	        }
	}
	
	@FXML
    void NovoBem(ActionEvent event) {
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("telaNovoBem.fxml"));
	                Parent parentNovoBem = (Parent) fxmlLoader.load();
	                Stage stageNovoBem = new Stage();
	                stageNovoBem.setScene(new Scene(parentNovoBem));
	                stageNovoBem.setTitle("Novo Bem");
	                stageNovoBem.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	        }
    }

    @FXML
    void NovoUsuario(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("telaNovoUsuario.fxml"));
	                Parent parentNovoUsuario = (Parent) fxmlLoader.load();
	                Stage stageNovoUsuario = new Stage();
	                stageNovoUsuario.setScene(new Scene(parentNovoUsuario));
	                stageNovoUsuario.setTitle("Novo Usuário");
	                stageNovoUsuario.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	        }
    }
	
    
    @FXML
    void Sobre(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sobre");
		alert.setHeaderText("TF - Dund. Dev. Sw. \nSistema Controle de Leilões");
		alert.setContentText("Desenvolvido por: \n Guilherme Dohms\n Ial Jonas\n Lariel Negreiros \n \nProfessor: \n Michael Mora");
		alert.showAndWait();
    }

	
	
	@FXML
    void Fechar(ActionEvent event) {
    	Platform.exit();
    }
	    
}
