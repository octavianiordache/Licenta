import java.util.List;

public class Flight {

    private int id;
    private final String date;
    private final String time;
    private final List<User> passengers;

    private final String departure;

    private final String destination;

    private Plane plane;

    public Flight(int i, String date, String time, String s, String destination1, List<User> passengers, String departure, String destination) {
        this.id= id;
        this.date = date;
        this.time = time;
        this.passengers = passengers;
        this.departure = departure;
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<User> getPassengers() {
        return passengers;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", passengers=" + passengers +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", plane=" + plane +
                '}';
    }
}
