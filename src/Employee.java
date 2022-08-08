import java.util.ArrayList;
import java.util.List;

public abstract class Employee {
    private String firstName;
    private String lastName;
    private String pesel;
    private double salary;

    public Employee(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "firstName: " + firstName + ", lastName: " + lastName + ", pesel: " + pesel + ", salary: " + salary;
    }

    private void createNewTask(){}
    private void joinToTeam(){}
}
