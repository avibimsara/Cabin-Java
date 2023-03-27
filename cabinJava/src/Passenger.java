public class Passenger {
    private String firstName;
    private String surname;
    private double expenses;

    public Passenger() {

    }

    public Passenger(String firstName, String surname, double expenses){
        this.firstName = firstName;
        this.surname = surname;
        this.expenses = expenses;
    }

    //applying getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }
}
