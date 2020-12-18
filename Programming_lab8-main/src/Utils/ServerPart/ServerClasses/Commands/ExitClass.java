package Utils.ServerPart.ServerClasses.Commands;

import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;


//Класс Exit.
//Не нужен по заданию

public class ExitClass extends AbstractCommand {

    public ExitClass(){
        command = "exit";
        TextInfo = ": Завершение работы программы";

        NeedAnStr = false;
    }


    PrintHelpClass printHelpClass;

    {
        try {
            printHelpClass = new PrintHelpClass();
        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }



    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {

        System.exit(-1);
        return null;
    }


    public String execute(){
        System.exit(-1);
        return "Завершение";
    }
}
