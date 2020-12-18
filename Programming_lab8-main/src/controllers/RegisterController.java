package controllers;

import Utils.ServerPart.ServerClasses.SpecialCommands.Register;
import Utils.ClientPart.ClientHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class RegisterController {

    //Окно для регистрации
    private static String name777 = null;
    private static String pass777 = null;


    private ResourceBundle currentBundle;
    private MainController mainController;
    private ClientHandler clientHandler;

    public RegisterController(){
        currentBundle = new MainController().getCurrentBundle();
    }

    public RegisterController(MainController mainController, ClientHandler clientHandler, ResourceBundle currentBundle) {
        this.mainController = mainController;
        this.clientHandler = clientHandler;
        this.currentBundle = mainController.getCurrentBundle();
    }

    @FXML private Button OkButton;
    @FXML private TextField nameField;
    @FXML private PasswordField passField;
    @FXML private Button CancelButton;
    @FXML private Button helpField;
    @FXML private Label Reg_label;

    @FXML void helpClick(){
        showAlert(currentBundle.getString("info_button"));
    }

    @FXML void OkClick(ActionEvent event) throws IOException {
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String name;
        String password;

        try{
            name = nameField.getText();
            password = passField.getText();

            boolean lessThen4 = name.trim().split("\\s+")[0].length() < 4;
            boolean withSpaces = name.trim().split("\\s+").length != 1;
            boolean invalidChars = !name.trim().split("\\s+")[0].matches("[a-z0-9]+");

            if(lessThen4){
                showAlert(currentBundle.getString("lessThen4"));
                return;
            }

            if(withSpaces){
                showAlert(currentBundle.getString("withSpaces"));
                return;
            }
            if(invalidChars){
                showAlert(currentBundle.getString("invalidChars"));
                return;
            }

            String answer = clientHandler.checkConnection();

            if (answer.equals("server_is_not_available") || answer.equals("DB_is_not_available")){
                showAlert(answer);
            }

            clientHandler.sendRequest(new Register(name, password));
            String ans;

            while(true){
                ans = clientHandler.receiveAnswer().trim();
                if(!ans.equals("")) break;
            }

            System.out.println(ans);
            if(ans.equals("err_register")){
                showAlert(currentBundle.getString("uncorrectLogPas"));
            }else {

                name777 = name;
                pass777 = password;
                thisStage.close();

            }
        }catch (IllegalArgumentException e){
            System.out.println("error");
        }





    }


    @FXML
    void cancelClick(ActionEvent event) {
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }




    private void showAlert(String strAlert) {
        System.out.println("Authorization: " + strAlert);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(strAlert);

        alert.showAndWait();
    }


    @FXML
    void initialize() {


        CancelButton.setText(currentBundle.getString("cancel_button"));
        nameField.setPromptText(currentBundle.getString("write_userName"));
        passField.setPromptText(currentBundle.getString("write_userPass"));
        Reg_label.setText(currentBundle.getString("Reg_label"));

    }

    public String getName(){return name777;}
    public String getPass(){return pass777;}

    public void setClientMessagesHandler(ClientHandler clientMessagesHandler){
        this.clientHandler = clientMessagesHandler;
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }


}
