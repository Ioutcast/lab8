package Utils.ServerPart;

import Worker.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.ServerClasses.CommandManager;
import Utils.ServerPart.ServerClasses.Commands.AbstractCommand;
import Utils.ServerPart.ServerClasses.SpecialCommands.Login;
import Utils.ServerPart.ServerClasses.SpecialCommands.Register;
import Utils.OtherUtils.Serialization;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;

public class ServerHelper extends Thread implements ColorText{

    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private DataBaseManager dataBaseManager;
    private ByteBuffer byteBuffer;
    private static HashSet<String> User_list = new HashSet<>();
    private CommandManager commandManager;


    Object o = null;


    public ServerHelper(DatagramChannel datagramChannel, SocketAddress socketAddress,
                        DataBaseManager dataBaseManager, ByteBuffer byteBuffer) throws TransformerException, ParserConfigurationException {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
        this.dataBaseManager = dataBaseManager;
        this.byteBuffer = byteBuffer;
        commandManager = new CommandManager(dataBaseManager);
    }



    @Override
    public void run(){


        //Расшифровка ссобщения
        o = new Serialization().DeserializeObject(byteBuffer.array());
            String s;

        try {
            s = o.toString();
        }catch (NullPointerException e){
            s = "";
        }


        commandManager.setServerDatagramChannel(datagramChannel);
        commandManager.setSocketAddress(socketAddress);

        if(s != "" && !s.equals("show")) {
            System.out.println("Полученно [" + s + "] от " + socketAddress + " в " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)));
        }


        if(o.getClass().getName().contains(".CheckClassDB")){
           if (dataBaseManager.CheckDB()){
               try {
                   datagramChannel.send(ByteBuffer.wrap("DB_available".getBytes()), socketAddress);
                   return;
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }else{
               try {
                   datagramChannel.send(ByteBuffer.wrap("DB_is_not_available".getBytes()), socketAddress);
                   return;
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           return;
        }

        if(o.getClass().getName().contains(".Login")){
            Login register = (Login) o;
            String login = register.getUserName();
            String pass = register.getPassword();


                try {
                    boolean check = dataBaseManager.login(login, pass);
                    if(check) {
                        datagramChannel.send(ByteBuffer.wrap("suc_login".getBytes()), socketAddress);
                        User_list.add(register.getUserName());
                    }else{
                        datagramChannel.send(ByteBuffer.wrap("err_login".getBytes()), socketAddress);
                }}catch (IOException | SQLException e){
                    try {
                        datagramChannel.send(ByteBuffer.wrap("DB_is_not_available".getBytes()), socketAddress);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace(); }
                return;
        }




        //Регистрация пользователя
        if(o.getClass().getName().contains(".Register")){
            Register register = (Register) o;
            String login = register.getUserName();
            String pass = register.getPassword();

            boolean check = dataBaseManager.AddUser(login, pass);

            if (check){
                try {
                    datagramChannel.send(ByteBuffer.wrap(("suc_register").getBytes()), socketAddress);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    datagramChannel.send(ByteBuffer.wrap(("err_register").getBytes()), socketAddress);
                } catch (IOException e) {
                    try {
                        datagramChannel.send(ByteBuffer.wrap("DB_is_not_available".getBytes()), socketAddress);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
                return;
        }

        //Если команда не сущестует
        if (!commandManager.Check(s)) {
            try {
                datagramChannel.send(ByteBuffer.wrap(("Error").getBytes()), socketAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        //Если команды нет
        if (o == null){
            try {
                datagramChannel.send(ByteBuffer.wrap(("Error").getBytes()), socketAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }



        if (commandManager.Check(s)){
            try {
                socketAddress = datagramChannel.receive(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            AbstractCommand command = null;
            if (socketAddress != null) {
                command = (AbstractCommand) o;
                dataBaseManager.setUSER(command.getUserName());
                //System.out.println("Получено [" + o + "] от " + ColorText.Text(dataBaseManager.getUSER(), Color.YELLOW) +" " + socketAddress);
            }

            command = (AbstractCommand) o;



            //выполнение команды и отправка результата
            if(!command.getCommand().equals("exit")){
                try {
                    String str;
                    CommandResult commandResult = new CommandResult(commandManager, datagramChannel, dataBaseManager);
                    commandManager.printToClient(str = commandResult.sendResult(command));
                    System.out.println(str);
                } catch (IOException | ParserConfigurationException | TransformerException | InvalidCountOfArgumentException e) {
                    e.printStackTrace();
                }
            }else{
                User_list.remove(command.getUserName());
            }
        }
    }

    private boolean User_authorized(String string){
        if (User_list.contains(string)) return true;
        else return false;
    }



}
