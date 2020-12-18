package Worker;

import javafx.beans.property.SimpleStringProperty;

public enum Location {
    WEST,
    SOUTH,
    NORTH ,
    EAST,
    UNKNOWN;

    public static Location byOrdinal(String s){
        try {
            Location dragonType = Location.valueOf(s);
            return dragonType;

        }catch (IllegalArgumentException e){
            return Location.UNKNOWN;
        }
    }

    public SimpleStringProperty getTypeProperty(Location type){
        String str = type.toString();
        return new SimpleStringProperty(str);
    }

}
/**
 *UNKNOWN используется в случае неправильного ввода всех остлаьных enum-ов
 */