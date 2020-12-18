package Utils.ServerPart.ServerClasses.Commands;

import Worker.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.Map;

public class Insert extends AbstractCommand {

    public Insert(){
        command = "insert";
        TextInfo = "{key} : добавить новый элемент с заданным ключом";


        NeedAnStr = true;
        NeedAnObject = true;
    }

    public Insert(String username){
        user = username;

        command = "insert";
        TextInfo = "{key} : добавить новый элемент с заданным ключом";


        NeedAnStr = true;
        NeedAnObject = true;
    }


    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {

        Integer id = getWorker().getId();

        for (Map.Entry<Integer, Worker> workerEntry : dataBaseManager.getCollection().entrySet()) {
            if (workerEntry.getKey().equals(id)) {
                String str = "err_insert";
                return str;

            }
        }

            dataBaseManager.getCollection().put(id, worker);
            dataBaseManager.addToDataBase(worker);



            return "suc_insert";

    }
}
