package Utils.ServerPart;

import Worker.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.ServerClasses.CommandManager;
import Utils.ServerPart.ServerClasses.Commands.AbstractCommand;
import Utils.OtherUtils.Serialization;
import Utils.ServerPart.ServerClasses.Commands.Show;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.*;

import static java.lang.Thread.sleep;


class CommandResult{

    CommandManager commandManager;
    DatagramChannel datagramChannel;
    SocketAddress socketAddress;
    DataBaseManager dataBaseManager;
    private static Set<SocketAddress> clientAddr = new HashSet();
    private static Integer hash;


    CommandResult(CommandManager commandManager, DatagramChannel datagramChannel, DataBaseManager dataBaseManager) throws TransformerException, ParserConfigurationException {
        this.commandManager = commandManager;
        this.datagramChannel = datagramChannel;
        this.socketAddress = commandManager.getSocketAddress();
        this.dataBaseManager = dataBaseManager;
    }


    /**
     * Метод на входе получает Абстракт-команду, и на выходе даёт её результат.
     *
     * @return String: результат команды
     */
     String sendResult(AbstractCommand command) throws IOException, InvalidCountOfArgumentException {

         if(hash == null){
             hash = (int)(Math.random()*1000000);
         }

         if (command.getCommand().equals("show")) {
             Show show = (Show) command;
             //System.out.println(hash + " == " + show.getString() + " => " + show.getString().equals(hash.toString()));
             if (show.getString().equals(hash.toString())) {
                 return "err_show";
             }
         }

             if(!dataBaseManager.updateCollectionFromDataBase()){
                 return "DB_is_not_available";
             }

        //Получение запроса на коллекцию от клиента
        if (command.getCommand().equals("show")) {

            if (sendCollection1()) {
                return "suc_show";
            }
            else {
                return "err_show";
            }
        }

         hash = (int)(Math.random()*1000000);

         //Команды, где не нужен объект и аргумент к нему.(Пример help, info)
            if (!command.getObjectExecute() && !command.isNeedAnStr()) {
                return command.execute(dataBaseManager, command.getString());
            }

            //Команды, где нужен только аргумент. (Пример: remove_key_lower {key})
            if (!command.getObjectExecute() && command.isNeedAnStr()) {


                if (command.getString() != null) {
                    return command.execute(dataBaseManager, command.getString());
                } else {
                    if (command.getString() == null) return "IncorrectDataField";
                }

            }

            //Команды, где нужен объект и аргумент. (Пример: remove_lower {element}})
            if (command.getObjectExecute() && !command.isNeedAnStr()) {

                if (command.getWorker() != null) {
                    return command.execute(dataBaseManager, command.getString());
                } else {
                    return "dr_err";
                }
            }

            //Команды, где нужен объект и аргумент. (Пример: insert {key} + {element}})
            if (command.getObjectExecute() && command.isNeedAnStr()) {


                if (command.getWorker() != null && command.getString() != null) {
                    return command.execute(dataBaseManager, command.getString());
                } else {
                    if (command.getWorker() == null && command.getString() == null) return "IncorrectField";
                    if (command.getWorker() == null) return "dr_err";
                    if (command.getString() == null) return "IncorrectDataField";
                }
            }

            return "UnexpectedException";
        }




    //Штука, которая посылает коллекцию клиенту
    private boolean sendCollection() throws IOException {
        try {
            int i = 0;
        for (Map.Entry<Integer, Worker> workerEntry : dataBaseManager.getCollection().entrySet()) {

            i++;
            if(i == dataBaseManager.getCollection().size()) workerEntry.getValue().setNum(-1);

            sleep(10);
            ByteBuffer byteBuffer = ByteBuffer.wrap(Objects.requireNonNull(Serialization.SerializeObject(workerEntry.getValue())));
            datagramChannel.send(byteBuffer, commandManager.getSocketAddress());
            //System.out.println(workerEntry.getValue().getName() + " - > " + i);
        }

        return true;
    }
        catch (Exception e){
            System.out.println(e + " -> CommandResult");
            return  false;
        }

    }


    private boolean sendCollection1() throws IOException {
        try {

            dataBaseManager.getCollection().get(1).setNum(hash);//Чтобы не было повторных отправок коллекции

            datagramChannel.send
                    (ByteBuffer.wrap(Objects.requireNonNull(Serialization.SerializeObject(dataBaseManager.getCollection()))),
                            commandManager.getSocketAddress());

            //System.out.println("sendCollection bytes" + ByteBuffer.wrap(Objects.requireNonNull(Serialization.SerializeObject(dataBaseManager.getCollection()))).array().length);
            return true;
        }
        catch (Exception e){
            System.out.println(e + " -> CommandResult");
            return  false;
        }

    }


}
