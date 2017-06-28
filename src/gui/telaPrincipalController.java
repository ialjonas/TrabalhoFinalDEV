package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dados.BemDAOJavaDb;
import dados.DAOException;
import dados.LeilaoDAOJavaDb;
import dados.LoteDAOJavaDb;
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
import negocio.Leilao;
import negocio.Lote;

public class telaPrincipalController implements Initializable{
	LeilaoDAOJavaDb leilaoDB=LeilaoDAOJavaDb.getInstance();
	LoteDAOJavaDb loteDB=LoteDAOJavaDb.getInstance();
	BemDAOJavaDb bemDB=BemDAOJavaDb.getInstance();
	ObservableList<String> itensChoisestatus = FXCollections.observableArrayList(
			"Selecione um status para exibir","Todos","Em Andamento","Encerrados","Leilão de oferta","Leilão de demanda","Lance aberto","Lance fechado"
	);
	ObservableList<Leilao> listaLeiloes = FXCollections.observableArrayList();
	
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
    private ListView<Leilao> lvLeiloes;
    
    @FXML
    private Button bDetalheLeilao;
    
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
    private TextArea taLote;
    
    @FXML
    private TextField tfNomeCriador;

    @FXML
    private TextField tfNomeVencedor;

    @FXML
    private TextField tfValorLanceVencedor;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbStatus.setItems(itensChoisestatus);
		cbStatus.getSelectionModel().select(0);
		cbStatus.setTooltip(new Tooltip("Selecione o status do leilão"));
		
		cbStatus.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String valorAntigo, String novoValor) {
	        	refresh(novoValor);
			}
	    });
	}
	
	private void refresh(String novoValor){
		switch (novoValor) {
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
    			// TODO Auto-generated catch block
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
    			// TODO Auto-generated catch block
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
    			// TODO Auto-generated catch block
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
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	break;

        
        default:
        	
		}
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
    void DetalheLeilao(ActionEvent event){
    	

    	//falta implementar solução para problema de consultas incorretas, já tenho a solução
    	
    	int index=lvLeiloes.getSelectionModel().getSelectedIndex(); //pega o indice do item clicado na view
    	try {
			tfLeilaoId.setText(Integer.toString(leilaoDB.getTodos().get(index).getLeilaoId()));
			tfDataIni.setText(leilaoDB.getTodos().get(index).getDataIni());
			tfDataFim.setText(leilaoDB.getTodos().get(index).getDataFim());
			tfTipoLeilao.setText(leilaoDB.getTodos().get(index).getTipo());
			tfTipoLance.setText(leilaoDB.getTodos().get(index).getTipoLance());
			taLote.setText(
					"Bem no lote: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTodos().get(index).getLoteId()).getBemId()).getDescricao()+
					"\n"+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTodos().get(index).getLoteId()).getBemId()).getDetalhes()+
					"\nCategoria: "+bemDB.getBemPorBemID(loteDB.getLotePorLoteID(leilaoDB.getTodos().get(index).getLoteId()).getBemId()).getCategoria()
					);
			tfNomeCriador.setText(leilaoDB.getTodos().get(index).getCriador());
			tfNomeVencedor.setText(leilaoDB.getTodos().get(index).getVencedor());
			tfValorLanceVencedor.setText(Double.toString(leilaoDB.getTodos().get(index).getArremate()));
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
