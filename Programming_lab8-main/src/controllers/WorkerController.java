package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import Worker.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class WorkerController {
    private Worker worker;
    private ResourceBundle currentBundle;

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Label DCText;
    @FXML private ComboBox<Status> statusComboBox;
    @FXML private ComboBox<Location> TypeBox;
    @FXML private ComboBox<OrganizationType> charBox;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField employeesCount;
    @FXML private TextField coordXField;
    @FXML private TextField coordYField;
    @FXML private Label worker_param;

    @FXML
    void CancelClick(ActionEvent event) {
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }

    @FXML
    void OkClick(ActionEvent event) {
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String name;
        Location type;
        Status status;
        OrganizationType charac;
        int coor_x, coor_y, age;
        int depth;

        name = nameField.getText();

        if (name.trim().isEmpty()) {
            showAlertWithoutHeaderText("emptyName");
            return;
        }

        try{
            coor_x = Integer.parseInt(coordXField.getText());

        }catch (Exception e) {
            showAlertWithoutHeaderText("InvalidX");
            return;
        }

        try{
            coor_y = Integer.parseInt(coordYField.getText());



        }catch (Exception e) {
            showAlertWithoutHeaderText("InvalidY");
            return;
        }
        try {
            age = Integer.parseInt(ageField.getText());
            if (age < 0) {
                showAlertWithoutHeaderText("InvalidAge");
                return;
            }
        } catch (NumberFormatException e) {
            showAlertWithoutHeaderText("InvalidAge");
            return;
        }

        try {
            depth = Integer.parseInt(employeesCount.getText());
            if (depth < 0) {
                showAlertWithoutHeaderText("InvalidCave");
                return;
            }
        } catch (Exception e) {
            showAlertWithoutHeaderText("InvalidCave");
            return;
        }

        try {
            status = statusComboBox.getValue();
        }catch (NullPointerException e){
            showAlertWithoutHeaderText("emptyColor");
            return;
        }
        try {
            type = TypeBox.getValue();
        }catch (NullPointerException e){
            showAlertWithoutHeaderText("emptyType");
            return;
        }
        try {
            charac = charBox.getValue();
        }catch (NullPointerException e){
            showAlertWithoutHeaderText("emptyChar");
            return;
        }
        worker = new Worker(name, status, new Coordinates(coor_x, coor_y), age, type, charac, new Organization((long) depth));


        thisStage.close();
    }

    @FXML
    void initialize() {

        ObservableList<Location> locationObservableList = FXCollections.observableArrayList(Location.WEST, Location.SOUTH, Location.NORTH,
                Location.EAST);

        TypeBox.getItems().addAll(locationObservableList);

        ObservableList<Status> statusObservableList = FXCollections.observableArrayList(Status.FIRED, Status.HIRED, Status.RECOMMENDED_FOR_PROMOTION, Status.UNKNOWN);
        statusComboBox.getItems().addAll(statusObservableList);

        ObservableList<OrganizationType> characterObservableList = FXCollections.observableArrayList(OrganizationType.COMMERCIAL, OrganizationType.PUBLIC,
                OrganizationType.PRIVATE_LIMITED_COMPANY, OrganizationType.OPEN_JOINT_STOCK_COMPANY);
        charBox.getItems().addAll(characterObservableList);
    }

    private void showAlert(String fieldName, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(currentBundle.getString("Error"));
        alert.setHeaderText(currentBundle.getString("IncorrectField") + "\"" + fieldName + "\"");
        alert.setContentText(content);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println(currentBundle.getString("windowClose"));
            }
        });
    }

    private void showAlertWithoutHeaderText(String strAlert) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning alert");


        alert.setHeaderText(null);
        alert.setContentText(currentBundle.getString(strAlert));

        alert.showAndWait();
    }

    public void setCurrentBundle(ResourceBundle currentBundle){
        this.currentBundle = currentBundle;
    }

    public Worker getWorker(){
        return worker;
    }

    public void setParametersWorker(){
        worker_param.setText(currentBundle.getString("parameters_worker"));
    }

}
