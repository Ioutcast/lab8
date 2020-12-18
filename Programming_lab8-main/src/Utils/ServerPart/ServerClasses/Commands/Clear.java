package Utils.ServerPart.ServerClasses.Commands;

import Worker.Worker;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.Map;

public class Clear extends AbstractCommand {
    public Clear() {
        command = "clear";
        TextInfo = ": Удаляет содержимое коллекции";

        NeedAnStr = false;
    }


    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {

        for (Map.Entry<Integer, Worker> workerEntry : dataBaseManager.getCollection().entrySet()) {
            String UserName = workerEntry.getValue().getUserName().trim();
            if(user.equals(UserName)){
                dataBaseManager.removeFromDataBase(workerEntry.getValue().getId(), user);
            }

        }


        return "suc_clear";
    }
}
