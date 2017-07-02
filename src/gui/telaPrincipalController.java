package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dados.BemDAOJavaDb;
import dados.DAOException;
import dados.LeilaoDAOJavaDb;
import dados.LoteDAOJavaDb;
import dados.Usuario_PfDAOJavaDb;
import dados.Usuario_PjDAOJavaDb;
import javafx.application.Platform;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import negocio.Bem;
import negocio.Lance;
import negocio.Leilao;
import negocio.Lote;
import negocio.Usuario;

public class telaPrincipalController implements Initializable{
	Leilao l_temp;
	Usuario_PjDAOJavaDb Usuario_PjDB=Usuario_PjDAOJavaDb.getInstance();
	Usuario_PfDAOJavaDb Usuario_PfDB=Usuario_PfDAOJavaDb.getInstance();
	LeilaoDAOJavaDb leilaoDB=LeilaoDAOJavaDb.getInstance();
	LoteDAOJavaDb loteDB=LoteDAOJavaDb.getInstance();
	BemDAOJavaDb bemDB=BemDAOJavaDb.getInstance();
	
	ObservableList<String> itensChoisestatus = FXCollections.observableArrayList(
			"Selecione um status para exibir","Todos","Em Andamento","Encerrados","Leilão de oferta","Leilão de demanda","Lance aberto","Lance fechado"
	);
	ObservableList<String> itensChoiseusuarios = FXCollections.observableArrayList(
			"Selecione um tipo de usuário","Pessoa Física","Pessoa Juridica"
	);
	
	ObservableList<Leilao> listaLeiloes = FXCollections.observableArrayList();
	ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
	
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
	
    @FXML
    private ChoiceBox<String> cbStatus;
    
    @FXML
    private ChoiceBox<String> cbUsuarios;
	
	@FXML
    private ListView<Leilao> lvLeiloes;
	
    @FXML
    private ListView<Usuario> lvUsuarios;
    
    @FXML
    private Button bDetalheLeilao;
    
    @FXML
    private Button bselecionaUsuario;
    
    @FXML
    private Button bDarLance;
    
    @FXML
    private TextField tfLeilaoId;
    
    @FXML
    private TextField tfDataIni;
    
    @FXML
    private TextField tfDataFim;
    
    @FXML
    private TextField tfTipoLeilao;
    
    @FXML
    private TextField tfTipoLance;
    
    @FXML
    private TextField tfUsuarioSelecionado;
    
    @FXML
    private TextField tfLance;
    
    @FXML
    private TextArea taLote;

    @FXML
    private TextField tfNomeVencedor;

    @FXML
    private TextField tfValorLanceVencedor;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tfLeilaoId.setEditable(false);
		tfDataIni.setEditable(false);
		tfDataFim.setEditable(false);
		tfTipoLeilao.setEditable(false);
		tfTipoLance.setEditable(false);
		taLote.setEditable(false);
		tfNomeVencedor.setEditable(false);
		tfValorLanceVencedor.setEditable(false);
		tfUsuarioSelecionado.setEditable(false);
		tfLance.setEditable(false);
		
		cbStatus.setItems(itensChoisestatus);
		cbStatus.getSelectionModel().select(0);
		cbStatus.setTooltip(new Tooltip("Selecione o status do leilão"));
		
