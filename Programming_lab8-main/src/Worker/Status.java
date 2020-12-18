package Worker;


import javafx.beans.property.SimpleStringProperty;

/**
 * Класс Color
 */
public enum Status {
    FIRED,
    HIRED,
    RECOMMENDED_FOR_PROMOTION,
    UNKNOWN;


    public static Status byOrdinal(String s){
        try {
            Status status = Status.valueOf(s);
            return status;

        }catch (IllegalArgumentException e){
            return Status.UNKNOWN;
        }
    }

    public SimpleStringProperty getColorProperty(Status status){
        String str = status.toString();
        return new SimpleStringProperty(str);
    }
}
