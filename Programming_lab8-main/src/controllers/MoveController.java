package controllers;

import Worker.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MoveController {
    //Контроллер для передвижения дракона из таблицы визуализации
    public MoveController(){
    }

    private MainController mainController;
    private Worker worker;
    private static int coordX = 0;
    private static int coordY = 0;

    @FXML
    private TextField XField;

    @FXML
    private Label MoveWorkerLabel;

    @FXML
    private TextField YField;

    @FXML
    private Button CancelLabel;

    @FXML
    void CancelButton(ActionEvent event) {
        worker = null;
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }



    @FXML
    void Okbutton(ActionEvent event){
        int x = coordX;
        int y = coordY;
        try {
            y = Integer.parseInt(YField.getText());
            x = Integer.parseInt(XField.getText());
        }catch (Exception e){
            return;
        }
        coordX = x;
        coordY = y;
        worker.setCoordinates(new Coordinates(coordX, coordY));
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }

    @FXML
    void DownButton(ActionEvent event) {
        coordY = coordY - 1;
        if(coordY < -10) coordY = -10;
        YField.setText(String.valueOf(coordY));
    }

    @FXML
    void LeftButton(ActionEvent event) {
        coordX = coordX - 1;
        if(coordX < -100 )coordX = -100;
        XField.setText(String.valueOf(coordX));
    }

    @FXML
    void RightButton(ActionEvent event) {
        coordX = coordX + 1;
        if(coordX > 250 ) coordX = 250;
        XField.setText(String.valueOf(coordX));
    }

    @FXML
    void UpButton(ActionEvent event) {
        coordY = coordY + 1;
        if(coordY > 100) coordY = 100;
        YField.setText(String.valueOf(coordY));
    }


    public void setWorker(Worker worker){
        this.worker = worker;
    }

    public Worker getWorker(){
        return worker;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @FXML
    void initialize() {
        MoveWorkerLabel.setText(new MainController().getCurrentBundle().getString("moveWorker"));
        CancelLabel.setText(new MainController().getCurrentBundle().getString("cancel_button"));




    }

    public void setInitialize(Worker worker){
        this.worker = worker;
        XField.setText(worker.getCoordinates().getX().toString());
        YField.setText(worker.getCoordinates().getY().toString());
        coordY = worker.getCoordinates().getY();
        coordX = worker.getCoordinates().getX();
    }
}
