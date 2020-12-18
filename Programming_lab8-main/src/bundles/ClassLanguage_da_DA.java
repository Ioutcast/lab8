package bundles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ListResourceBundle;

public class ClassLanguage_da_DA extends ListResourceBundle {

    private static final Object[][] contents = {


            {"lan", "da"},//*
            {"country", "DA"},//*


            //Регистрация/Авторизация
            {"info_button", "Adgangskode skal indeholde mindst 4 tegn \n (kun engelske store bogstaver og tal er tilladt)"},
            {"cancel_button", "Annuller"},
            {"write_userName", "brugernavn"},
            {"write_userPass", "Password"},
            {"Reg_label", "Kom med et brugernavn og adgangskode"},


            //Блок authorization.fxml:
            {"selectLanguage", "Select language"},
            {"registrationLogin", "Har du ikke en konto?"},
            {"loginBut", "Login"},
            {"regBut", "Gør det!"},
            {"Autor", "Autorisation"}, // **

            //(дополнение)
            {"DB_available", "Database tilgængelig"},
            {"DB_is_not_available", "Database ikke tilgængelig"},
            {"server_is_not_available", "Server ikke tilgængelig"},




            {"hello", "hej!"},
            {"windowClose", "Message vindue lukket"},
            {"err_login", "Ugyldigt brugernavn eller adgangskode! \n Prøv igen"},
            {"suc_login", "Du har logget ind!"},
            {"suc_register", "Du er registreret! \nPrøv at logge ind."},
            {"err_register", "En bruger med dette kaldenavn er allerede oprettet. \n Prøv igen!"},
            {"Entry", "Indgang"},
            {"Info", "Information"},
            {"update", "Opdatering"},
            {"move", "Flyt"},
            {"moveWorker", "Move Worker"},
            {"WriteId", "Indtast ID"},

            {"uPick", "Du har valgt"},
            {"WorkerCreatedByUser", "Ejer:"},
            {"with", "med"},
            {"age", "Alder:"},
            {"Dname", "\nName: '"},
            {"cooridanateX", ", \nKoordinater {x ="},
            {"Dtype", "}, \nType:"},
            {"WorkerColor", "Worker Color:"},
            {"Dchar", ", \nTegn:"},
            {"Dcave", ", \nGrottedybde:"},
            {"CreationTime", "\nSidst opdateret:"},

            //Блок arg.fxml:
            {"InputData", "Input Data"},
            // {"count_less_number", "Enter"},
            {"input_id", "Enter ID"},
            {"delete_id", "Delete object"},
            {"input_depth", "Angiv hulens dybde."},
            {"add", "Tilføj objekt"},


            //Блок workerDialog.fxml:
            {"parameters_worker", "Enter Worker parameters"},




            //Блок команд:
            {"suc_help", "remove_gesser_key: Fjern alle elementer fra samlingen, hvis nøgle overstiger den angivne. \n" +
                    "clear: Ryd samlingen (fjerner de ting, du ejer fra samlingen). \n" +
                    "show: Udskriv alle elementer i samlingen i strengrepræsentation til standardoutput. \n" +
                    "indsæt: Tilføj et nyt element med den givne nøgle. \n" +
                    "opdatering: Opdater værdien af ​​det samleobjekt, hvis id er lig med det givne. \n" +
                    "remove_geaker: Fjern alle elementer fra samlingen, der er større end den angivne. \n" +
                    "remove_lower: Fjern alle elementer fra samlingen, der ikke overstiger den angivne. \n" +
                    "remove_lower_key: Fjern alle elementer fra samlingen mindre end den givne nøgle. \n" +
                    "print_field_descending_character: Udskriv poster sorteret efter karakter. \n" +
                    "print_field_descending_type: Udskriv poster sorteret efter type. \n" +
                    "exit: Afslut programmet uden at gemme. \n" +
                    "help: Viser hjælp til de tilgængelige kommandoer. \n" +
                    "min_by_name: Vis objektet, minimum efter navn. \n" +
                    "remove_key: Fjern et element fra samlingen ved dens id. \n" +
                    "info: Udskriver oplysninger om samlingen til standardstrøm."},
            {"suc_info", "Information:"},
            {"desc_char", "Elementer efter karakter i faldende rækkefølge:"},
            {"desc_type", "Elementer efter type i faldende rækkefølge:"},

            {"collection_empty", "Samlingen er tom!"},
            {"suc_insert", "Tegn er tilføjet!"},
            {"err_insert", "Tegnet findes allerede med den givne nøgle."},
            {"suc_update", "Tegn er blevet opdateret med ID:"},
            {"err_update", "Karakteren tilhører ikke dig med ID:"},
            {"err_update", "Denne karakter er ikke i din samling"},
            {"suc_clear", "Samlingen er blevet ryddet! \nAlle elementer, der tilhører dig, er blevet fjernet!"},
            {"suc_remove", "Tegn fjernet med ID:"},
            {"err_remove", "Tegn ikke fundet eller ikke hører til dig med ID:"},
            {"err_greater", "Tegnet findes allerede med det givne ID."},
            {"error_greater", "Der er ingen tegn i samlingen, der overskrider ID:"},
            {"suc_greater", "Tegn, der tilhører dig, er blevet fjernet med et højere ID:"},
            {"HMColl", "Antal varer i samlingen"}, // *
            {"suc_rlk", "Objekter under den specificerede nøgle er blevet slettet"},
            {"suc_rg", "Objekter højere end den specificerede er blevet slettet"},
            {"suc_rl", "Objekter under den specificerede er blevet slettet"},
            {"suc_script", "Script udført"},




            //Блок Ошибок:
            {"Error", "Fejl"},
            {"error_arg", "Kommandoen blev ikke leveret med et argument eller var i forkert format!"},
            {"InvalidData", "Ugyldige kommandodata!"},
            {"IncorrectField", "Forkert udfyldte felter!"},
            {"IncorrectNumber", "Antal uden for rækkevidde!"},
            {"IncorrectDataField", "Forkert udfyldt felt!"},
            {"ErrorCreatingObject", "Fejl ved oprettelse af objekt!"},
            {"UnexpectedException", "Unexpected exception!"},
            {"incorrectLogPas", "Forkert login eller adgangskode!"},
            {"uncorrectLogPas", "Indtast et andet brugernavn og adgangskode!"},
            {"idNotFound", "Karakter med det givne ID blev ikke fundet!"},
            {"error_argDepth", "Ugyldigt inputformat! \n (Nummeret skal være større end eller lig med nul)"},
            {"DB_NotAvailable", "Databasen er midlertidigt utilgængelig. Beklager ulejligheden."}, // Tilføjet
            {"dr_err", "Objektfejl"},
            {"Wrong_input", "Forkert indtastning af data"}, // *
            {"lessThen4", "Adgangskode skal være mindst 5 tegn"},
            {"withSpaces", "Adgangskoden må ikke indeholde mellemrum"},
            {"invalidChars", "Password skal kun indeholde engelske bogstaver og tal"},

            //Worker Constructor
            {"emptyName", "Tomt navn"},
            {"InvalidAge", "Alder skal være et ikke-negativt heltal"},
            {"InvalidX", "Koordinat (X) skal være et heltal"},
            {"InvalidY", "Koordinat (Y) skal være et heltal"},
            {"emptyColor", "Du skal indstille farven (farve)"},
            {"tomChar", "Du skal indstille et tegn"},
            {"TomType", "Du skal indstille typen (Type)"},
            {"InvalidCave", "Hulen skal være et ikke-negativt tal."},




            //Блок main.fxml:
            {"file", "Fil"},
            {"exit", "Exit"},

            {"help", "Hjælp"},
            {"info", "Collection Information"},
            {"helpCom", "Command Information"},

            {"language", "Vælg sprog"},

            {"username", "Bruger:"},
            {"updateTable", "Update tabel Values"},

            {"addCommands", "Tilføj kommandoer:"},
            {"insertCom", "Tilføj Worker"},

            {"removeCommands", "Remove Commands:"},
            {"removeID", "Fjern med ID"},
            {"removeLower", "Fjern mindre Workers"},
            {"removeGreater", "Fjern Large Workers"},
            {"remove_lower_key", "Fjern lavere id'er"}, // *
            {"clearCom", "Clear"},

            {"updateCommands", "Update Commands:"},
            {"updateCom", "Update"},

            {"printCommands", "Output Commands:"},
            {"printDescendingType", "Print Workers by Type"},
            {"printDescendingChar", "Vis dragen efter karakter"},
            {"script", "Script"}, // *

            {"tabWorker", "Worker Table"},
            {"tabVisual", "Visualiseringsområde"},
            {"",""},

            //script
            {"worker", "Worker"},
            {"param", "Parameter"},
            {"command", "kommando"},
            {"user", "Bruger"},
            {"commandManager", "Command Management"},
            {"delete", "Slet"},
            {"execute", "udføre"},


    };

    public String getDate(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }


    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
