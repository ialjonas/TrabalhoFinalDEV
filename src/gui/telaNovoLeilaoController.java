package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class telaNovoLeilaoController {
	 @FXML
	    private ChoiceBox<?> cbLeilao;

	    @FXML
	    private ChoiceBox<?> cbLance;

	    @FXML
	    private DatePicker dpInicio;

	    @FXML
	    private DatePicker dpFim;

	    @FXML
	    private ComboBox<?> cbUser;

	    @FXML
	    private TextField tfValor;
}
