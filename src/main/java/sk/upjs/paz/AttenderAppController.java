package sk.upjs.paz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class AttenderAppController {

    @FXML
    private Button generujButton;

    @FXML
    private Label vysledokLabel;

    @FXML
    void generujButtonAction(ActionEvent event) throws IOException {
        var allStudents = StudentService.loadFromResources();
        var service = new StudentService(allStudents);

        RatioOfGenders r = service.ratio();

        String text = String.format(
                "Мужчины: %d, Женщины: %d, Мутанты: %d%nДоля мужчин: %.2f, Доля женщин: %.2f,%n Доля мутантов: %.2f =)",
                r.man(), r.woman(), r.mutant(),
                r.ratioToMen(r.man(), r.woman(), r.mutant()),
                r.ratioToWomen(r.man(), r.woman(), r.mutant()),
                r.ratioToMutants(r.man(), r.woman(), r.mutant())
        );


        vysledokLabel.setText(text);
    }

}
