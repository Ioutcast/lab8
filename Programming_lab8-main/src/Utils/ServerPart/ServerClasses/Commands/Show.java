package Utils.ServerPart.ServerClasses.Commands;

import Worker.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.Map;

public class Show extends AbstractCommand {

    public Show(){
        command = "show";
        TextInfo = ": Показывает содержимое коллекции";
        NeedAnStr = false;
    }

    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {

        dataBaseManager.setCollection(SortCollection(dataBaseManager.getCollection()));


        String ShowCollection = "";
        String ShowCollection1 = "";
        if(!dataBaseManager.getCollection().isEmpty()){
            for(Map.Entry<Integer, Worker> entry : dataBaseManager.getCollection().entrySet()) {

                String workerUser = entry.getValue().getUserName().trim();


                if(workerUser.equals(user)) {
                    ShowCollection = ShowCollection + (entry.getValue().toString()) + "\n";
                }else{
                    ShowCollection1 = ShowCollection1 + entry.getValue().toString();
                }
                //System.out.println(entry.getKey());
            }
        }else{
            return ("coll_empty");
        }
        return (ShowCollection + ShowCollection1);



    }
}
