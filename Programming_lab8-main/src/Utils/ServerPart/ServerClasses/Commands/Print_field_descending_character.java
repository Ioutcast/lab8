package Utils.ServerPart.ServerClasses.Commands;

import Worker.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.*;

public class Print_field_descending_character extends AbstractCommand {
    public Print_field_descending_character() {
        command = "print_field_descending_character";
        TextInfo = ": Вывести коллекцию, сортируя по character.";
        NeedAnStr = false;
    }


    public String execute(DataBaseManager dataBaseManager, String arg)  {
        if(dataBaseManager.getCollection().isEmpty()) return "collection_empty";
        String msg = "";
        Comparator<OrganizationType> workerCharacterComparator = new Comparator<OrganizationType>() {
            public int compare(OrganizationType d1, OrganizationType d2) {
                return d1.compareTo(d2);
            }
        };
        List<OrganizationType> organizationTypes = new ArrayList<>();
        for (Map.Entry<Integer, Worker> workerEntry : dataBaseManager.getCollection().entrySet()) {
            if(workerEntry.getValue().getUserName().equals(dataBaseManager.getUSER()))
                organizationTypes.add(workerEntry.getValue().getCharacter());
        }
        Collections.sort(organizationTypes, workerCharacterComparator);
        for (OrganizationType organizationType : organizationTypes) {
            msg = msg + (organizationType) + " \n";
        }

        return msg;
    }
}
