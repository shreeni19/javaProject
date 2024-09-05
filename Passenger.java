
public class Passenger {
    static int id = -1;
    public String name;
    public int age;
    public String berthPrefernce;
    public int seatNumber;
    public int passengerId;
    public String allotedBerth;

    public Passenger(String name, int age, String berthPreference) {
        this.name = name;
        this.age = age;
        this.berthPrefernce = berthPreference;
        allotedBerth = "";
        passengerId = id + 1;
        seatNumber = -1;
    }
}
