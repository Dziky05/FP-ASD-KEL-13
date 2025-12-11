import java.util.*;

public class Main {
    private static List<City> cities = new ArrayList<>();

    public static void main(String[] args) {
        initSampleData();
        Scanner sc = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("      HALO!! SMART TRAVELING PLANNER");
        System.out.println("==============================================");

        // --- 1. PILIH KOTA ---
        System.out.println("\n[Langkah 1] Pilih Lokasi Anda:");
        System.out.println("1. Surabaya");
        System.out.println("2. Jakarta");
        System.out.println("3. Bandung");

        int kotaIdx = -1;
        while (true) {
            System.out.print("Pilihan (1-3): ");
            String line = sc.nextLine().trim();
            try {
                int pick = Integer.parseInt(line);
                if (pick >= 1 && pick <= cities.size()) { kotaIdx = pick - 1; break; }
            } catch (NumberFormatException ignored) {}
            System.out.println("Input tidak valid.");
        }

        City chosen = cities.get(kotaIdx);
        System.out.printf(">> Kota Terpilih: %s\n", chosen.getName());

        // --- 2. TENTUKAN POSISI SAAT INI ---
        System.out.println("\n[Langkah 2] Di mana posisi Anda saat ini?");
        System.out.println("Daftar Lokasi:");
        showPlaces(chosen.getPlaces());

        System.out.println("\nMasukkan ID tempat Anda berada sekarang.");
        System.out.println("(Ketik 0 jika baru sampai di Stasiun)");

        int startId = -1;
        Place startPlace = null;
        while(startPlace == null) {
            System.out.print("Posisi Saya (ID): ");
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) continue;
                startId = Integer.parseInt(input);
                startPlace = chosen.getPlaceById(startId);
                if(startPlace == null) System.out.println("ID tidak ditemukan. Cek daftar diatas.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka.");
            }
        }
        System.out.println(">> Posisi Awal: " + startPlace.getName());

        // --- 3. MENU UTAMA (LOOPING TERUS MENERUS) ---
        while (true) {
            System.out.println("\n[Langkah 3] Menu Utama - Apa yang ingin Anda lakukan?");
            System.out.println("1. Buat Rute Perjalanan (Lanjut ke Navigasi)");
            System.out.println("2. Cek Wisata Terdekat (Lihat Daftar)");
            System.out.println("3. Cek Wisata Termurah (Lihat Daftar)");
            System.out.println("4. Keluar Aplikasi");
            System.out.print("Pilihan: ");
            String menuChoice = sc.nextLine().trim();

            List<Place> displayList = new ArrayList<>();

            if (menuChoice.equals("1")) {
                // ============================================================
                // LOGIKA NOMOR 1 (NAVIGASI) PINDAH KE SINI (DALAM LOOP)
                // ============================================================
                System.out.println("\n--- Mode Navigasi Aktif ---");
                System.out.println("Daftar Tempat Tersedia:");
                showPlaces(chosen.getPlaces());

                System.out.println("\n[Langkah 4] Pilih tempat yang ingin dikunjungi");
                System.out.println("Masukkan ID destinasi (pisahkan spasi, cth: 2 4 5)");

                List<Place> destinations = new ArrayList<>();

                // Loop kecil untuk memastikan user input destinasi valid sebelum lanjut
                while(destinations.isEmpty()) {
                    System.out.print("Tujuan: ");
                    String line = sc.nextLine().trim();
                    if(line.isEmpty()) {
                        System.out.println("Input kosong. Silakan masukkan ID.");
                        continue;
                    }

                    String[] tokens = line.split("\\s+");
                    for (String t : tokens) {
                        if (t.isBlank()) continue;
                        try {
                            int id = Integer.parseInt(t);
                            if (id == startId) continue;
                            Place p = chosen.getPlaceById(id);
                            if (p != null && !destinations.contains(p)) destinations.add(p);
                        } catch (NumberFormatException ignored) {}
                    }

                    if(destinations.isEmpty()) {
                        System.out.println("Tidak ada ID valid yang dimasukkan. Coba lagi.");
                    }
                }

                // --- PROSES ROUTING (Dijkstra) ---
                System.out.println("\n[Langkah 5] Rute Perjalanan Terbaik (Dijkstra Analysis)");
                System.out.println("-------------------------------------------------------");

                double[][] matrix = chosen.getDistancesBetweenPlaces();
                Graph graph = new Graph(matrix);
                List<Place> route = new ArrayList<>();
                double totalDistance = 0;
                Place currentPlace = startPlace;

                // Copy destinations agar list asli tidak hilang jika loop diulang
                List<Place> remainingDestinations = new ArrayList<>(destinations);

                while (!remainingDestinations.isEmpty()) {
                    Place nearest = null;
                    double minDist = Double.MAX_VALUE;
                    int currentIndex = chosen.getPlaces().indexOf(currentPlace);

                    Graph.DijkstraResult result = graph.dijkstra(currentIndex);

                    for (Place candidate : remainingDestinations) {
                        int candidateIndex = chosen.getPlaces().indexOf(candidate);
                        double dist = result.dist[candidateIndex];

                        if (dist < minDist) {
                            minDist = dist;
                            nearest = candidate;
                        }
                    }

                    if (nearest != null) {
                        int nearestIndex = chosen.getPlaces().indexOf(nearest);
                        String pathStr = result.reconstructPath(nearestIndex);

                        route.add(nearest);
                        totalDistance += minDist;
                        remainingDestinations.remove(nearest);

                        System.out.printf(" -> Menuju %-25s (Jarak: %.1f km | Via Node: %s)\n",
                                nearest.getName(), minDist, pathStr);

                        currentPlace = nearest;
                    }
                }

                // --- REKAP RUTE ---
                System.out.println("\n=================================");
                System.out.println("       REKAP PERJALANAN");
                System.out.println("=================================");
                System.out.println("START : " + startPlace.getName());
                for (Place p : route) {
                    System.out.println("  ↓   ");
                    System.out.println("TIBA  : " + p.getName());
                }
                System.out.println("---------------------------------");
                System.out.printf("Total Jarak Tempuh: %.1f km\n", totalDistance);

                // --- PAUSE AGAR TIDAK LANGSUNG CLS/EXIT ---
                System.out.println("\n[Tekan ENTER untuk kembali ke menu...]");
                sc.nextLine();

            } else if (menuChoice.equals("2")) {
                // Quick Sort (Terdekat)
                System.out.println("\n--- Mode Eksplorasi: Wisata Terdekat dari " + startPlace.getName() + " ---");
                double[][] matrix = chosen.getDistancesBetweenPlaces();
                int startIndex = chosen.getPlaces().indexOf(startPlace);

                for (Place p : chosen.getPlaces()) {
                    int targetIndex = chosen.getPlaces().indexOf(p);
                    double realDistance = matrix[startIndex][targetIndex];
                    displayList.add(new Place(p.getId(), p.getName(), realDistance, p.getPrice()));
                }

                Sorter.quickSortByDistance(displayList);
                showPlaces(displayList);

                System.out.println("\n[Tekan ENTER untuk kembali ke menu...]");
                sc.nextLine();

            } else if (menuChoice.equals("3")) {
                // Merge Sort (Termurah)
                System.out.println("\n--- Mode Eksplorasi: Wisata Termurah ---");
                displayList.addAll(chosen.getPlaces());
                displayList = Sorter.mergeSortByPrice(displayList);
                showPlaces(displayList);

                System.out.println("\n[Tekan ENTER untuk kembali ke menu...]");
                sc.nextLine();

            } else if (menuChoice.equals("4")) {
                System.out.println("Terima kasih telah menggunakan aplikasi.");
                return; // Keluar Program
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void showPlaces(List<Place> places) {
        for (Place p : places) {
            System.out.println(p);
        }
    }

    private static void initSampleData() {
        // Surabaya
        City surabaya = new City("Surabaya");
        surabaya.addPlace(new Place(0,"Stasiun Pasar Turi", 0, 0));
        surabaya.addPlace(new Place(1,"Tugu Pahlawan", 2.0, 0));
        surabaya.addPlace(new Place(2,"House of Sampoerna", 3.5, 10000));
        surabaya.addPlace(new Place(3,"Monumen Kapal Selam", 4.0, 5000));
        surabaya.addPlace(new Place(4,"Kebun Binatang Sby", 8.0, 20000));
        surabaya.addPlace(new Place(5,"Jembatan Suroboyo", 5.0, 0));
        surabaya.addPlace(new Place(6,"Surabaya North Quay", 15.0, 0));
        surabaya.addPlace(new Place(7,"Ciputra Waterpark", 18.0, 80000));
        surabaya.addPlace(new Place(8,"Masjid Cheng Hoo", 3.0, 0));
        surabaya.addPlace(new Place(9,"Pantai Kenjeran", 12.0, 0));
        surabaya.addPlace(new Place(10,"Graha Famili", 6.5, 25000));
        cities.add(surabaya);

        // Jakarta
        City jakarta = new City("Jakarta");
        jakarta.addPlace(new Place(0,"Stasiun Gambir", 0, 0));
        jakarta.addPlace(new Place(1,"Monas", 4.0, 15000));
        jakarta.addPlace(new Place(2,"Kota Tua", 10.0, 0));
        jakarta.addPlace(new Place(3,"Ancol", 20.0, 50000));
        jakarta.addPlace(new Place(4,"Taman Mini", 25.0, 40000));
        jakarta.addPlace(new Place(5,"Istiqlal", 5.5, 0));
        jakarta.addPlace(new Place(6,"Kebun Raya Bogor", 60.0, 30000));
        jakarta.addPlace(new Place(7,"Museum Nasional", 6.0, 15000));
        jakarta.addPlace(new Place(8,"Bundaran HI", 3.5, 0));
        jakarta.addPlace(new Place(9,"Setu Babakan", 22.0, 10000));
        jakarta.addPlace(new Place(10,"Plaza Indonesia", 4.2, 0));
        cities.add(jakarta);

        // Bandung
        City bandung = new City("Bandung");
        bandung.addPlace(new Place(0,"Stasiun Bandung", 0, 0));
        bandung.addPlace(new Place(1,"Gedung Sate", 6.0, 0));
        bandung.addPlace(new Place(2,"Braga", 5.0, 0));
        bandung.addPlace(new Place(3,"Dusun Bambu", 30.0, 60000));
        bandung.addPlace(new Place(4,"Trans Studio", 12.0, 120000));
        bandung.addPlace(new Place(5,"Kawah Putih", 45.0, 40000));
        bandung.addPlace(new Place(6,"Factory Outlet Dago", 8.0, 0));
        bandung.addPlace(new Place(7,"Farmhouse Lembang", 28.0, 30000));
        bandung.addPlace(new Place(8,"Jalan Riau", 7.0, 0));
        bandung.addPlace(new Place(9,"Saung Angklung Udjo", 18.0, 25000));
        bandung.addPlace(new Place(10,"Obs. Bosscha", 35.0, 15000));
        cities.add(bandung);
    }
}