package Utils.ServerPart.ServerClasses.Commands;

import Worker.Worker;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.*;

public class Min_by_name extends AbstractCommand{
    public Min_by_name() {
        command = "min_by_name";
        TextInfo = ": Вывести объект, минимального по имени";
        NeedAnStr = false;
    }


    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {

        String msg = "";
        if(!dataBaseManager.getCollection().isEmpty()) {




            Comparator<String> stringComparator = new Comparator<String>() {
                @Override
                public int compare(String s, String t1) {
                    return t1.compareTo(s);
                }
            };
            List<String> strings = new ArrayList<>();
            for (Map.Entry<Integer, Worker> workerEntry : dataBaseManager.getCollection().entrySet()) {
                String userName = workerEntry.getValue().getUserName().trim();
                if(user.equals(userName)) {
                    strings.add(workerEntry.getValue().getName());
                }
            }
            Collections.sort(strings, stringComparator);
            msg = ("Вывод имен в порядке возрастания: \n");
            for (String string : strings) {
                msg = msg + string + "\n";
            }

            return msg;
        }else{
            return ("collection_empty");
        }

    }
}
