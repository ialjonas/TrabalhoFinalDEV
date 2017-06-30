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

	@FXML
    private ListView<Bem> lvBens; //listar bens disponíveis para add ao lote com o clique
    
    @FXML
    private TextArea taLote;

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
				.getBemId(),
				Double.parseDouble(tfValor.getText())
				);
		
		loteDB.adicionar(l);
		tfInfoLote.setText(bemDB.getBemPorBemID(l.getBemId()).toString()+"");
		//loteIdTemp=bemDB.getBemPorBemID(l.getBemId());
    }
	
    @FXML
    void Concluido(ActionEvent event) {
    	//lê os campos selecionados e executa a ação
    	
    	Leilao le=new Leilao(
    			1,
    			dpInicio.getValue().toString()+" 20:40:00",
    			dpFim.getValue().toString()+" 20:40:00",
    			Double.parseDouble(tfValor.getText()),
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
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
			e.printStackTrace();	
		}
    	
    	catch (NullPointerException n) {
    		// TODO Auto-generated catch block
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Atenção!");
    		alert.setHeaderText(null);
    		alert.setContentText(n.toString());
    		alert.showAndWait();
    		n.printStackTrace();	
    	}
    	
    	catch (RuntimeException r) {
    		// TODO Auto-generated catch block
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Atenção!");
    		alert.setHeaderText(null);
    		alert.setContentText(r.toString());
    		alert.showAndWait();
    		r.printStackTrace();	
    	}
    	
    	Stage stage = (Stage) bConcluido.getScene().getWindow();
        stage.close();
    }
}
