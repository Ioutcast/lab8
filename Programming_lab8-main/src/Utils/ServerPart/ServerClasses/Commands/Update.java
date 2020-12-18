package Utils.ServerPart.ServerClasses.Commands;

import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public  class Update extends AbstractCommand {

    public Update() throws TransformerException, ParserConfigurationException {

        command = "update_id";
        TextInfo = "{id} : обновить значение элемента коллекции, id которого равен заданному";
        NeedAnStr = true;

        NeedAnObject = true;
    }

    public Update(String username) throws TransformerException, ParserConfigurationException {
        user = username;

        command = "update_id";
        TextInfo = "{id} : обновить значение элемента коллекции, id которого равен заданному";
        NeedAnStr = true;

        NeedAnObject = true;
    }


    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {

        if (worker.getName().equals(null)) return "err_update";



        if (dataBaseManager.updateElementInDataBase1(worker, user)) {
            return "suc_update";
        } else {
            return ("err_update");

        }
    }
}