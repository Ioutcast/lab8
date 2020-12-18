package Utils.ClientPart;

import Worker.*;
import Utils.ServerPart.ServerClasses.Commands.AbstractCommand;
import Utils.ServerPart.ServerClasses.Commands.Show;
import Utils.ServerPart.ServerClasses.SpecialCommands.Authorization;
import Utils.ServerPart.ServerClasses.SpecialCommands.CheckClassDB;
import Utils.OtherUtils.Serialization;
import controllers.MainController;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.*;
import java.util.stream.Collectors;

public class ClientHandler{

    MainController mainController;
    private DatagramChannel datagramChannel;
    private static SocketAddress socketAddress;
    private static String login = "";
    private String password = "";
    private boolean authorized = false;
    private PriorityQueue<Worker> clientDq = new PriorityQueue<>();
    private volatile static List<Worker> clientList = new LinkedList<>();
    Integer hash;

    public ClientHandler(){

    }

    public ClientHandler(MainController mainController){
        this.mainController = mainController;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void connect(String hostname, int port) throws IOException{
        socketAddress = new InetSocketAddress(hostname, port);
        datagramChannel.connect(socketAddress);
        //System.out.println("Устанавливаем соединение с " + hostname + " по порту " + port);
    }






    public String receiveAnswer() throws IOException {
        try {
            byte[] bytes = new byte[65536];
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            socketAddress = datagramChannel.receive(buffer);
            String msg = new String(buffer.array());
            return msg;
        }catch (PortUnreachableException e){
            mainController.showAlert(Alert.AlertType.ERROR, "Error", "Error");
            return null;
        }
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isAuthorized() {
        return authorized;
    }



    public PriorityQueue<Worker> getClientDq() {
        return clientDq;
    }
    public List<Worker> getClientList(){return clientList;}


        //Проверка состояния сервера. Использую String, потому что 3 состояния -
        // сервер работает, сервер не работает, не работает БД
    public String checkConnection() throws IOException {

        try {
            sendRequest(new CheckClassDB("CCD", "CCD"));
            String answer;

            while(true){
                answer = receiveAnswer().trim();
                if(!answer.equals("")) break;
            }
            return answer;

        }catch (PortUnreachableException | NullPointerException e){
            return "server_is_not_available";
        }
    }


    //Отправка команды c параметром на сервер

    private void sendCommandWithParam(AbstractCommand command, String parameter) throws IOException{
        if (command != null){
            command.setString(parameter);
        }
        ByteBuffer buffer = ByteBuffer.wrap(new Serialization().SerializeObject(command));
        datagramChannel.send(buffer, socketAddress);
        sendParam(parameter);
    }

    //Отправка строки на сервер

    public void sendParam(String str) throws IOException{
        ByteBuffer buffer = ByteBuffer.wrap(new Serialization().SerializeObject(str));
        datagramChannel.send(buffer, socketAddress);

    }
    public void sendShowRequest() throws IOException {
        if(hash == null){
            hash = 0;
        }

        Show show = new Show();
        show.setString(hash.toString());
        sendCommand(show);
    }

    //Отправка команды на сервер
    public void sendCommand(AbstractCommand str) throws IOException{
        ByteBuffer buffer = ByteBuffer.wrap(new Serialization().SerializeObject(str));
        datagramChannel.send(buffer, socketAddress);
        if(str.getCommand().equals("exit")){
            System.out.println("Завершение программы");
            System.exit(0);
        }

    }
    //Отправка запроса от пользователя (для авторизации)
    public void sendRequest(Authorization authorization) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(new Serialization().SerializeObject(authorization));
        datagramChannel.send(buffer, socketAddress);
    }



    //Отправляет команду вместе с объектом
    public void sendWorkerCommand(AbstractCommand command, Worker worker, Integer id) throws IOException {

        worker.setId(id);
        command.setWorker(worker);
        ByteBuffer buffer = ByteBuffer.wrap(new Serialization().SerializeObject(command));
        datagramChannel.send(buffer, socketAddress);
        //sendWorker(Worker);
    }



    public String receiveCollection() throws IOException {
        clientList.clear();
        List<Worker> list;
        byte[] bytes = new byte[65536];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        String receiver = "";
        while(true){
            socketAddress = datagramChannel.receive(buffer);
            receiver = new String(buffer.array()).trim();
            while(receiver.equals("")){
                socketAddress = datagramChannel.receive(buffer);
                receiver = new String(buffer.array()).trim();
            }


            if(receiver.equals("err_show")) {
//                System.out.println(receiver);
                return receiver;
            }

            try{
                ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
                //System.out.println("buffer = " + buffer.array().length);
                Object o = stream.readObject();
                HashMap<Integer, Worker> d = (LinkedHashMap) o;
                hash = d.get(1).getNum();
                list = d.values()
                        .stream()
                        .map(v -> v.getWorker())
                        .collect(Collectors.toList());

                stream.close();
                break;
            }catch (IllegalMonitorStateException | ClassNotFoundException e){}
            //System.out.println("receiveCollection -> " + receiver);

        }


        socketAddress = datagramChannel.receive(buffer);
        receiver = new String(buffer.array()).trim();
        while(receiver.equals("")){
            socketAddress = datagramChannel.receive(buffer);
            receiver = new String(buffer.array()).trim();
        }

        if(receiver.contains("suc_show")){
            Collections.sort(list);
            clientList.clear();
            clientList.addAll(list);
            return "suc_show";
        }


        System.out.println("ClientHandler " + receiver);
        return receiver;
    }
}
