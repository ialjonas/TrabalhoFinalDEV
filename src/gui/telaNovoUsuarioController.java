package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.font.freetype.FTFactory;

import dados.DAOException;
import dados.Usuario_PfJavaDb;
import dados.Usuario_PjJavaDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import negocio.Usuario_PF;
import negocio.Usuario_PJ;

public class telaNovoUsuarioController implements Initializable{
	Usuario_PjJavaDb Usuario_PjJavaDbDB=Usuario_PjJavaDb.getInstance();
	Usuario_PfJavaDb Usuario_PfJavaDbDB=Usuario_PfJavaDb.getInstance();
	
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
    	if(cbTipo.getSelectionModel().getSelectedIndex()==0){ //selecionado Empresa
			lDado.setText("CNPJ");
			lNome.setText("Raz�o Social");
			Mascaras.cnpjField(this.tfDado);
			lDado.setDisable(false);
			lNome.setDisable(false);
			lEmail.setDisable(false);
			tfNome.setEditable(true);
			tfEmail.setEditable(true);
			tfDado.setEditable(true);
		}
    	if(cbTipo.getSelectionModel().getSelectedIndex()==1){ //selecionado Pessoa Fisica
    		lDado.setText("CPF");
    		lNome.setText("Nome Completo");
    		Mascaras.cpfField(tfDado);
    		lDado.setDisable(false);
			lNome.setDisable(false);
			lEmail.setDisable(false);
			tfNome.setEditable(true);
			tfEmail.setEditable(true);
			tfDado.setEditable(true);
    	}
    }
	
    @FXML
    void Concluido(ActionEvent event) throws DAOException {
    	if(cbTipo.getSelectionModel().getSelectedIndex()==0){//selecionado Empresa
    		String CNPJ = tfDado.getText();
    		String nome = tfNome.getText();
    		String email = tfEmail.getText();
    		Usuario_PJ pj = new Usuario_PJ(CNPJ,nome,email);
    		Usuario_PjJavaDbDB.adicionar(pj);
    	}
    	if(cbTipo.getSelectionModel().getSelectedIndex()==1){//selecionado Pessoa Fisica
    		String CPF = tfDado.getText();
    		String nome = tfNome.getText();
    		String email = tfEmail.getText();
    		Usuario_PF pf = new Usuario_PF(CPF,nome,email);
    		Usuario_PfJavaDbDB.adicionar(pf);
    		/*
    		try {	
    			Usuario_PfJavaDbDB.adicionar(pf);
    			Stage stage = (Stage) bConcluido.getScene().getWindow();
    	        stage.close();
    	        } catch(Exception e) {
    	        	Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Aten��o!");
					alert.setHeaderText(null);
					alert.setContentText("Usu�rio n�o inserido");
					alert.showAndWait();
    	        }
    	        */
    	}
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }

}








