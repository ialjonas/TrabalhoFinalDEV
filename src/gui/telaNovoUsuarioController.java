package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.font.freetype.FTFactory;

import dados.DAOException;
import dados.Usuario_PfJavaDb;
import dados.Usuario_PjJavaDb;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	ObservableList<String> itensChoiseTipo = FXCollections.observableArrayList("Pessoa Juridica","Pessoa Física");
	
    @FXML
    private Button bConcluido;

    @FXML
    private Label lDado; //muda o valor de acordo com o tipo de usuário
    
    @FXML
    private Label lNome; //muda o valor de acordo com o tipo de usuário
   
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
		cbTipo.setTooltip(new Tooltip("Pessoa Física ou Juridica"));
		
		cbTipo.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String valorAntigo, String novaLabel) {
	        	refresh(novaLabel);
			}
	    });
	}
	
	private void refresh(String novaLabel){
		switch (novaLabel) {
        case ("Pessoa Juridica"):
        	lNome.setText("Razão Social");
        	lDado.setText("CNPJ");
        	Mascaras.cnpjField(this.tfDado);
        	lDado.setDisable(false);
			lNome.setDisable(false);
			lEmail.setDisable(false);
			tfNome.setEditable(true);
			tfEmail.setEditable(true);
			tfDado.setEditable(true);
            break;
        case ("Pessoa Física"):
        	lNome.setText("Nome");
        	lDado.setText("CPF");
        	Mascaras.cpfField(this.tfDado);
        	lDado.setDisable(false);
			lNome.setDisable(false);
			lEmail.setDisable(false);
			tfNome.setEditable(true);
			tfEmail.setEditable(true);
			tfDado.setEditable(true);
            break;
        
        default:
        	lNome.setText("Nome Completo / Razao");
        	lDado.setText("CPF / CNPJ");
        	lDado.setDisable(true);
			lNome.setDisable(true);
			lEmail.setDisable(true);
			tfNome.setEditable(false);
			tfEmail.setEditable(false);
			tfDado.setEditable(false);
		}
	}
	
	
    @FXML
    void Adicionar(ActionEvent event) throws DAOException {
    	if(cbTipo.getSelectionModel().getSelectedIndex()==0){//selecionado Empresa
    		String CNPJ = tfDado.getText();
    		String nome = tfNome.getText();
    		String email = tfEmail.getText();
    		Usuario_PJ pj = new Usuario_PJ(CNPJ,nome,email);
    		try {	
    			Usuario_PjJavaDbDB.adicionar(pj);
    			cbTipo.setSelectionModel(null);
    			lDado.setText("CPF / CNPJ");
    			lNome.setText("Nome Completo / Razao Social");
    			lDado.setDisable(true);
    			lNome.setDisable(true);
    			lEmail.setDisable(true);
    			tfNome.setEditable(false);
    			tfEmail.setEditable(false);
    			tfDado.setEditable(false);
    			Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucesso!");
				alert.setHeaderText(null);
				alert.setContentText("Usuário Cadastrado corretamente");
				alert.showAndWait();
				
    	        } catch(Exception e) {
    	        	Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Atenção!");
					alert.setHeaderText(null);
					alert.setContentText("Usuário não inserido");
					alert.showAndWait();
    	        }
    	}
    	if(cbTipo.getSelectionModel().getSelectedIndex()==1){//selecionado Pessoa Fisica
    		String CPF = tfDado.getText();
    		String nome = tfNome.getText();
    		String email = tfEmail.getText();
    		Usuario_PF pf = new Usuario_PF(CPF,nome,email);
    		try {	
    			Usuario_PfJavaDbDB.adicionar(pf);
    			cbTipo.setSelectionModel(null);
    			lDado.setText("CPF / CNPJ");
    			lNome.setText("Nome Completo / Razao Social");
    			lDado.setDisable(true);
    			lNome.setDisable(true);
    			lEmail.setDisable(true);
    			tfNome.setEditable(false);
    			tfEmail.setEditable(false);
    			tfDado.setEditable(false);
    			Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucesso!");
				alert.setHeaderText(null);
				alert.setContentText("Usuário Cadastrado corretamente");
				alert.showAndWait();
 
    	        } catch(Exception e) {
    	        	Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Atenção!");
					alert.setHeaderText(null);
					alert.setContentText("Usuário não inserido");
					alert.showAndWait();
    	        }
    	}
    	
    }
	
    @FXML
    void Concluido(ActionEvent event) throws DAOException {
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }

}








