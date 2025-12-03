//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<City> cities = new ArrayList<>();
    private static TreeNode rootTree = new TreeNode("Indonesia");

    public static void main(String[] args) {
        initSampleData();
        Scanner sc = new Scanner(System.in);

        System.out.println("Halo!! selamat datang di Smart Traveling Planner");
        System.out.println("Pilih kota : 1.Surabaya 2.Jakarta 3.Bandung (ketik angka)");
        int kotaIdx = -1;
        while (true) {
            System.out.print("Pilihan: ");
            String line = sc.nextLine().trim();
            try {
                int pick = Integer.parseInt(line);
                if (pick >= 1 && pick <= cities.size()) { kotaIdx = pick - 1; break; }
            } catch (NumberFormatException ignored) {}
            System.out.println("Input tidak valid. Masukkan angka 1-3.");
        }

        City chosen = cities.get(kotaIdx);
        System.out.printf("Kamu memilih: %s\n", chosen.getName());
        showPlaces(chosen.getPlaces());

        System.out.println("\nMasukkan pilihan destinasi (boleh lebih dari 1 pisah spasi).");
        System.out.println("Atau ketik 11 untuk urutkan dulu dari yang terdekat.");
        System.out.println("Atau ketik 12 untuk urutkan dulu dari yang termurah.");
        System.out.print("Pilihan: ");
        String line = sc.nextLine().trim();

        List<Place> displayList = new ArrayList<>(chosen.getPlaces()); // baseline

        if (line.equals("11")) {
            Sorter.quickSortByDistance(displayList);
            System.out.println("Daftar destinasi diurutkan berdasarkan jarak (terdekat -> terjauh):");
            showPlaces(displayList);
            System.out.print("Sekarang pilih destinasi (contoh: 1 3 5): ");
            line = sc.nextLine().trim();
        } else if (line.equals("12")) {
            displayList = Sorter.mergeSortByPrice(displayList);
            System.out.println("Daftar destinasi diurutkan berdasarkan harga (termurah -> termahal):");
            showPlaces(displayList);
            System.out.print("Sekarang pilih destinasi (contoh: 1 3 5): ");
            line = sc.nextLine().trim();
        }

        // parse selections (ids separated by spaces)
        String[] tokens = line.split("\\s+");
        List<Place> chosenPlaces = new ArrayList<>();
        for (String t : tokens) {
            if (t.isBlank()) continue;
            try {
                int id = Integer.parseInt(t);
                Place p = chosen.getPlaceById(id);
                if (p != null) chosenPlaces.add(p);
                else System.out.printf("Warning: tempat dengan id %d tidak ditemukan dan diabaikan.\n", id);
            } catch (NumberFormatException e) {
                System.out.printf("Token '%s' bukan angka, diabaikan.\n", t);
            }
        }

        if (chosenPlaces.isEmpty()) {
            System.out.println("Tidak ada destinasi yang valid dipilih. Program selesai.");
            return;
        }

        // Output: urutkan pilihan sesuai jarak dari hotel (terdekat dulu)
        chosenPlaces.sort(Comparator.comparingDouble(Place::getDistanceFromHotel));
        System.out.println("\nUrutan destinasi yang sudah dipilih (diurutkan berdasarkan jarak dari hotel):");
        for (Place p : chosenPlaces) {
            System.out.println(p);
        }

        // Optional: show suggestion of path via Dijkstra between first and next (not optimized for full TSP)
        System.out.println("\nCatatan: jika ingin rute terpendek antar-destinasi (graph/Dijkstra), fitur dapat diaktifkan di pengembangan berikutnya.");
    }

    private static void showPlaces(List<Place> places) {
        for (Place p : places) {
            System.out.println(p);
        }
    }

    private static void initSampleData() {
        // Surabaya
        City surabaya = new City("Surabaya");
        surabaya.addPlace(new Place(1,"Tugu Pahlawan", 2.0, 0));
        surabaya.addPlace(new Place(2,"House of Sampoerna", 3.5, 10000));
        surabaya.addPlace(new Place(3,"Monumen Kapal Selam", 4.0, 5000));
        surabaya.addPlace(new Place(4,"Kebun Binatang Surabaya", 8.0, 20000));
        surabaya.addPlace(new Place(5,"Jembatan Suroboyo", 5.0, 0));
        surabaya.addPlace(new Place(6,"Surabaya North Quay", 15.0, 0));
        surabaya.addPlace(new Place(7,"Ciputra Waterpark", 18.0, 80000));
        surabaya.addPlace(new Place(8,"Masjid Cheng Hoo", 3.0, 0));
        surabaya.addPlace(new Place(9,"Pantai Kenjeran", 12.0, 0));
        surabaya.addPlace(new Place(10,"Graha Famili", 6.5, 25000));
        cities.add(surabaya);
        TreeNode city1 = new TreeNode("Surabaya");
        rootTree.addChild(city1);

        // Jakarta
        City jakarta = new City("Jakarta");
        jakarta.addPlace(new Place(1,"Monas", 4.0, 15000));
        jakarta.addPlace(new Place(2,"Kota Tua", 10.0, 0));
        jakarta.addPlace(new Place(3,"Ancol", 20.0, 50000));
        jakarta.addPlace(new Place(4,"Taman Mini", 25.0, 40000));
        jakarta.addPlace(new Place(5,"Istiqlal", 5.5, 0));
        jakarta.addPlace(new Place(6,"Kebun Raya Bogor (dekat)", 60.0, 30000));
        jakarta.addPlace(new Place(7,"Museum Nasional", 6.0, 15000));
        jakarta.addPlace(new Place(8,"Bundaran HI", 3.5, 0));
        jakarta.addPlace(new Place(9,"Setu Babakan", 22.0, 10000));
        jakarta.addPlace(new Place(10,"Plaza Indonesia (mall)", 4.2, 0));
        cities.add(jakarta);
        TreeNode city2 = new TreeNode("Jakarta");
        rootTree.addChild(city2);

        // Bandung
        City bandung = new City("Bandung");
        bandung.addPlace(new Place(1,"Gedung Sate", 6.0, 0));
        bandung.addPlace(new Place(2,"Braga", 5.0, 0));
        bandung.addPlace(new Place(3,"Dusun Bambu", 30.0, 60000));
        bandung.addPlace(new Place(4,"Trans Studio Bandung", 12.0, 120000));
        bandung.addPlace(new Place(5,"Kawah Putih", 45.0, 40000));
        bandung.addPlace(new Place(6,"Factory Outlet Dago", 8.0, 0));
        bandung.addPlace(new Place(7,"Farmhouse Lembang", 28.0, 30000));
        bandung.addPlace(new Place(8,"Jalan Riau Shopping", 7.0, 0));
        bandung.addPlace(new Place(9,"Saung Angklung Udjo", 18.0, 25000));
        bandung.addPlace(new Place(10,"Observatorium Bosscha", 35.0, 15000));
        cities.add(bandung);
        TreeNode city3 = new TreeNode("Bandung");
        rootTree.addChild(city3);

        // populate tree leaves with place names (simple)
        for (Place p : surabaya.getPlaces()) city1.addChild(new TreeNode(p.getName()));
        for (Place p : jakarta.getPlaces()) city2.addChild(new TreeNode(p.getName()));
        for (Place p : bandung.getPlaces()) city3.addChild(new TreeNode(p.getName()));
    }
}
