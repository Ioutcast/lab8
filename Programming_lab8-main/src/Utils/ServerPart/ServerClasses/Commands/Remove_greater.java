package Utils.ServerPart.ServerClasses.Commands;

import Worker.Worker;
import Utils.ServerPart.DataBaseManager;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Remove_greater extends AbstractCommand {
    public Remove_greater() {
        command = "remove_greater";
        TextInfo = " {element} : удалить из коллекции все элементы, превышающие заданный.\n (Удалаются только те экземпляры, владельцем которых являетесь Вы)";

        NeedAnStr = false;
        NeedAnObject = true;
    }


    public String execute(DataBaseManager dataBaseManager, String arg) {


        if (dataBaseManager.getCollection().isEmpty()) return "collection_empty";
        dataBaseManager.getCollection().entrySet().removeIf(entry -> entry.getValue().compareTo(worker) < 0 && entry.getValue().getUserName().equals(dataBaseManager.getUSER()));


        for (Map.Entry<Integer, Worker> workerEntry : dataBaseManager.getCollection().entrySet()) {
            String UserName = workerEntry.getValue().getUserName().trim();
            if (workerEntry.getValue().compareTo(worker) < 0 && user.equals(UserName)) {
                dataBaseManager.removeFromDataBase(workerEntry.getValue().getId(), user);
            }

        }

        return "rg_suc";
//        String s = "";
//        if (!dataBaseManager.getCollection().isEmpty()) {
//            Set<Map.Entry<Integer, Worker>> setOfEntries = dataBaseManager.getCollection().entrySet();
//            Iterator<Map.Entry<Integer, Worker>> iterator = setOfEntries.iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<Integer, Worker> entry = iterator.next();
//                Integer value = entry.getKey();
//                if (value.compareTo(Integer.valueOf(arg)) > 0) {
//                    s = "removeing : " + entry;
//                    iterator.remove();
//                }
//            }
//        } else return "Размер коллекции == 0";
//        return s;
    }
}
