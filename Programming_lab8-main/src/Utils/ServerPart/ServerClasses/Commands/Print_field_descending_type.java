package Utils.ServerPart.ServerClasses.Commands;

import Worker.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.*;

public class Print_field_descending_type extends AbstractCommand {
    public Print_field_descending_type() {
        command = "print_field_descending_type";
        TextInfo = ": Вывести коллекцию, сортируя по типу";
        NeedAnStr = false;
    }



    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {
        String msg = "";
        if (dataBaseManager.getCollection().isEmpty())return "collection_empty";
        Comparator<Location> workerTypeComparator = new Comparator<Location>() {
            public int compare(Location d1, Location d2) {
                return d1.compareTo(d2);
            }
        };
        Comparator<Location> workerTypeComparator1 = new Comparator<Location>() {
            public int compare(Location d1, Location d2) {
                return d1.compareTo(d2);
            }
        };
        List<Location> locations = new ArrayList<>();
        for (Map.Entry<Integer, Worker> workerEntry : dataBaseManager.getCollection().entrySet()) {
            if(workerEntry.getValue().getUserName().equals(dataBaseManager.getUSER()))
                locations.add(workerEntry.getValue().getType());

        }
        Collections.sort(locations, workerTypeComparator);
                for (Location location : locations) {
            msg = msg + (location) + " \n";
        }

        return msg;
    }
}
