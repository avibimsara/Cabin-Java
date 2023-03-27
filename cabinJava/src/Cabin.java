

public class Cabin {
    private String cabinName;
    private int cabinNum = 0;
    private Passenger passengers  = new Passenger();
    public CircularQueue q;

    public Cabin() {

    }

    public CircularQueue getQ() {
        return q;
    }

    public void setQ(CircularQueue q) {
        this.q = q;
    }

    public Cabin(String cabinName, int cabinNum) {
        this.cabinName = cabinName;
        this.cabinNum = cabinNum;
    }

    //applying getters and setters

    public String getCabinName() {
        return cabinName;
    }

    public void setCabinName(String cabinName) {
        this.cabinName = cabinName;
    }

    public int getCabinNum() {
        return cabinNum;
    }

    public void setCabinNum(int cabinNum) {
        this.cabinNum = cabinNum;
    }

    public Passenger getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger passengers) {
        this.passengers = passengers;
    }

    public String toString(){    // to pass string value when writing file
        return getPassengers().getFirstName()+ " " + getPassengers().getSurname();
    }
}

