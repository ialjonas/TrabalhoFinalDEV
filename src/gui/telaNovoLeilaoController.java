package gui;

import java.net.URL;
import java.util.ResourceBundle;

import dados.BemJavaDb;
import dados.DAOException;
import dados.Usuario_PfJavaDb;
import dados.Usuario_PjJavaDb;
import negocio.Usuario;
import negocio.Usuario_PF;
import negocio.Usuario_PJ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class telaNovoLeilaoController implements Initializable {
	Usuario_PjJavaDb Usuario_PjJavaDbDB=Usuario_PjJavaDb.getInstance();
	Usuario_PfJavaDb Usuario_PfJavaDbDB=Usuario_PfJavaDb.getInstance();
	
	ObservableList<String> itensChoiseLeilao = FXCollections.observableArrayList("Demanda","Oferta");
	ObservableList<String> itensChoiseLance = FXCollections.observableArrayList("Aberto","Fechado");
	ObservableList<Usuario> itensCbUser = FXCollections.observableArrayList();
	ObservableList<Usuario> itensPJCbUser = FXCollections.observableArrayList();
	ObservableList<Usuario> itensPFCbUser = FXCollections.observableArrayList();
	    
    @FXML
    private ComboBox<Usuario> cbUser;

    @FXML
    private DatePicker dpInicio;

    @FXML
    private TextField tfValor;

    @FXML
    private DatePicker dpFim;

    @FXML
    private ChoiceBox<String> cbLance;

    @FXML
    private ChoiceBox<String> cbLeilao;
    
    @FXML
    private Button bAddItens;
    
    @FXML
    private Button bConcluido;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbLance.setItems(itensChoiseLance);
		cbLeilao.setItems(itensChoiseLeilao);
		try {
			for(int i=0;i<Usuario_PjJavaDbDB.getTodos().size();i++){
				itensCbUser.add(Usuario_PjJavaDbDB.getTodos().get(i));
			}
			for(int i=0;i<Usuario_PfJavaDbDB.getTodos().size();i++){
				itensCbUser.add(Usuario_PfJavaDbDB.getTodos().get(i));
			}
			
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbUser.setItems(itensCbUser);
		
	}
	
	@FXML
    void NovoLote(ActionEvent event) {
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("telaNovoLote.fxml"));
	                Parent parentNovoLote = (Parent) fxmlLoader.load();
	                Stage stageNovoLote = new Stage();
	                stageNovoLote.setScene(new Scene(parentNovoLote));
	                stageNovoLote.setTitle("Novo Lote");
	                stageNovoLote.show();
	        } catch(Exception e) {
	           e.printStackTrace();
	        }
    }
	
    @FXML
    void Concluido(ActionEvent event) {
    	//lê os campos selecionados e executa a ação
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }
    


}
