package gui;

import java.net.URL;
import java.util.ResourceBundle;

import dados.BemDAOJavaDb;
import dados.DAOException;
import dados.LoteDAOJavaDb;
import dados.Usuario_PfDAOJavaDb;
import dados.Usuario_PjDAOJavaDb;
import negocio.Bem;
import negocio.Lote;
import negocio.Usuario;
import negocio.Usuario_PF;
import negocio.Usuario_PJ;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class telaNovoLeilaoController implements Initializable {
	Usuario_PjDAOJavaDb Usuario_PjJavaDbDB=Usuario_PjDAOJavaDb.getInstance();
	Usuario_PfDAOJavaDb Usuario_PfJavaDbDB=Usuario_PfDAOJavaDb.getInstance();
	BemDAOJavaDb bemDB=BemDAOJavaDb.getInstance();
	LoteDAOJavaDb loteDB=LoteDAOJavaDb.getInstance();
	
	ObservableList<Bem> listaBens=FXCollections.observableArrayList();
	ObservableList<String> itensChoiseLeilao = FXCollections.observableArrayList("Demanda","Oferta");
	ObservableList<String> itensChoiseLance = FXCollections.observableArrayList("Aberto","Fechado");
	ObservableList<Usuario> itensCbUser = FXCollections.observableArrayList();
	ObservableList<Usuario> itensPJCbUser = FXCollections.observableArrayList();
	ObservableList<Usuario> itensPFCbUser = FXCollections.observableArrayList();
	
	@FXML
    private ListView<Bem> lvBens; //listar bens disponíveis para add ao lote com o clique
    
    @FXML
    private TextArea taLote;
	    
    @FXML
    private ComboBox<Usuario> cbUser;

    @FXML
    private DatePicker dpInicio;

    @FXML
    private TextField tfValor;
    
    @FXML
    private TextField tfInfoLote;

    @FXML
    private DatePicker dpFim;
    
    @FXML
    private Label lCompraVende;

    @FXML
    private ChoiceBox<String> cbLance;

    @FXML
    private ChoiceBox<String> cbLeilao;
    
    @FXML
    private Button bConcluido;
    
    @FXML
    private Button badicionar;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbLance.setItems(itensChoiseLance);
		cbLeilao.setItems(itensChoiseLeilao);
		
		//alimentaçao da lista de usuarios PJ + Pf
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
		
		//monitoramento da ChoiceBox com os tipos de leilão
		cbLeilao.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String valorAntigo, String novoValor) {
				controleCbLeilao(novoValor);
			}
	    });
		
		//alimentaçao da lista de bens disponíveis para add ao lote
		try {
			for(int i=0;i<bemDB.getTodos().size();i++){
				listaBens.add(bemDB.getTodos().get(i));
			}
			lvBens.setItems(listaBens);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//monitoramento da lista de bems
		lvBens.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Bem>() {
			@Override
			public void changed(ObservableValue<? extends Bem> observable, Bem oldValue, Bem newValue) {
				int index=lvBens.getSelectionModel().getSelectedIndex(); //pega o indice do item clicado na view
				try {
					taLote.setText("Bem ID: "+bemDB.getTodos().get(index).getBemId()+"\n Produto: "+bemDB.getTodos().get(index).getDescricao()+"\n"+bemDB.getTodos().get(index).getDetalhes()+"\n"+"Categoria: "+bemDB.getTodos().get(index).getCategoria());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	    });
	}
	
	//altera o o Label com o tipo de leilão de acordo com o campo selecionado na ChoiceBox cbLeilao
	private void controleCbLeilao(String novoValor){
		switch (novoValor) {
        case ("Demanda"):
        	lCompraVende.setText("Comprador");
        break;
        
        case ("Oferta"):
        	lCompraVende.setText("Vendedor");
        break;
              
        default:
        	
		}
	}
	
	public void setTfInfoLote(String text){
		tfInfoLote.setText(text);
	}
	
	
	
    @FXML
    void Adicionar(ActionEvent event) throws DAOException {
    	int index=lvBens.getSelectionModel().getSelectedIndex(); //pega o indice do item clicado na view
		Lote l=new Lote(bemDB.getTodos().get(index).getBemId());
		loteDB.adicionar(l);
		tfInfoLote.setText(bemDB.getBemPorBemID(l.getBemId()).toString()+"");
    }
	
    @FXML
    void Concluido(ActionEvent event) {
    	//lê os campos selecionados e executa a ação
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }
}
