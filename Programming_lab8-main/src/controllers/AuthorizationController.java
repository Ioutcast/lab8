package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import Worker.Status;
import Worker.ColorText;
import Utils.ServerPart.ServerClasses.SpecialCommands.Login;
import Utils.ClientPart.ClientHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AuthorizationController {
    private MainController mainController;
    private ClientHandler clientHandler;

    private String LanguageLabel;


    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField login;
    @FXML private PasswordField password;
    @FXML private Button logining;
    @FXML private Button registration;
    @FXML private MenuButton selectLanguage;
    @FXML private Label registrationLogin;
    @FXML private Label registrationAuto;
    @FXML private Button CheckButton;


    @FXML void loginClick(ActionEvent event) {
        try {
            String login1 = login.getText();
            String password1 = password.getText();
            clientHandler.setLogin(login1);
            clientHandler.setPassword(password1);
            clientHandler.sendRequest(new Login(login1, password1));
            String answer = "";

            while (true){
                answer = clientHandler.receiveAnswer().trim();
                if(!answer.equals("")) break;
            }

            System.out.println("AuthorizationController received " + answer);

            if (answer.equals("suc_login")){
                showAlert("suc_login");
                Stage authorizedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                clientHandler.setAuthorized(true);
                clientHandler.setLogin(login1);
                authorizedStage.close();
            }
            else {
                showAlert(answer);
            }
        } catch (Exception e){
            e.printStackTrace();
            //showAlert("Error");
        }
    }

    @FXML void CheckButton() throws IOException {
        String ans = clientHandler.checkConnection();
        showAlert(ans);

    }



    @FXML void registrationClick(ActionEvent event) throws IOException {

        String fxmFile = "../JavaFX/AutoMenu.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmFile));
        fxmlLoader.setResources(mainController.getCurrentBundle());

        Parent root = fxmlLoader.load();
        RegisterController argDialogController = fxmlLoader.getController();
        argDialogController.setMainController(mainController);
        argDialogController.setClientMessagesHandler(clientHandler);


        Stage dialogStage = new Stage();
        dialogStage.setTitle(mainController.getCurrentBundle().getString("InputData"));
        dialogStage.setResizable(false);
        dialogStage.setScene(new Scene(root));
        dialogStage.requestFocus();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.showAndWait();

        String pass = argDialogController.getPass();
        String name = argDialogController.getName();


        if(name != null && pass != null){
            login.setText(name);
            password.setText(pass);
        }

    }




    private void showAlert(String strAlert) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(mainController.getCurrentBundle().getString(strAlert));

        alert.showAndWait();
    }



    public void setClientMessagesHandler(ClientHandler clientMessagesHandler){
        this.clientHandler = clientMessagesHandler;
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    @FXML private void switchRussian(){
        mainController.setCurrentBundle(ResourceBundle.getBundle("bundles.ClassLanguage", new Locale("ru", "RU")));
        LanguageLabel = "Русский (RU)";
        changeLanguage();
    }

    @FXML void switchCanadian() {
        mainController.setCurrentBundle(ResourceBundle.getBundle("bundles.ClassLanguage", new Locale("en", "CA")));
        LanguageLabel = "English (CA)";
        changeLanguage();
    }

    @FXML void switchDank() {
        mainController.setCurrentBundle(ResourceBundle.getBundle("bundles.ClassLanguage", new Locale("da", "DA")));
        LanguageLabel = "Dansk (DA)";
        changeLanguage();
    }

    @FXML void switchRomania() {
        mainController.setCurrentBundle(ResourceBundle.getBundle("bundles.ClassLanguage", new Locale("ro", "RO")));
        LanguageLabel = "Română (RO)";
        changeLanguage();
    }

    private void changeLanguage(){
        selectLanguage.setText(LanguageLabel);
        //selectLanguage.setText(mainController.getCurrentBundle().getString("selectLanguage"));
        registrationLogin.setText(mainController.getCurrentBundle().getString("registrationLogin"));
        logining.setText(mainController.getCurrentBundle().getString("loginBut"));
        registration.setText(mainController.getCurrentBundle().getString("regBut"));
        registrationAuto.setText(mainController.getCurrentBundle().getString("Autor"));
        registrationLogin.setAlignment(Pos.CENTER);
        login.setPromptText(mainController.getCurrentBundle().getString("write_userName"));
        password.setPromptText(mainController.getCurrentBundle().getString("write_userPass"));



    }

    public void setregistrationLoginPos(Pos pos){
        registrationLogin.setAlignment(pos);
    }

    private boolean isNum(String str){
        try {
            Integer i = Integer.parseInt(str);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @FXML
    void initialize() {

        login.setText("aaaaa");
        password.setText("aaaaa");
    }
}