package controllers;

import Worker.Worker;
import Utils.ServerPart.ServerClasses.Commands.*;
import Utils.ClientPart.ClientHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ResourceBundle;


public class ScriptController {


    private static ObservableList<AbstractCommand> abstractCommands = FXCollections.observableArrayList();
    private MainController mainController;
    private ClientHandler clientHandler;



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }


    @FXML
    private Label commandManager;


    @FXML
    private Button DeleteAll;

    @FXML
    private Button executeButton;

    @FXML
    private Button CancelButton;

    @FXML
    private Menu menuHelp;

    @FXML
    private MenuItem help;

    @FXML
    private MenuItem info;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label addCmdLabel;

    @FXML
    private Button insert;

    @FXML
    private Label removeCmdLabel;

    @FXML
    private Button remove_key;

    @FXML
    private Button removeLower;

    @FXML
    private Button remove_greater;

    @FXML
    private Button remove_lower_key;

    @FXML
    private Button clear;

    @FXML
    private Label updateCmdLabel;

    @FXML
    private Button update;


    @FXML
    private Tab tabWorker;

    @FXML
    private TableView<AbstractCommand> dbTable;

    @FXML
    private TableColumn<AbstractCommand, String> CommandColumn;

    @FXML
    private TableColumn<AbstractCommand, String> ParColumn;

    @FXML
    private TableColumn<AbstractCommand, String> WorkerColumn;

    @FXML
    private TableColumn<AbstractCommand, String> userColumn;





    @FXML
    void DeleteAllClick(ActionEvent event) {
    abstractCommands.clear();
    createListeners();
    }


    @FXML
    void executeClick(ActionEvent event) throws IOException {
        for(AbstractCommand abstractCommand : abstractCommands){
            clientHandler.sendCommand(abstractCommand);
            String ans = clientHandler.receiveAnswer().trim();
            while(ans.equals("")){
                ans = clientHandler.receiveAnswer().trim();
            }
            System.out.println(ans);
        }
        mainController.showString(Alert.AlertType.INFORMATION, new MainController().getCurrentBundle().getString("script"), new MainController().getCurrentBundle().getString("suc_script"));
        abstractCommands.clear();
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }


    @FXML
    void CancelClick(ActionEvent event) {
        abstractCommands.clear();
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }


    @FXML
    void clearClick(ActionEvent event) {
        Clear clear = new Clear();
        clear.setUserName(clientHandler.getLogin());
        abstractCommands.add(clear);
        createListeners();
    }


    @FXML
    void help(ActionEvent event) {
        mainController.showHelp(mainController.getCurrentBundle().getString("Info"));
        createListeners();
    }

    @FXML
    void info(ActionEvent event) {
        PrintInfoClass printInfoClass = new PrintInfoClass();
        printInfoClass.setUserName(clientHandler.getLogin());
        abstractCommands.add(printInfoClass);
        createListeners();
    }

    @FXML
    void insertClick(ActionEvent event) {
        try{
        Insert insert = (Insert)mainController.getCommandWithSimpleArg(new Insert(), mainController.getCurrentBundle().getString("input_id"));
        insert.setUserName(clientHandler.getLogin());
        Worker worker = mainController.getWorkerFromController("add");
        worker.setId(Integer.parseInt(insert.getString()));
        insert.setWorker(worker);
        abstractCommands.add(insert);
        createListeners();}catch (NullPointerException e){
            return;
        }
    }

    @FXML
    void removeByKeyClick(ActionEvent event) {
        try {
            Remove remove = (Remove) mainController.getCommandWithSimpleArg(new Remove(), mainController.getCurrentBundle().getString("removeID"));
            remove.setUserName(clientHandler.getLogin());
            abstractCommands.add(remove);
            createListeners();
        }catch (NullPointerException e){
            return;
        }
    }

    @FXML
    void removeGreaterClick(ActionEvent event) {
        try{
        Remove_greater removeLowerKey = new Remove_greater();//Лень было название менять
        removeLowerKey.setUserName(clientHandler.getLogin());
        Worker worker = mainController.getWorkerFromController("removeLower");
        worker.setUserName(clientHandler.getLogin());
        removeLowerKey.setUserName(clientHandler.getLogin());
        worker.setId(Integer.parseInt(removeLowerKey.getString()));
        removeLowerKey.setWorker(worker);
        createListeners();}catch (NullPointerException e){
            return;
        }
    }

    @FXML
    void removeLowerClick(ActionEvent event) {
        try{
        Remove_lower removeLowerKey = new Remove_lower();
        removeLowerKey.setUserName(clientHandler.getLogin());
        Worker worker = mainController.getWorkerFromController("removeLower");
        worker.setUserName(clientHandler.getLogin());
        removeLowerKey.setUserName(clientHandler.getLogin());
        worker.setId(Integer.parseInt(removeLowerKey.getString()));
        removeLowerKey.setWorker(worker);
        createListeners();}catch (NullPointerException e){
            return;
        }
    }

    @FXML
    void removeLowerKeyClick(ActionEvent event) {
        try{
        Remove_lower_key remove_lower_key = (Remove_lower_key) mainController.getCommandWithSimpleArg(new Remove_lower_key(), mainController.getCurrentBundle().getString("remove_lower_key"));
        remove_lower_key.setUserName(clientHandler.getLogin());
        createListeners();}catch (NullPointerException e){
            return;
        }
    }

    @FXML
    void updateClick(ActionEvent event) throws TransformerException, ParserConfigurationException {
        try{
        Update update = (Update) mainController.getCommandWithSimpleArg(new Update(), mainController.getCurrentBundle().getString("updateCom"));

        Worker worker = mainController.getWorkerFromController("updateCom");
        update.setUserName(clientHandler.getLogin());
        worker.setUserName(clientHandler.getLogin());
        worker.setId(Integer.parseInt(update.getString()));
        update.setWorker(worker);
        createListeners();}catch (NullPointerException e){
            return;
        }
    }


    private void createListeners(){
        CommandColumn.setCellValueFactory(abstractCommand -> new SimpleStringProperty(abstractCommand.getValue().getCommand()));
        ParColumn.setCellValueFactory(abstractCommand -> new SimpleStringProperty(abstractCommand.getValue().getString()));
        WorkerColumn.setCellValueFactory(abstractCommand -> new SimpleStringProperty(abstractCommand.getValue().getWorker().getName() + " " + abstractCommand.getValue().getWorker().getId()));
        userColumn.setCellValueFactory(abstractCommand -> new SimpleStringProperty(new ClientHandler().getLogin()));
        dbTable.setItems(abstractCommands);
    }
    @FXML
    void initialize() {
        //Блок верхнего меню
        ResourceBundle currentBundle = new MainController().getCurrentBundle();

        menuHelp.setText(currentBundle.getString("help"));
        help.setText(currentBundle.getString("helpCom"));
        info.setText(currentBundle.getString("info"));



        //Блок нижнего меню (ТулБар)
        usernameLabel.setText(currentBundle.getString("username") + new ClientHandler().getLogin());



        //Блок команд:
        addCmdLabel.setText(currentBundle.getString("addCommands"));
        insert.setText(currentBundle.getString("insertCom"));

        removeCmdLabel.setText(currentBundle.getString("removeCommands"));
        remove_key.setText(currentBundle.getString("removeID"));
        removeLower.setText(currentBundle.getString("removeLower"));
        remove_greater.setText(currentBundle.getString("removeGreater"));
        remove_lower_key.setText(currentBundle.getString("remove_lower_key"));;
        clear.setText(currentBundle.getString("clearCom"));

        updateCmdLabel.setText(currentBundle.getString("updateCommands"));
        update.setText(currentBundle.getString("updateCom"));


        //Блок таблиц и визуализации
        tabWorker.setText(currentBundle.getString("tabWorker"));
        CommandColumn.setText(currentBundle.getString("command"));
        ParColumn.setText(currentBundle.getString("param"));
        WorkerColumn.setText(currentBundle.getString("worker"));
        userColumn.setText(currentBundle.getString("user"));
        commandManager.setText(currentBundle.getString("commandManager"));
        DeleteAll.setText(currentBundle.getString("delete"));
        executeButton.setText(currentBundle.getString("execute"));
        CancelButton.setText(currentBundle.getString("cancel_button"));

    }

}
