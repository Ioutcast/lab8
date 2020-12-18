package controllers;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ArgController {

    Integer id;

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField inputArg;
    @FXML private Label argName;
    @FXML private Button CancelButton;
    @FXML private Button OkButton;

    @FXML
    void CancelClick(ActionEvent event) {
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        inputArg.setText("");
        thisStage.close();
    }

    @FXML
    void OKClick(ActionEvent event) {


        try {
            id = Integer.parseInt(inputArg.getText());

            Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisStage.close();
        }catch (Exception e){
            showAlertWithoutHeaderText("Wrong_input");
        }

        return;
    }

    private void showAlertWithoutHeaderText(String strAlert) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(new MainController().getCurrentBundle().getString(strAlert));

        alert.showAndWait();
    }

    public Integer getId(){
        return id;
    }


    public Label getArgName(){
       return argName;
    }

    public String getArg(){
        return id.toString();
    }

}
