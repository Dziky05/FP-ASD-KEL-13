import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<Place> places;
    // adjacency matrix representing distances between places (indexes 0..n-1)
    private double[][] distancesBetweenPlaces;

    public City(String name) {
        this.name = name;
        this.places = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Place> getPlaces() { return places; }

    public void addPlace(Place p) {
        places.add(p);
        rebuildDistancesMatrixIfNeeded();
    }

    private void rebuildDistancesMatrixIfNeeded() {
        int n = places.size();
        distancesBetweenPlaces = new double[n][n];
        // default: symmetric random-like distances derived from absolute diff of distanceFromHotel
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) distancesBetweenPlaces[i][j] = 0;
                else {
                    double d1 = places.get(i).getDistanceFromHotel();
                    double d2 = places.get(j).getDistanceFromHotel();
                    distancesBetweenPlaces[i][j] = Math.abs(d1 - d2) + 1.0; // simple heuristic
                }
            }
        }
    }

    public double[][] getDistancesBetweenPlaces() {
        return distancesBetweenPlaces;
    }

    public Place getPlaceById(int id) {
        for (Place p : places) if (p.getId() == id) return p;
        return null;
    }
}