		cbUsuarios.setItems(itensChoiseusuarios);
		cbUsuarios.getSelectionModel().select(0);
		cbUsuarios.setTooltip(new Tooltip("Selecione um tipo de usuário"));
		
		
		//monitoramento da ChoiceBox de stauts dos leilões
		cbStatus.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String valorAntigoCbStatus, String novoValorCbStatus) {
				controleCbStatus(novoValorCbStatus);
			}
	    });
		
		//monitoramento da ChoiceBox de usuários
		cbUsuarios.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValuecbUsuarios, String newValuecbUsuarios) {
				controleCbUsuarios(newValuecbUsuarios);
				
			}
		});
	}
	
	//alimentaçao da lista de leiloes com base na ChoiceBox de status dos leilões
	private void controleCbStatus(String novoValorCbStatus){
		switch (novoValorCbStatus) {
		
		case ("Selecione um status para exibir"):
        	listaLeiloes.clear();
			tfLeilaoId.setText(null);
			tfDataIni.setText(null);
			tfDataFim.setText(null);
			tfTipoLeilao.setText(null);
			tfTipoLance.setText(null);
			taLote.setText(null);
			tfNomeVencedor.setText(null);
			tfValorLanceVencedor.setText(null);
            break;
		
        case ("Encerrados"):
        	listaLeiloes.clear();
        	try {
    			for(int i=0;i<leilaoDB.getEncerrados().size();i++){
    				listaLeiloes.add(leilaoDB.getEncerrados().get(i));
    			}
    			lvLeiloes.setItems(listaLeiloes);
    			
    		} catch (DAOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            break;
        
        case ("Em Andamento"):
        	listaLeiloes.clear();
        	try {
    			for(int i=0;i<leilaoDB.getAtivos().size();i++){
    				listaLeiloes.add(leilaoDB.getAtivos().get(i));
    			}
    			lvLeiloes.setItems(listaLeiloes);
    			
    		} catch (DAOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	break;
        
        case ("Todos"):
        	listaLeiloes.clear();
        	try {
    			for(int i=0;i<leilaoDB.getTodos().size();i++){
    				listaLeiloes.add(leilaoDB.getTodos().get(i));
    			}
    			lvLeiloes.setItems(listaLeiloes);
    			
    			
    		} catch (DAOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	break;
        	
        case ("Leilão de oferta"):
        	listaLeiloes.clear();
        	try {
    			for(int i=0;i<leilaoDB.getTipoOferta().size();i++){
    				listaLeiloes.add(leilaoDB.getTipoOferta().get(i));
    			}
    			lvLeiloes.setItems(listaLeiloes);
    			
    		} catch (DAOException e) {
    			e.printStackTrace();
    		}
        	break;
        	
        case ("Leilão de demanda"):
        	listaLeiloes.clear();
        	try {
    			for(int i=0;i<leilaoDB.getTipoDemanda().size();i++){
    				listaLeiloes.add(leilaoDB.getTipoDemanda().get(i));
    			}
    			lvLeiloes.setItems(listaLeiloes);
    			
    		} catch (DAOException e) {
    			e.printStackTrace();
    		}
        	break;
        
        case ("Lance aberto"):
        	listaLeiloes.clear();
        	try {
    			for(int i=0;i<leilaoDB.getLanceAberto().size();i++){
    				listaLeiloes.add(leilaoDB.getLanceAberto().get(i));
    			}
    			lvLeiloes.setItems(listaLeiloes);
    			
    		} catch (DAOException e) {
    			e.printStackTrace();
    		}
        	break;
        	
        case ("Lance fechado"):
        	listaLeiloes.clear();
        	try {
    			for(int i=0;i<leilaoDB.getLanceFechado().size();i++){
    				listaLeiloes.add(leilaoDB.getLanceFechado().get(i));
    			}
    			lvLeiloes.setItems(listaLeiloes);
    			
    		} catch (DAOException e) {
    			e.printStackTrace();
    		}
        	break;
        default:
        	
		}
	}
	
	
    //açao do botao de detalhes do leilão
    @FXML
    void DetalheLeilao(ActionEvent event){    	
       	if(cbStatus.getSelectionModel().getSelectedItem().equals("Selecione um status para exibir")){
       		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um status para exibir os leilões disponíveis");
			alert.showAndWait();
    	}else 
    		PopulaDetalhesLeilao();
       	
       	/*
    	if(cbStatus.getSelectionModel().getSelectedItem().equals("Todos")){
    		PopulaDetalheTodos(indexLeilao);
    	}
    	if(cbStatus.getSelectionModel().getSelectedItem().equals("Em Andamento")){
    		PopulaDetalheLeilaoEmAndamento(indexLeilao);
    	}
    	
    	if(cbStatus.getSelectionModel().getSelectedItem().equals("Encerrados")){
    		PopulaDetalheLeilaoEncerrado(indexLeilao);
    	}
    	
    	if(cbStatus.getSelectionModel().getSelectedItem().equals("Leilão de oferta")){
    		PopulaDetalheLeilaoOferta(indexLeilao);
    	}
    	
    	if(cbStatus.getSelectionModel().getSelectedItem().equals("Leilão de demanda")){
    		PopulaDetalheLeilaoDemanda(indexLeilao);
    	}
    	
    	if(cbStatus.getSelectionModel().getSelectedItem().equals("Lance aberto")){
    		PopulaDetalheLeilaoLanceAberto(indexLeilao);
    	}
    	
    	if(cbStatus.getSelectionModel().getSelectedItem().equals("Lance fechado")){
    		PopulaDetalheLeilaoLanceAberto(indexLeilao);
    	}	*/
    }
    
   
      public void PopulaDetalhesLeilao(){
    	l_temp = lvLeiloes.getSelectionModel().getSelectedItem();
    	try {
			tfLeilaoId.setText(Integer.toString(l_temp.getLeilaoId()));
			tfDataIni.setText(l_temp.getDataIni());
			tfDataFim.setText(l_temp.getDataFim());
			tfTipoLeilao.setText(l_temp.getTipo());
			tfTipoLance.setText(l_temp.getTipoLance());
			taLote.setText(
					"Bem no lote: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(l_temp.getLoteId()).getBemId()).getDescricao()+
					"\n"+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(l_temp.getLoteId()).getBemId()).getDetalhes()+
					"\nCategoria: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(l_temp.getLoteId()).getBemId()).getCategoria()
					);
			tfNomeVencedor.setText(l_temp.getVencedor());
			tfValorLanceVencedor.setText(Double.toString(l_temp.getArremate()));
		} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}
    }
     
   
   /* 
    public void PopulaDetalheTodos(int indexLeilao){
    	try {
			tfLeilaoId.setText(Integer.toString(leilaoDB.getTodos().get(indexLeilao).getLeilaoId()));
			tfDataIni.setText(leilaoDB.getTodos().get(indexLeilao).getDataIni());
			tfDataFim.setText(leilaoDB.getTodos().get(indexLeilao).getDataFim());
			tfTipoLeilao.setText(leilaoDB.getTodos().get(indexLeilao).getTipo());
			tfTipoLance.setText(leilaoDB.getTodos().get(indexLeilao).getTipoLance());
			taLote.setText(
					"Bem no lote: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTodos().get(indexLeilao).getLoteId()).getBemId()).getDescricao()+
					"\n"+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTodos().get(indexLeilao).getLoteId()).getBemId()).getDetalhes()+
					"\nCategoria: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTodos().get(indexLeilao).getLoteId()).getBemId()).getCategoria()
					);
			tfNomeVencedor.setText(leilaoDB.getTodos().get(indexLeilao).getVencedor());
			tfValorLanceVencedor.setText(Double.toString(leilaoDB.getTodos().get(indexLeilao).getArremate()));
		} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}
    }
    
    public void PopulaDetalheLeilaoEmAndamento(int indexLeilao){
    	try {
			tfLeilaoId.setText(Integer.toString(leilaoDB.getAtivos().get(indexLeilao).getLeilaoId()));
			tfDataIni.setText(leilaoDB.getAtivos().get(indexLeilao).getDataIni());
			tfDataFim.setText(leilaoDB.getAtivos().get(indexLeilao).getDataFim());
			tfTipoLeilao.setText(leilaoDB.getAtivos().get(indexLeilao).getTipo());
			tfTipoLance.setText(leilaoDB.getAtivos().get(indexLeilao).getTipoLance());
			taLote.setText(
					"Bem no lote: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getAtivos().get(indexLeilao).getLoteId()).getBemId()).getDescricao()+
					"\n"+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getAtivos().get(indexLeilao).getLoteId()).getBemId()).getDetalhes()+
					"\nCategoria: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getAtivos().get(indexLeilao).getLoteId()).getBemId()).getCategoria()
					);
			tfNomeVencedor.setText(leilaoDB.getAtivos().get(indexLeilao).getVencedor());
			tfValorLanceVencedor.setText(Double.toString(leilaoDB.getAtivos().get(indexLeilao).getArremate()));
		} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}
    }
    
    public void PopulaDetalheLeilaoEncerrado(int indexLeilao){
    	try {
			tfLeilaoId.setText(Integer.toString(leilaoDB.getEncerrados().get(indexLeilao).getLeilaoId()));
			tfDataIni.setText(leilaoDB.getEncerrados().get(indexLeilao).getDataIni());
			tfDataFim.setText(leilaoDB.getEncerrados().get(indexLeilao).getDataFim());
			tfTipoLeilao.setText(leilaoDB.getEncerrados().get(indexLeilao).getTipo());
			tfTipoLance.setText(leilaoDB.getEncerrados().get(indexLeilao).getTipoLance());
			taLote.setText(
					"Bem no lote: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getEncerrados().get(indexLeilao).getLoteId()).getBemId()).getDescricao()+
					"\n"+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getEncerrados().get(indexLeilao).getLoteId()).getBemId()).getDetalhes()+
					"\nCategoria: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getEncerrados().get(indexLeilao).getLoteId()).getBemId()).getCategoria()
					);
			tfNomeVencedor.setText(leilaoDB.getEncerrados().get(indexLeilao).getVencedor());
			tfValorLanceVencedor.setText(Double.toString(leilaoDB.getEncerrados().get(indexLeilao).getArremate()));
		} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}
    }
    
    public void PopulaDetalheLeilaoOferta(int indexLeilao){
    	try {
			tfLeilaoId.setText(Integer.toString(leilaoDB.getTipoOferta().get(indexLeilao).getLeilaoId()));
			tfDataIni.setText(leilaoDB.getTipoOferta().get(indexLeilao).getDataIni());
			tfDataFim.setText(leilaoDB.getTipoOferta().get(indexLeilao).getDataFim());
			tfTipoLeilao.setText(leilaoDB.getTipoOferta().get(indexLeilao).getTipo());
			tfTipoLance.setText(leilaoDB.getTipoOferta().get(indexLeilao).getTipoLance());
			taLote.setText(
					"Bem no lote: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTipoOferta().get(indexLeilao).getLoteId()).getBemId()).getDescricao()+
					"\n"+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTipoOferta().get(indexLeilao).getLoteId()).getBemId()).getDetalhes()+
					"\nCategoria: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTipoOferta().get(indexLeilao).getLoteId()).getBemId()).getCategoria()
					);
			tfNomeVencedor.setText(leilaoDB.getTipoOferta().get(indexLeilao).getVencedor());
			tfValorLanceVencedor.setText(Double.toString(leilaoDB.getTipoOferta().get(indexLeilao).getArremate()));
		} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}
    }
    
    public void PopulaDetalheLeilaoDemanda(int indexLeilao){
    	try {
			tfLeilaoId.setText(Integer.toString(leilaoDB.getTipoDemanda().get(indexLeilao).getLeilaoId()));
			tfDataIni.setText(leilaoDB.getTipoDemanda().get(indexLeilao).getDataIni());
			tfDataFim.setText(leilaoDB.getTipoDemanda().get(indexLeilao).getDataFim());
			tfTipoLeilao.setText(leilaoDB.getTipoDemanda().get(indexLeilao).getTipo());
			tfTipoLance.setText(leilaoDB.getTipoDemanda().get(indexLeilao).getTipoLance());
			taLote.setText(
					"Bem no lote: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTipoDemanda().get(indexLeilao).getLoteId()).getBemId()).getDescricao()+
					"\n"+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTipoDemanda().get(indexLeilao).getLoteId()).getBemId()).getDetalhes()+
					"\nCategoria: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTipoDemanda().get(indexLeilao).getLoteId()).getBemId()).getCategoria()
					);
			tfNomeVencedor.setText(leilaoDB.getTipoDemanda().get(indexLeilao).getVencedor());
			tfValorLanceVencedor.setText(Double.toString(leilaoDB.getTipoDemanda().get(indexLeilao).getArremate()));
		} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}
    }
    
    public void PopulaDetalheLeilaoLanceAberto(int indexLeilao){
    	try {
			tfLeilaoId.setText(Integer.toString(leilaoDB.getLanceAberto().get(indexLeilao).getLeilaoId()));
			tfDataIni.setText(leilaoDB.getLanceAberto().get(indexLeilao).getDataIni());
			tfDataFim.setText(leilaoDB.getLanceAberto().get(indexLeilao).getDataFim());
			tfTipoLeilao.setText(leilaoDB.getLanceAberto().get(indexLeilao).getTipo());
			tfTipoLance.setText(leilaoDB.getLanceAberto().get(indexLeilao).getTipoLance());
			taLote.setText(
					"Bem no lote: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getLanceAberto().get(indexLeilao).getLoteId()).getBemId()).getDescricao()+
					"\n"+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getLanceAberto().get(indexLeilao).getLoteId()).getBemId()).getDetalhes()+
					"\nCategoria: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getLanceAberto().get(indexLeilao).getLoteId()).getBemId()).getCategoria()
					);
			tfNomeVencedor.setText(leilaoDB.getLanceAberto().get(indexLeilao).getVencedor());
			tfValorLanceVencedor.setText(Double.toString(leilaoDB.getLanceAberto().get(indexLeilao).getArremate()));
		} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}
    }
    
    public void PopulaDetalheLeilaoLanceFechado(int indexLeilao){
    	try {
			tfLeilaoId.setText(Integer.toString(leilaoDB.getLanceFechado().get(indexLeilao).getLeilaoId()));
			tfDataIni.setText(leilaoDB.getLanceFechado().get(indexLeilao).getDataIni());
			tfDataFim.setText(leilaoDB.getLanceFechado().get(indexLeilao).getDataFim());
			tfTipoLeilao.setText(leilaoDB.getLanceFechado().get(indexLeilao).getTipo());
			tfTipoLance.setText(leilaoDB.getLanceFechado().get(indexLeilao).getTipoLance());
			taLote.setText(
					"Bem no lote: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getLanceFechado().get(indexLeilao).getLoteId()).getBemId()).getDescricao()+
					"\n"+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getLanceFechado().get(indexLeilao).getLoteId()).getBemId()).getDetalhes()+
					"\nCategoria: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getLanceFechado().get(indexLeilao).getLoteId()).getBemId()).getCategoria()
					);
			tfNomeVencedor.setText(leilaoDB.getLanceFechado().get(indexLeilao).getVencedor());
			tfValorLanceVencedor.setText(Double.toString(leilaoDB.getLanceFechado().get(indexLeilao).getArremate()));
		} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}	
    }
    
    */
    
  //alimentaçao da lista de usuarios com base na ChoiceBox de usuários	
  	private void controleCbUsuarios(String newValuecbUsuarios){
  		switch (newValuecbUsuarios) {
  		
	  		case ("Selecione um tipo de usuário"):
	  			listaUsuarios.clear();
	  			break;
	  		
	        case ("Pessoa Física"):  
	        	listaUsuarios.clear();
	          	try {
	          		for(int i=0;i<Usuario_PfDB.getTodos().size();i++){
	          			listaUsuarios.add(Usuario_PfDB.getTodos().get(i));
	      			}
	      			lvUsuarios.setItems(listaUsuarios);
	      			
	      		} catch (DAOException e) {
	      			// TODO Auto-generated catch block
	      			e.printStackTrace();
	      		} 	
	          	break;
	          
	        case ("Pessoa Juridica"):
	          	listaUsuarios.clear();
	          	try {
	      			for(int i=0;i<Usuario_PjDB.getTodos().size();i++){
	      				listaUsuarios.add(Usuario_PjDB.getTodos().get(i));
	      			}
	      			lvUsuarios.setItems(listaUsuarios);
	      		} catch (DAOException e) {
	      			// TODO Auto-generated catch block
	      			e.printStackTrace();
	      		}
	          	break;
	          
	        default:
	          	
	        break;
  		}
  	}
     
    @FXML
    void SelecionarUsuario(ActionEvent event) {
    	int indexUsuario=lvUsuarios.getSelectionModel().getSelectedIndex(); //pega o indice do item clicado na view
    	
       	if(cbUsuarios.getSelectionModel().getSelectedItem().equals("Selecione um tipo de usuário")){
       		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Primeiro selecione o tipo de usuário para visualizar");
			alert.showAndWait();
    	}
       	
    	if(cbUsuarios.getSelectionModel().getSelectedItem().equals("Pessoa Física")){
    		PopulaUsuarioPF(indexUsuario);
    	}
    
    	if(cbUsuarios.getSelectionModel().getSelectedItem().equals("Pessoa Juridica")){
    		PopulaUsuarioPJ(indexUsuario);
    	}
    }
           
    public void PopulaUsuarioPJ(int indexUsuario){
    	try {
			tfUsuarioSelecionado.setText(
					Usuario_PjDB.getTodos().get(indexUsuario).getNome()+", e-mail:"+Usuario_PjDB.getTodos().get(indexUsuario).getEmail()
					);
    	} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}
    }
    
    public void PopulaUsuarioPF(int indexUsuario){
    	try {
			tfUsuarioSelecionado.setText(Usuario_PfDB.getTodos().get(indexUsuario).getNome()+", e-mail:"+Usuario_PfDB.getTodos().get(indexUsuario).getEmail());
    	} catch (DAOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	    catch (ArrayIndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atenção!");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um leilão para ver os detalhes.\n\nMensagem do sistema: "+e.toString());
			alert.showAndWait();
		}
    	tfLance.setEditable(true);
    }
    
    //TODO terminar Lance
    @FXML
    void DarLance(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Atenção!");
		alert.setHeaderText(null);
		alert.setContentText("ainda não implementado");
		alert.showAndWait();
    	//Lance la=new Lance(
    			//l_temp.getLeilaoId(),
    			//lvUsuarios.getSelectionModel().getSelectedItem().
    			
    		//	);

    }
    
  //menu novo leilão
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
  	
  	//menu novo bem	
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
  	
  	//menu novo usuario
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
