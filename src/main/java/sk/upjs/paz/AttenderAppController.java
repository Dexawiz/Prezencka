package sk.upjs.paz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AttenderAppController {

    @FXML
    private Button generujButton;

    @FXML
    private Label vysledokLabel;

    @FXML
    void generujButtonAction(ActionEvent event) {
        vysledokLabel.setText(Math.random() + "");
    }

}
