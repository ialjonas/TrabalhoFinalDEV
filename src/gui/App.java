package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		Parent tela1 = FXMLLoader.load(getClass().getResource("telaPrincipal.fxml"));
		
		Scene scene = new Scene(tela1);
		
		stage.setScene(scene);
		stage.setTitle("Controle de Leilões");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}