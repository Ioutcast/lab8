package Utils.ServerPart;

import Worker.*;
import Utils.OtherUtils.PortForwarder;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;


public class DataBaseManager implements Serializable {

    protected static LinkedHashMap<Integer, Worker> workerLinkedHashMap = new LinkedHashMap<Integer, Worker>();
    private String USER = "s284702";
    private String PASSWORD = "tfo348";

    public DataBaseManager() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver ());
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }


    public boolean CheckDB(){
        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public boolean CheckUser(String login){
        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)) {

            PreparedStatement ps = connection.prepareStatement("select * from users where login = ?");
            ps.setString(1, login);
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    //Добавление юзера в БД
    public boolean AddUser(String login, String password){
        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO s284702.users(login, pass) VALUES (?, ?)");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            userLogin = login;
            return true;
        }catch (SQLException e) {
        return false;
        }
    }

//метод для авторизации пользователя
    public boolean login(String login, String password) throws SQLException {

        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)) {

            PreparedStatement ps = connection.prepareStatement("select * from users where login = ?");

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return false;

            if(rs.getString("pass").equals(password)) {
                userLogin = login;
                return true;
            }

            return false;
        }
    }

    public boolean updateCollectionFromDataBase(){
        LinkedHashMap<Integer, Worker> linkedHashMap = new LinkedHashMap<>();
        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM worker");
            while (resultSet.next()){

                Worker worker = new Worker();
                worker.setId(resultSet.getInt("id"));
                worker.setName(resultSet.getString("name"));
                worker.setAge(resultSet.getInt("age"));
                worker.setStatus(Status.byOrdinal(resultSet.getString("color")));
                worker.setType(Location.byOrdinal(resultSet.getString("type")));
                worker.setCharacter(OrganizationType.byOrdinal(resultSet.getString("character")));
                worker.setCoordinates(new Coordinates(resultSet.getInt("x"), resultSet.getInt("y")));
                worker.setCave(new Organization(resultSet.getLong("cave_depth")));
                worker.setUserName(resultSet.getString("user"));
                try {
                    worker.setLocaleDate(LocalDateTime.parse(resultSet.getString("creation_date")));
                    //System.out.println(worker.GetTime());
                }catch (NullPointerException e){
                    worker.setLocaleDate(LocalDateTime.now());
                }

                linkedHashMap.put(worker.getId(), worker);
            }
            workerLinkedHashMap.clear();
            workerLinkedHashMap = linkedHashMap;
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addToDataBase(Worker worker) {
        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO worker (\"id\",\"name\", \"age\", \"color\", \"type\",  \"character\", \"cave_depth\", \"x\", \"y\", \"user\", \"creation_date\") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setLong(1, worker.getId());
            preparedStatement.setString(2, worker.getName());
            preparedStatement.setLong(3, worker.getAge());
            preparedStatement.setString(4, String.valueOf(worker.getStatus()));
            preparedStatement.setString(5, String.valueOf(worker.getType()));
            preparedStatement.setString(6, String.valueOf(worker.getCharacter()));
            preparedStatement.setDouble(7, worker.getCave().getEmployeesCount());
            preparedStatement.setLong(8, worker.getCoordinates().getX());
            preparedStatement.setLong(9, worker.getCoordinates().getY());
            preparedStatement.setString(10, worker.getUserName());
            preparedStatement.setString(11, worker.GetTime().toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addToDataBase1(Worker worker) {
        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO worker (\"id\",\"name\", \"age\", \"color\", \"type\",  \"character\", \"cave_depth\", \"x\", \"y\", \"user\", \"creation_date\") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setLong(1, worker.getId());
            preparedStatement.setString(2, worker.getName());
            preparedStatement.setLong(3, worker.getAge());
            preparedStatement.setString(4, String.valueOf(worker.getStatus()));
            preparedStatement.setString(5, String.valueOf(worker.getType()));
            preparedStatement.setString(6, String.valueOf(worker.getCharacter()));
            preparedStatement.setLong(7, worker.getCoordinates().getX());
            preparedStatement.setLong(8, worker.getCoordinates().getY());
            preparedStatement.setString(9, userLogin);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateElementInDataBase(Worker worker) {
        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM worker");
            long maxId = 0;
            while (resultSet.next()){
                maxId = resultSet.getLong("max");
            }
            if (addToDataBase1(worker)){
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE worker SET id = " + worker.getId() + " WHERE id = (SELECT MAX(id) FROM worker);");
                preparedStatement.executeUpdate();
                return true;
            } else {
            }
        } catch (SQLException e) {
            System.out.println("");
            return true;
        }
        return false;
    }


    public boolean updateElementInDataBase1(Worker worker, String user) {
        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM worker WHERE id = " + worker.getId());

            String userString = "";

            while (resultSet.next()){
                userString = resultSet.getString("user");
            }

            if(user.equals(userString)){
                removeFromDataBase(worker.getId(), user);
                addToDataBase(worker);
              return true;
            } else return false;

        } catch (SQLException e) {
            return false;
        }
    }




    public boolean removeFromDataBase(int id, String userLogin){
        try (Connection connection = DriverManager.getConnection(PortForwarder.forwardAnyPort(), USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM worker WHERE id = " + id);
            while (resultSet.next()){
                if (!resultSet.getString("user").equals(userLogin)){
                    return false;
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM worker WHERE id = " + id + " AND \"user\" = '" + userLogin + "'");
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    public void setUSER(String userLogin){
        this.userLogin = userLogin;
    }

    public void setPASSWORD(String PASSWORD){
        this.PASSWORD = PASSWORD;
    }

    public String getUSER(){
        return userLogin;
    }



    public  LinkedHashMap<Integer, Worker> getCollection() {
        return workerLinkedHashMap;
    }

    private String userLogin = "-___-";

    public  void setCollection(LinkedHashMap<Integer, Worker> workerLinkedHashMap) {
        DataBaseManager.workerLinkedHashMap = workerLinkedHashMap;
    }


}
