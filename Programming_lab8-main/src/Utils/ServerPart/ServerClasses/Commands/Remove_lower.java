package Utils.ServerPart.ServerClasses.Commands;

import Worker.Worker;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.Map;

public class Remove_lower extends AbstractCommand{


    public Remove_lower() {
        command = "remove_lower";
        TextInfo = "{element} : удалить из коллекции все элементы, меньшие, чем заданный.\n (Удалаются только те экземпляры, владельцем которых являетесь Вы)";


        NeedAnStr = false;
        NeedAnObject = true;
    }



    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException {
        if(dataBaseManager.getCollection().isEmpty()) return "collection_empty";
        //System.out.println("Введите данные Группы. Все группы, которые меньше вашей(исходя из логики сравнения), будут удалены.");


        for (Map.Entry<Integer, Worker> workerEntry : dataBaseManager.getCollection().entrySet()) {
            String UserName = workerEntry.getValue().getUserName().trim();
            if(workerEntry.getValue().compareTo(worker) > 0 && user.equals(UserName)){
                dataBaseManager.removeFromDataBase(workerEntry.getValue().getId(), user);
            }

        }


        return "rl_suc";
    }


}
