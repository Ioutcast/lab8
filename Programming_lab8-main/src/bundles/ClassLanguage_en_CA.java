package bundles;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ListResourceBundle;
import java.util.Locale;

public class ClassLanguage_en_CA extends ListResourceBundle {
    private static final Object[][] contents = {

            {"lan", "en"},//*
            {"country", "CA"},//*


            //Регистрация/Авторизация
            {"info_button", "Password must be at least 4 characters\n" +
                    "(only English capital letters and numbers are allowed)"},
            {"cancel_button", "cancel"},
            {"write_userName", "Username"},
            {"write_userPass", "Password"},
            {"Reg_label", "Write a username and password"},

            //Блок authorization.fxml:
            {"selectLanguage", "Select language"},
            {"registrationLogin", "Don't have an account?"},
            {"loginBut", "to come in"},
            {"regBut", "Make it!"},
            {"Autor", "Authorization"}, //**
            //(дополнение)
            {"DB_available", "Database available"},
            {"DB_is_not_available", "Database not available"},
            {"server_is_not_available", "Server not available"},



            {"hello", "Hello!"},
            {"windowClose", "Message window closed"},
            {"err_login", "Invalid username or password! \nTry again"},
            {"suc_login", "You have successfully logged in!"},
            {"suc_register", "You are registered! \nTry to login."},
            {"err_register", "A user with this nickname has already been created. \nTry again!"},
            {"Entry", "Entry"},
            {"Info", "Information"},
            {"update", "Update"},
            {"move", "Move"},
            {"moveWorker", "Move Worker"},
            {"WriteId", "Enter ID"},

            {"uPick", "You have chosen"},
            {"WorkerCreatedByUser", "Owner:"},
            {"with", "with"},
            {"age", "Age:"},
            {"Dname", "\nName: '"},
            {"cooridanateX", ", \nCoordinates {x ="},
            {"Dtype", "}, \nType:"},
            {"WorkerColor", "Worker Color:"},
            {"Dchar", ", \ntype:"},
            {"Dcave", ", \norg type:"},
            {"CreationTime", "\nLast updated:"},

            // arg.fxml block:
            {"InputData", "Input Data"},
            {"input_id", "Enter ID"},
            {"delete_id", "Delete object"},
            {"input_depth", "Enter the depth of the cave."},
            {"add", "Add object"},


            // Block WorkerDialog.fxml:
            {"parameters_worker", "Enter Worker parameters"},




            //Блок команд:
            {"suc_help", "remove_greater_key: Remove all items from the collection whose key exceeds the specified one. \n" +
                    "clear: Clear the collection (removes the items you own from the collection). \n" +
                    "show: Print all elements of the collection in string representation to standard output. \n" +
                    "insert: Add a new item with the given key. \n" +
                    "update: Update the value of the collection item whose id is equal to the given one. \n" +
                    "remove_greater: Remove all items from the collection that are larger than the specified one. \n" +
                    "remove_lower: Remove all elements from the collection that do not exceed the specified one. \n" +
                    "remove_lower_key: Remove all items from the collection less than the given key. \n" +
                    "print_field_descending_character: Print items sorted by character. \n" +
                    "print_field_descending_type: Print items sorted by type. \n" +
                    "exit: Exit the program without saving. \n" +
                    "help: Displays help for the available commands. \n" +
                    "min_by_name: Display the object, minimum by name. \n" +
                    "remove_key: Remove an item from the collection by its id. \n" +
                    "info: Prints information about the collection to standard stream."},
            {"suc_info", "Information:"},
            {"desc_char", "Items by Character in descending order:"},
            {"desc_type", "Items by Type in descending order:"},

            {"collection_empty", "The collection is empty!"},
            {"suc_insert", "Character added successfully!"},
            {"err_insert", "The character already exists with the given key."},
            {"suc_update", "Character has been successfully updated with ID:"},
            {"err_update", "The character does not belong to you with ID:"},
            {"err_update", "This character is not in your collection"},
            {"suc_clear", "The collection has been cleared successfully! \nAll items belonging to you have been removed!"},
            {"suc_remove", "Character removed with ID:"},
            {"err_remove", "Character not found or does not belong to you with ID:"},
            {"err_greater", "The character already exists with the given ID."},
            {"error_greater", "There are no characters in the collection that exceed ID:"},
            {"suc_greater", "Characters that belong to you have been removed with a higher ID:"},
            {"HMColl", "Number of items in the collection"}, // *
            {"suc_rlk", "Objects below the specified key have been deleted"},
            {"suc_rg", "Objects higher than the specified one have been deleted"},
            {"suc_rl", "Objects below the specified one have been deleted"},
            {"suc_script", "Script executed"},





            // Error Block:
            {"Error", "Error"},
            {"error_arg", "The command was not supplied with an argument or was in the wrong format!"},
            {"InvalidData", "Invalid command data!"},
            {"IncorrectField", "Incorrectly filled fields!"},
            {"IncorrectNumber", "Number out of range!"},
            {"IncorrectDataField", "Incorrectly filled field!"},
            {"ErrorCreatingObject", "Error creating object!"},
            {"UnexpectedException", "Unexpected exception!"},
            {"incorrectLogPas", "Incorrect Login or Password!"},
            {"uncorrectLogPas", "Please enter a different username and password!"},
            {"idNotFound", "Character with the given ID was not found!"},
            {"error_argDepth", "Invalid input format! \n (The number must be greater than or equal to zero)"},
            {"DB_NotAvailable", "The database is temporarily unavailable. Sorry for the inconvenience."}, // Added
            {"dr_err", "Object error"},
            {"Wrong_input", "Wrong data input"}, // *
            {"lessThen4", "Password must be at least 5 characters"},
            {"withSpaces", "The password must not include spaces"},
            {"invalidChars", "Password must contain only English letters and numbers"},

            // Worker Constructor
            {"emptyName", "Empty name"},
            {"InvalidAge", "Age must be a non-negative integer"},
            {"InvalidX", "Coordinate (X) must be an integer"},
            {"InvalidY", "Coordinate (Y) must be an integer"},
            {"emptyColor", "You need to set the color (Color)"},
            {"emptyChar", "You need to set a Character"},
            {"emptyType", "You need to set the type (Type)"},
            {"InvalidCave", "The cave must be a non-negative number."},




            // Block main.fxml:
            {"file", "File"},
            {"exit", "Exit"},

            {"help", "Help"},
            {"info", "Collection Information"},
            {"helpCom", "Command Information"},

            {"language", "Select language"},

            {"username", "User:"},
            {"updateTable", "Update table values"},

            {"addCommands", "Add Commands:"},
            {"insertCom", "Add Worker"},

            {"removeCommands", "Remove Commands:"},
            {"removeID", "Remove by ID"},
            {"removeLower", "Remove smaller Workers"},
            {"removeGreater", "Remove Large Workers"},
            {"remove_lower_key", "Remove lower IDs"}, // *
            {"clearCom", "Clear"},

            {"updateCommands", "Update Commands:"},
            {"updateCom", "Update"},

            {"printCommands", "Output Commands:"},
            {"printDescendingType", "Print Workers by Loc"},
            {"printDescendingChar", "Display the Worker by ORGType"},
            {"script", "Script"}, // *


            {"tabWorker", "Worker Table"},
            {"tabVisual", "Visualization area"},
            {"", ""},

            // script
            {"worker", "Worker"},
            {"param", "Parameter"},
            {"command", "Command"},
            {"user", "User"},
            {"commandManager", "Command Management"},
            {"delete", "Delete"},
            {"execute", "execute"},



    };

    public String getDate(LocalDateTime localDateTime) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy", Locale.CANADA);
        return localDateTime.format(formatter);
    }


    @Override
    protected Object[][] getContents() {
        return contents;
    }
}