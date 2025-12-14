import java.util.*;

public class Place {
    private int id;
    private String name;
    private double distance; // km
    private double price; // rupiah or representative cost

    public Place(int id, String name, double distance, double price) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getDistance() { return distance; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format(
                "%-30s | %8.1f km | Rp %8.0f",
                name,
                distance,
                price
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return id == place.id && Objects.equals(name, place.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
