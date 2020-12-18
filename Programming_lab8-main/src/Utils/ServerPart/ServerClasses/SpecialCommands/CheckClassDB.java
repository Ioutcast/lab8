package Utils.ServerPart.ServerClasses.SpecialCommands;

public class CheckClassDB extends Authorization {


    public CheckClassDB(){
        super(null, null);
        command = "check";
        TextInfo = " : Проверка состояния сервера и базы данных";

    }


    public CheckClassDB(String userName, String password){
        super(userName, password);
        command = "check";
        TextInfo = " : Проверка состояния сервера и базы данных";

    }

}
