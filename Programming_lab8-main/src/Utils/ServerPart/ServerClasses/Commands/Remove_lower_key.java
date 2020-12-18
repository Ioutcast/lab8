package Utils.ServerPart.ServerClasses.Commands;

import Worker.Worker;
import Utils.ServerPart.DataBaseManager;

import java.util.Map;

public  class Remove_lower_key extends AbstractCommand{
    public Remove_lower_key() {
        command = "remove_lower_key";
        TextInfo = " {key}: удалить значение по ключу, если новое значение больше старого.\n (Удалаются только те экземпляры, владельцем которых являетесь Вы)";
        NeedAnStr = true;
    }


    public synchronized String execute(DataBaseManager dataBaseManager, String arg) {
        String msg = " ";



        if(!dataBaseManager.getCollection().isEmpty()) {

            int rlk = Integer.parseInt(arg);
            msg = ("suc_rld");

            for (Map.Entry<Integer, Worker> workerEntry : dataBaseManager.getCollection().entrySet()) {

                String userName = workerEntry.getValue().getUserName().trim();

                if ((workerEntry.getKey() < rlk) && userName.equals(user)) {
                    dataBaseManager.getCollection().remove(workerEntry.getValue().getId());
                    dataBaseManager.removeFromDataBase(workerEntry.getValue().getId(), user);
                }
            }
        }else{
            msg = ("collection_empty");
        }
        return msg;
    }



}

