public class Ticket {
    private User owner;
    private Plane plane;
    private Flight flight;

    private int place;

    private float price;

    public Ticket(User owner, Plane plane, Flight flight) {
        this.owner = owner;
        this.plane = plane;
        this.flight = flight;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "owner=" + owner +
                ", plane=" + plane +
                ", flight=" + flight +
                '}';
    }
}
