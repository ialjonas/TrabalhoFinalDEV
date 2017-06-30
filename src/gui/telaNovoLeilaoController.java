package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dados.BemDAOJavaDb;
import dados.DAOException;
import dados.LeilaoDAOJavaDb;
import dados.LoteDAOJavaDb;
import dados.Usuario_PfDAOJavaDb;
import dados.Usuario_PjDAOJavaDb;
import negocio.Bem;
import negocio.Leilao;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class telaNovoLeilaoController implements Initializable {
	private int loteIdTemp=0;
	Usuario_PjDAOJavaDb Usuario_PjJavaDbDB=Usuario_PjDAOJavaDb.getInstance();
	Usuario_PfDAOJavaDb Usuario_PfJavaDbDB=Usuario_PfDAOJavaDb.getInstance();
	BemDAOJavaDb bemDB=BemDAOJavaDb.getInstance();
	LoteDAOJavaDb loteDB=LoteDAOJavaDb.getInstance();
	LeilaoDAOJavaDb leilaoDB=LeilaoDAOJavaDb.getInstance();
	
	ObservableList<Bem> listaBens=FXCollections.observableArrayList();
	ObservableList<String> itensChoiseLeilao = FXCollections.observableArrayList("Demanda","Oferta");
	ObservableList<String> itensChoiseLance = FXCollections.observableArrayList("Aberto","Fechado");
	ObservableList<Usuario> itensCbUser = FXCollections.observableArrayList();
	ObservableList<Usuario> itensPJCbUser = FXCollections.observableArrayList();
	ObservableList<Usuario> itensPFCbUser = FXCollections.observableArrayList();
	
	@FXML
    private ListView<Bem> lvBens; //listar bens dispon�veis para add ao lote com o clique
    
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
		
		//alimenta�ao da lista de usuarios PJ + Pf
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
		
		//monitoramento da ChoiceBox com os tipos de leil�o
		cbLeilao.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String valorAntigo, String novoValor) {
				controleCbLeilao(novoValor);
			}
	    });
		
		//alimenta�ao da lista de bens dispon�veis para add ao lote
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
	
	//altera o o Label com o tipo de leil�o de acordo com o campo selecionado na ChoiceBox cbLeilao
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
	
	
	//adiciona o bem selecionado a um novo lote
    @FXML
    void Adicionar(ActionEvent event) throws DAOException {
		Lote l=new Lote(
				bemDB.getTodos().get(
						lvBens.getSelectionModel().getSelectedIndex() //pega o indice do item clicado na view
						)
				.getBemId());
		
		loteDB.adicionar(l);
		//loteIdTemp=loteDB.getTodos().get
		tfInfoLote.setText(bemDB.getBemPorBemID(l.getBemId()).toString()+"");
    }
	
    @FXML
    void Concluido(ActionEvent event) {
    	//l� os campos selecionados e executa a a��o
    	
    	Leilao le=new Leilao(
    			loteIdTemp,
    			dpInicio.getValue().toString()+" 20:40:00",
    			dpFim.getValue().toString()+" 20:40:00",
    			Double.parseDouble(tfValor.getText()),
    			cbUser.getSelectionModel().getSelectedItem().getNome(),
    			"",
    			cbLeilao.getSelectionModel().getSelectedItem().toString(),
    			cbLance.getSelectionModel().getSelectedItem().toString()
    			);
    	try {
    		System.out.println(le.toString());
			leilaoDB.adicionar(le);
		} 
    	catch (DAOException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aten��o!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
			e.printStackTrace();	
		}
    	
    	catch (NullPointerException n) {
    		// TODO Auto-generated catch block
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Aten��o!");
    		alert.setHeaderText(null);
    		alert.setContentText(n.toString());
    		alert.showAndWait();
    		n.printStackTrace();	
    	}
    	
    	catch (RuntimeException r) {
    		// TODO Auto-generated catch block
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Aten��o!");
    		alert.setHeaderText(null);
    		alert.setContentText(r.toString());
    		alert.showAndWait();
    		r.printStackTrace();	
    	}
    	
    	
    	
    
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }
}
