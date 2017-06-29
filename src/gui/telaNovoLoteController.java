package gui;

import java.net.URL;
import java.util.ResourceBundle;

import dados.BemDAOJavaDb;
import dados.DAOException;
import dados.LoteDAOJavaDb;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import negocio.Bem;
import negocio.Lote;

public class telaNovoLoteController implements Initializable {
	BemDAOJavaDb bemDB=BemDAOJavaDb.getInstance();
	LoteDAOJavaDb loteDB=LoteDAOJavaDb.getInstance();
	ObservableList<Bem> listaBens=FXCollections.observableArrayList();
    
    @FXML
    private ListView<Bem> lvBens; //listar bens disponíveis para add ao lote com o clique
    
    @FXML
    private TextArea taLote;

    @FXML
    private Button bConcluido;
    
    @FXML
    private Button badicionar;


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
	}
	
	@FXML
    void Adicionar(ActionEvent event) throws DAOException {
		int index=lvBens.getSelectionModel().getSelectedIndex(); //pega o indice do item clicado na view
		taLote.setText("Bem ID: "+bemDB.getTodos().get(index).getBemId()+"\n Produto: "+bemDB.getTodos().get(index).getDescricao()+"\n"+bemDB.getTodos().get(index).getDetalhes()+"\n"+"Categoria: "+bemDB.getTodos().get(index).getCategoria());
		Lote l=new Lote(bemDB.getTodos().get(index).getBemId());
		loteDB.adicionar(l);
	}
    
	@FXML
    void Concluido(ActionEvent event) {
    	//lê os campos selecionados e executa a ação
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }

}
