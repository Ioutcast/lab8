package Utils.ServerPart.ServerClasses.Commands;

import Worker.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public abstract class AbstractCommand implements Serializable, ColorText {

    protected boolean NeedAnObject = false; //Необходимость объекта worker для команды.
    protected String command;// Название команды
    protected String TextInfo;// Описание команды
    protected boolean NeedAnStr = false;//Необходимость аргумента для команды.
    protected Worker worker = new Worker();
    protected String string = "";

    protected String user;




    public String getUserName() {
        return user;
    }

    public void setUserName(String userName) {
        user = userName;
    }

    //Getters and Setters
    public void setWorker(Worker worker){
        this.worker = worker;
    }

    public void setString(String string){
        this.string = string;
    }

    public Worker getWorker(){
        return worker;
    }

    public boolean isNeedAnStr() {
        return NeedAnStr;
    }


    public String getCommand() {
        return command;
    }


    public String execute(){
        return "AC Такая команда не существует";
    }



    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException{
        return execute();
    }


    //Запрос на принятие объекта
    public boolean getObjectExecute(){
        return NeedAnObject;
    }

    public String getString(){
        return string;
    }

    public String toPrint() {
        return command + " " + TextInfo;
    }





    @Override
    public String toString() {
        return command;
    }

    protected static LinkedHashMap<Integer, Worker> SortCollection(LinkedHashMap<Integer, Worker> collection ) {
        Comparator<Worker> workerComparator = new Comparator<Worker>() {
            @Override
            public int compare(Worker s, Worker s1) {
                return s.getName().compareTo(s1.getName());
            }
        };
        List<Worker> workers = new ArrayList<>();
        for (Map.Entry<Integer, Worker> workerEntry : collection.entrySet()) {
            workers.add(workerEntry.getValue());
        }
        LinkedHashMap<Integer, Worker> linkedHashMap = new LinkedHashMap<Integer, Worker>();
        Collections.sort(workers, workerComparator);
        for(Worker worker : workers){
            linkedHashMap.put(worker.getId(), worker);
        }
        collection = linkedHashMap;

        return collection;
    }


}
