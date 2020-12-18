package Worker;


import java.io.Serializable;

/**
 * Класс Пещеры
 *
 *
 */

public class Organization implements Serializable {

    private Long employeesCount;//Поле может быть null

    public Organization(Long employeesCount) {
        this.employeesCount = employeesCount;
    }

    public Organization(){

    }

    public Long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(Long num) {
        this.employeesCount = num;
    }

    @Override
    public String toString() {
        if(employeesCount <= 0){
            return "null";
        }
        return "" + employeesCount;
    }


}

