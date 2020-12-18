package Utils.ServerPart.ServerClasses.SpecialCommands;


public class Login extends Authorization {



    public Login(String userName, String password){
        super(userName, password);
        command = "login";
        TextInfo = " : Вход зарегистрированного пользователя";

    }

    public Login(){
        super(null, null);
        command = "login";
        TextInfo = " : Вход зарегистрированного пользователя";

    }



}
