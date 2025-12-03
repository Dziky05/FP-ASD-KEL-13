import java.util.*;

public class Place {
    private int id;
    private String name;
    private double distanceFromHotel; // km
    private double price; // rupiah or representative cost

    public Place(int id, String name, double distanceFromHotel, double price) {
        this.id = id;
        this.name = name;
        this.distanceFromHotel = distanceFromHotel;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getDistanceFromHotel() { return distanceFromHotel; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%d. %s (jarak: %.1f km, harga: Rp%.0f)", id, name, distanceFromHotel, price);
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