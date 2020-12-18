package bundles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ListResourceBundle;

public class ClassLanguage_ro_RO extends ListResourceBundle {

    private static final Object [] [] contents = {

            {"lan", "ro"},//*
            {"country", "RO"},//*


            // Înregistrare / Autorizare
            {"info_button", "Parola trebuie să conțină cel puțin 4 caractere \n (sunt permise doar litere majuscule și numere engleze)"},
            {"cancel_button", "Cancel"},
            {"write_userName", "Username"},
            {"write_userPass", "Parolă"},
            {"Reg_label", "Vino cu un nume de utilizator și o parolă"},

            // Bloc autorizare.fxml:
            {"selectLanguage", "Select language"},
            {"registrationLogin", "Nu aveți cont?"},
            {"loginBut", "Login"},
            {"regBut", "Faceți!"},
            {"Autor", "Autorizare"}, // **
            //(plus)
            {"DB_available", "Baza de date disponibilă"},
            {"DB_is_not_available", "Baza de date nu este disponibilă"},
            {"server_is_not_available", "Serverul nu este disponibil"},




            {"hello", "bună!"},
            {"windowClose", "Window mesaj închis"},
            {"err_login", "Nume de utilizator sau parolă nevalide! \n Încercați din nou"},
            {"suc_login", "V-ați autentificat cu succes!"},
            {"suc_register", "Ești înregistrat! \n Încercați să vă autentificați."},
            {"err_register", "Un utilizator cu acest pseudonim a fost deja creat. \n Încercați din nou!"},
            {"Entry", "Intrare"},
            {"Info", "Informații"},
            {"update", "Actualizare"},
            {"move", "misca"},
            {"moveWorker", "Move Worker"},
            {"WriteId", "Enter ID"},


            {"uPick", "Ai ales"},
            {"WorkerCreatedByUser", "Owner:"},
            {"with", "cu"},
            {"age", "Age:"},
            {"Dname", "\nNume: '"},
            {"cooridanateX", ", \nCoordonate {x ="},
            {"Dtype", "}, \nTip:"},
            {"WorkerColor", "Worker Color:"},
            {"Dchar", ", \nCaracter:"},
            {"Dcave", ", \nAdâncimea peșterii:"},
            {"CreationTime", "\nUltima actualizare:"},


            // bloc arg.fxml:
            {"InputData", "Date de intrare"},
            // {"count_less_number", "Enter"},
            {"input_id", "Enter ID"},
            {"delete_id", "Șterge obiect"},
            {"input_depth", "Introduceți adâncimea peșterii."},
            {"add", "Add object"},



            //Блок WorkerDialog.fxml:
            {"parameters_worker", "Introduceți parametrii Worker"},




            //Блок команд:
            {"suc_help", "remove_greater_key: eliminați toate articolele din colecția a căror cheie o depășește pe cea specificată. \n" +
                    "Clear: Ștergeți colecția (elimină articolele pe care le dețineți din colecție). \n" +
                    "show: Tipăriți toate elementele colecției în reprezentarea șirurilor la ieșirea standard. \n" +
                    "Adaugă worker: Adăugați un element nou cu cheia dată. \n" +
                    "actualizare: actualizați valoarea articolului de colecție al cărui ID este egal cu cel dat. \n" +
                    "remove_greater: Eliminați toate articolele din colecție care sunt mai mari decât cea specificată. \n" +
                    "remove_lower: eliminați toate elementele din colecție care nu depășesc cea specificată. \n" +
                    "remove_lower_key: eliminați toate articolele din colecție mai puțin decât tasta dată. \n" +
                    "Afișați workerul după personaj: Tipăriți elementele sortate după caracter. \n" +
                    "Workers Print by Type: Tipăriți elementele sortate după tip. \n" +
                    "exit: Ieșiți din program fără să salvați. \n" +
                    "help: Afișează ajutor pentru comenzile disponibile. \n" +
                    "min_by_name: Afișează obiectul, minim după nume. \n" +
                    "remove_key: eliminați un articol din colecție cu ID-ul său. \n" +
                    "info: Tipărește informații despre colecția în fluxul standard."},

            {"suc_info", "Informații:"},
            {"desc_char", "Elemente după caracter în ordine descrescătoare:"},
            {"desc_type", "Elemente după tip în ordine descrescătoare:"},

            {"collection_empty", "Colecția este goală!"},
            {"suc_insert", "Caractere adăugate cu succes!"},
            {"err_insert", "Personajul există deja cu cheia dată."},
            {"suc_update", "Caracterul a fost actualizat cu succes cu ID:"},
            {"err_update", "Personajul nu vă aparține cu ID:"},
            {"err_update", "Acest personaj nu se află în colecția ta"},
            {"suc_clear", "Colecția a fost ștersă cu succes! \nToate articolele care vă aparțin au fost eliminate!"},
            {"suc_remove", "Caracterul eliminat cu ID:"},
            {"err_remove", "Caracterul nu a fost găsit sau nu vă aparține cu ID:"},
            {"err_greater", "Personajul există deja cu ID-ul dat."},
            {"error_greater", "Nu există caractere din colecție care să depășească ID-ul:"},
            {"suc_greater", "Caracterele care vă aparțin au fost eliminate cu un ID mai mare:"},
            {"HMColl", "Număr de articole din colecție"}, // *
            {"suc_rlk", "Obiectele de sub cheia specificată au fost șterse"},
            {"suc_rg", "Obiectele mai mari decât cele specificate au fost șterse"},
            {"suc_rl", "Obiectele de sub cel specificat au fost șterse"},
            {"suc_script", "Script executat"},



            //Блок Ошибок:
            {"Error", "Eroare"},
            {"error_arg", "Comanda nu a fost furnizată cu un argument sau a fost în format greșit!"},
            {"InvalidData", "Date de comandă nevalide!"},
            {"IncorrectField", "Câmpuri completate incorect!"},
            {"IncorrectNumber", "Număr în afara domeniului!"},
            {"IncorrectDataField", "Câmp complet incorect!"},
            {"ErrorCreatingObject", "Eroare la crearea obiectului!"},
            {"UnexpectedException", "Excepție neașteptată!"},
            {"incorrectLogPas", "Logare sau parolă incorecte!"},
            {"uncorrectLogPas", "Vă rugăm să introduceți un alt nume de utilizator și o parolă!"},
            {"idNotFound", "Caracterul cu ID-ul dat nu a fost găsit!"},
            {"error_argDepth", "Format de introducere nevalid! \n (numărul trebuie să fie mai mare sau egal cu zero)"},
            {"DB_NotAvailable", "Baza de date este temporar indisponibilă. Ne pare rău pentru inconveniente."}, // Adăugat
            {"dr_err", "Eroare obiect"},
            {"Wrong_input", "Intrare greșită de date"}, // *
            {"lessThen4", "Parola trebuie să aibă cel puțin 5 caractere"},
            {"withSpaces", "Parola nu trebuie să includă spații"},
            {"invalidChars", "Parola trebuie să conțină doar litere și numere în limba engleză"},

            //Worker Constructor
            {"emptyName", "Nume gol"},
            {"InvalidAge", "Vârsta trebuie să fie un număr întreg negativ"},
            {"InvalidX", "Coordonata (X) trebuie să fie un număr întreg"},
            {"InvalidY", "Coordonata (Y) trebuie să fie un număr întreg"},
            {"emptyColor", "Trebuie să setați culoarea (Color)"},
            {"emptyChar", "Trebuie să setați un caracter"},
            {"emptyType", "Trebuie să setați tipul (Type)"},
            {"InvalidCave", "Peștera trebuie să fie un număr non-negativ."},




            //Блок main.fxml:
            {"file", "File"},
            {"exit", "Exit"},

            {"help", "ajutor"},
            {"info", "Informații despre colecție"},
            {"helpCom", "Informații despre comandă"},

            {"language", "Select language"},

            {"username", "User:"},
            {"updateTable", "Actualizare valori tabel"},

            {"addCommands", "Add Commands:"},
            {"insertCom", "Adaugă worker"},

            {"removeCommands", "Remove Command:"},
            {"removeID", "Remove by ID"},
            {"removeLower", "Eliminare workeri mai mici"},
            {"removeGreater", "Eliminare workeri mari"},
            {"remove_lower_key", "Eliminare ID-uri inferioare"}, // *
            {"clearCom", "Clear"},


            {"updateCommands", "Update Command:"},
            {"updateCom", "Actualizare"},

            {"printCommands", "Comenzi de ieșire:"},
            {"printDescendingType", "Workers Print by Type"},
            {"printDescendingChar", "Afișați Workerul după personaj"},
            {"script", "Script"}, // *

            {"tabWorker", "Masă Worker"},
            {"tabVisual", "Zona de vizualizare"},
            {"", ""},


            //script
            {"worker", "Worker"},
            {"param", "Parametru"},
            {"command", "Command"},
            {"user", "User"},
            {"commandManager", "Command Management"},
            {"delete", "Șterge"},
            {"execute", "execute"},



    };

    public String getDate(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return localDateTime.format(formatter);
    }

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
