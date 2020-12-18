package Utils.ServerPart.ServerClasses.Commands;

import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Класс для вывода информации о коллекции
 */

public class PrintInfoClass extends AbstractCommand {

    public PrintInfoClass(){
        command = "info";
        TextInfo = ": вывести в стандартный поток вывода информацию о коллекции";
        NeedAnStr = false;
    }

    protected String printInfo(LinkedHashMap collection){
        String type = "Тип коллекции: " + collection.getClass().getSimpleName();
        String date = ("Дата инициализации: " + collection.values().toString());
        String size = ("Количество элементов: " + collection.size());

        return collection.size() + "";
    }


    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {
        return printInfo(dataBaseManager.getCollection());
    }
}
