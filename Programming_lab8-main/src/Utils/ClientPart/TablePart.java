package Utils.ClientPart;

import Worker.Worker;
import controllers.MainController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TablePart{
    private ClientHandler updateCollectionClientMessagesHandler;
    private ObservableList<Worker> workerData = FXCollections.observableArrayList();
    private VisualizationPart visualisationWorker;
    private MainController mainController;

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField cordXField;
    @FXML private TextField cordYField;
    @FXML private TextField WorkerAgeField;
    @FXML private TextField ColorField;
    @FXML private TextField TypeField;
    @FXML private TextField CharField;
    @FXML private TextField CaveField;
    @FXML private TextField userField;

    @FXML private TableColumn<Worker, Integer> idColumn;
    @FXML private TableColumn<Worker, String> nameColumn;
    @FXML private TableColumn<Worker, Integer> WorkerAgeColumn;
    @FXML private TableColumn<Worker, Integer> coordXColumn;
    @FXML private TableColumn<Worker, Integer> coordYColumn;
    @FXML private TableColumn<Worker, String> ColorColumn;
    @FXML private TableColumn<Worker, String> TypeColumn;
    @FXML private TableColumn<Worker, String> CharColumn;
    @FXML private TableColumn<Worker, Float> CaveDepthColumn;
    @FXML private TableColumn<Worker, String> userColumn;
    @FXML private TableView<Worker> dbTable;
    private static boolean updateRQ = true;
    public TablePart(ClientHandler updateCollectionClientMessagesHandler, VisualizationPart visualisationWorker, TextField idField, TextField nameField,
                     TextField cordXField, TextField cordYField, TextField workerAgeField, TextField colorField, TextField typeField, TextField charField,
                     TextField caveField, TextField userField, TableColumn<Worker, Integer> idColumn, TableColumn<Worker, String> nameColumn,
                     TableColumn<Worker, Integer> WorkerAgeColumn, TableColumn<Worker, Integer> coordXColumn, TableColumn<Worker, Integer> coordYColumn,
                     TableColumn<Worker, String> ColorColumn, TableColumn<Worker, String> TypeColumn, TableColumn<Worker, String> CharColumn,
                     TableColumn<Worker, Float> CaveDepthColumn, TableColumn<Worker, String> userColumn, TableView<Worker> dbTable, MainController mainController) {
        this.updateCollectionClientMessagesHandler = updateCollectionClientMessagesHandler;
        this.visualisationWorker = visualisationWorker;
        this.idField = idField;
        this.nameField = nameField;
        this.cordXField = cordXField;
        this.cordYField = cordYField;
        WorkerAgeField = workerAgeField;
        ColorField = colorField;
        TypeField = typeField;
        CharField = charField;
        CaveField = caveField;
        this.userField = userField;
        this.idColumn = idColumn;
        this.nameColumn = nameColumn;
        this.WorkerAgeColumn = WorkerAgeColumn;
        this.coordXColumn = coordXColumn;
        this.coordYColumn = coordYColumn;
        this.ColorColumn = ColorColumn;
        this.TypeColumn = TypeColumn;
        this.CharColumn = CharColumn;
        this.CaveDepthColumn = CaveDepthColumn;
        this.userColumn = userColumn;
        this.dbTable = dbTable;
        this.mainController = mainController;
       try{
            updateCollectionClientMessagesHandler.connect("localhost", 50000);
            //setUpTimer();
        }catch (Exception e){
            e.printStackTrace();
        }

    }




    public void setUpTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
            updateTable();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }



    private void updateTable(){
        try {
            updateCollectionClientMessagesHandler.sendShowRequest();
            String answer = updateCollectionClientMessagesHandler.receiveCollection();

            if(answer.equals("err_show")) return;
            //System.out.println(answer + " ->updateTable");
            if (answer.contains("suc_show")){
                setUpdateRQ(true);
            }
            if (updateRQ){
                mainController.tableReset();
                visualisationWorker.setupVisualisation();
                updateRQ = false;

            }
        } catch (Exception e) {
            System.out.println(e + " -> updateTable -> TablePart");
        }
    }



    public ObservableList<Worker> getWorkerData(){
        return workerData;
    }


    //Поиск по шаблону

    private void createListeners(FilteredList<Worker> workerData){
        idField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getId()).contains(value)){
                    return true;
                }else return false;
            });
        });

        nameField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getName()).contains(value)){
                    return true;
                }else return false;
            });
        });

        cordXField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getCoordinates().getX()).contains(value)){
                    return true;
                }else return false;
            });
        });
        cordYField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getCoordinates().getY()).contains(value)){
                    return true;
                }else return false;
            });
        });
        WorkerAgeField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getAge()).contains(value)){
                    return true;
                }else return false;
            });
        });
        ColorField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getStatus()).contains(value)){
                    return true;
                }else return false;
            });
        });
        TypeField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getType()).contains(value)){
                    return true;
                }else return false;
            });
        });
        CharField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getCharacter()).contains(value)){
                    return true;
                }else return false;
            });
        });
        CaveField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getCave().getEmployeesCount()).contains(value)){
                    return true;
                }else return false;
            });
        });
        userField.textProperty().addListener((observable, oldValue, newValue) ->{
            workerData.setPredicate(worker -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(worker.getUserName()).contains(value)){
                    return true;
                }else return false;
            });
        });
    }
    public void setUpdateRQ(Boolean updateRQ){
        this.updateRQ = updateRQ;
    }

    public boolean getUpdateRQ(){
        return updateRQ;
    }

    public void setupFilterTable(){
        updateTable();
        FilteredList<Worker> filteredData = new FilteredList<>(workerData, p -> true);
        createListeners(filteredData);
        SortedList<Worker> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(dbTable.comparatorProperty());

        dbTable.setItems(sortedList);
    }

    private void filterIdColumn(ObservableList<Worker> WorkerData){
        boolean firstFilter = false;
        List<Worker> addList = new ArrayList<>();
    }

}