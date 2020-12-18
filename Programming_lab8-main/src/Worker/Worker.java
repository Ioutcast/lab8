package Worker;

import javafx.beans.property.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

/**
 *
 *
 * Храниться в коллекции
 *
 *
 *
 */

public class Worker implements ColorText, Comparable<Worker>, Serializable {


    private Integer id; //Значение поля должно быть больше 0
    //Значение поля дожно быть уникальным
    // Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null
    private Status status; //Поле не может быть null
    private Coordinates coordinates;//Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля дожно генерироваться автоматически
    private int age; //Значение должно быть больше 0
    private Location type; //Поле не может быть null
    private OrganizationType character; //Поле может быть null
    private Organization cave; //Поле может быть null
    private String userName = "null";


    public Worker() {

        this.creationDate = LocalDateTime.now();
    }


    public Worker(String UserName){
        this.creationDate = LocalDateTime.now();
        this.userName = UserName;
    }
    /**
     * @param name - имя дракона
     * @param status - цвет дракона
     * @param coordinates - координаты дракона (x, y)
     * @param age - возраст дракона
     * @param type - тип дракона
     * @param character - характер
     * @param cave - пещера дракона (её глубина)
     */
    public Worker(String name, Status status, Coordinates coordinates, int age, Location type,
                  OrganizationType character, Organization cave) {
        this.name = name;
        this.status = status;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.age = age;
        this.type = type;
        this.character = character;
        this.cave = cave;
    }


//////////Геттеры и сеттеры

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Location getType() {
        return type;
    }

    public void setType(Location type) {
        this.type = type;
    }

    public OrganizationType getCharacter() {
        return character;
    }

    public void setCharacter(OrganizationType dragonCharacter) {
        this.character = dragonCharacter;
    }

    public Organization getCave() {
        return this.cave;
    }

    public void setCave(Organization cave) {
        this.cave = cave;
    }

    public LocalDateTime GetTime(){
        return creationDate;
    }

    public void setLocaleDate(LocalDateTime localeDate){
        creationDate = localeDate;
    }

    public Worker getWorker(){
        return this;
    }


////////
    /**
     * Нужен для сравнивания экзепляров коллекции
     */
    public int compareTo(Worker dr) {
//todo res
        int result=0; //= this.id.compareTo(dr.getId());

        if (result==0){
            result = this.name.compareTo(dr.name);
        }

        if (result==0){
            result = this.coordinates.compareTo(dr.coordinates);
        }

        return result;
    }


    @Override
    public String toString(){
        return "Dragon{id = " + id + " name = " +
                ColorText.Text(name, status) + ", owner:  " + userName + "} \n";
    }



    //вывод со всеми полями
    public String toDragonString() {
        return "Dragon{" +
                "id=" + id +
                ", name='" + ColorText.Text(name, status) + '\'' +
                ", age = "+ age + ",  color = " + status + ", type = " + type + ", character = " + character + ",\n cave = " + cave
                +", coordinates=" + coordinates.toString() + ", Creation Time = " + GetTime() +
                ", owner: " + userName + "}";
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        return false;
    }


    public SimpleIntegerProperty getIdProperty() {
        return new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty getNameProperty() {
        return new SimpleStringProperty(name);
    }

    public SimpleIntegerProperty getAgeProperty() {
        return new SimpleIntegerProperty(age);
    }

    public Coordinates getCoordinatesProperty(){
        return coordinates;
    }

    public SimpleFloatProperty getDepthProperty(){
        return new SimpleFloatProperty(cave.getEmployeesCount());
    }

    public SimpleStringProperty getColorProperty() {
        return new SimpleStringProperty(status.toString());
    }

    public SimpleStringProperty getTypeProperty() {
        return new SimpleStringProperty(type.toString());
    }

    public SimpleStringProperty getCharProperty() {
        return new SimpleStringProperty(character.toString());
    }

    public SimpleStringProperty getUserProperty() {
        return new SimpleStringProperty(userName);
    }



    private int size = 0;

    public void setNum(int size){
        this.size = size;
    }

    public int getNum(){
        return size;
    }

}
