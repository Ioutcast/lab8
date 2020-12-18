package Worker;

import javafx.beans.property.SimpleStringProperty;

public enum OrganizationType {
    COMMERCIAL,
    PUBLIC,
    PRIVATE_LIMITED_COMPANY,
    OPEN_JOINT_STOCK_COMPANY,
    UNKNOWN;



    public static OrganizationType byOrdinal(String s){
        try {
            OrganizationType dragonCharacter = OrganizationType.valueOf(s);
            return dragonCharacter;

        }catch (IllegalArgumentException e){
            return OrganizationType.UNKNOWN;
        }
    }

    public SimpleStringProperty getCharProperty(OrganizationType character){
        String str = character.toString();
        return new SimpleStringProperty(str);
    }
}
/**
 *UNKNOWN используется в случае неправильного ввода всех остлаьных enum-ов
 */