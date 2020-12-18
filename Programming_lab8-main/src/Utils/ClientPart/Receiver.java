package Utils.ClientPart;

import Utils.ServerPart.ServerClasses.Commands.Show;
import controllers.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ResourceBundle;

public class Receiver extends Thread {
    MainController mainController;
    ClientHandler clientHandler;
    ResourceBundle currentBundle;


    /**
     * Не используется!!!
     *
     *
     *
     * @param mainController
     * @param clientHandler
     */
    public Receiver(MainController mainController, ClientHandler clientHandler) {
        this.mainController = mainController;
        this.clientHandler = clientHandler;
        currentBundle = MainController.getCurrentBundle();
    }

    double number = 0.0D;

    Show showRequest = new Show();


    @Override
    public void run() {
        try {
            showRequest.setString("Number_request");

            while(true) {
                clientHandler.sendCommand(showRequest);
                String str = clientHandler.receiveAnswer().trim();
                while (str.equals("")){
                    str = clientHandler.receiveAnswer().trim();
                }

                try {
                    if (Double.parseDouble(str) == number){
                        clientHandler.sendCommand(new Show());
                        str = clientHandler.receiveCollection();

                        if(str == "suc_show"){

                        }

                    }
                }catch (Exception e){

                }

                sleep(5000);
            }
        } catch (Exception e) {
            showINFO(Alert.AlertType.ERROR, currentBundle.getString("Error"), currentBundle.getString("Error"));
            System.exit(-1);
        }
    }








    private void showINFO(Alert.AlertType alertType, String title, String content){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(currentBundle.getString("HMColl") +": " + content);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK){
                System.out.println(currentBundle.getString("windowClose"));
            }
        });
    }
}
