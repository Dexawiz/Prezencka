package sk.upjs.ics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {

    @FXML
    private Label attendanceLabel;

    @FXML
    private Button computeBtn;

    @FXML
    private TextField subjectTextField;

    @FXML
    void computeBtnAction(ActionEvent event) {
        AttendanceService service;
        try {
            service = AttendanceService.fromResources();
        } catch (IOException e) {
            attendanceLabel.setText("Error: " + e.getMessage());
            return;
        }

        String subject = subjectTextField.getText();

        var average = service.averageNumberOfStudents(subject);
        attendanceLabel.setText("Average number of students: " + average);
    }

}
